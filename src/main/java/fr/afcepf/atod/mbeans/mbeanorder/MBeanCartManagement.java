<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.mbeans.mbeanorder;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.xml.ws.BindingProvider;

import org.apache.log4j.Logger;

import fr.afcepf.atod.mbeans.mbeanproduct.MBeanProduct;
import fr.afcepf.atod.mbeans.mbeanuser.MBeanConnexion;
import fr.afcepf.atod.mbeans.mbeanuser.MBeanMail;
import fr.afcepf.atod.onwine.ws.soap.currency.CurrenciesWSException_Exception;
import fr.afcepf.atod.onwine.ws.soap.currency.CurrencyConverterService;
import fr.afcepf.atod.onwine.ws.soap.currency.ICurrencyConverter;
import fr.afcepf.atod.onwine.ws.soap.delivery.DeliveriesWSException_Exception;
import fr.afcepf.atod.onwine.ws.soap.delivery.DeliveryCalculatorService;
import fr.afcepf.atod.onwine.ws.soap.delivery.IDeliveryCalculator;
import fr.afcepf.atod.onwine.ws.soap.orchestre.OnWineServicesOrchestrator;
import fr.afcepf.atod.onwine.ws.soap.orchestre.OnWineServicesOrchestratorPortType;
import fr.afcepf.atod.onwine.ws.soap.orchestre.OnWineServicesOrchestratorRequest;
import fr.afcepf.atod.onwine.ws.soap.orchestre.OnWineServicesOrchestratorResponse;
import fr.afcepf.atod.onwine.ws.soap.tax.ServiceTax;
import fr.afcepf.atod.onwine.ws.soap.tax.ServiceTaxBeanService;
import fr.afcepf.atod.onwine.ws.soap.tax.TaxWSException_Exception;
import fr.afcepf.atod.util.SingletonSessionOrderTemp;
import fr.afcepf.atod.util.UtilConverter;
import fr.afcepf.atod.util.UtilDefParam;
import fr.afcepf.atod.util.UtilFindPath;
import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.business.order.api.IBuOrder;
import fr.afcepf.atod.wine.entity.Adress;
import fr.afcepf.atod.wine.entity.Customer;
import fr.afcepf.atod.wine.entity.Order;
import fr.afcepf.atod.wine.entity.OrderDetail;
import fr.afcepf.atod.wine.entity.PaymentInfo;
import fr.afcepf.atod.wine.entity.Product;
import fr.afcepf.atod.wine.entity.ShippingMethod;

@SessionScoped
@ManagedBean(name = "mBeanCartManagement")
public class MBeanCartManagement implements Serializable {

    private static final long serialVersionUID = -2317461571703883416L;
    // temporary
    private static Logger log = Logger.getLogger(MBeanCartManagement.class);
    // create a new command if necessary or 
    private Order order = SingletonSessionOrderTemp.getInstance().getOrder();
    // set transforme en list
    private List<OrderDetail> listOrderDetails;

    // global error adding product
    private String errorAddProduct;
    @ManagedProperty(value = "#{mBeanProduct}")
    private MBeanProduct mBeanProduct;
    private Order lastOrder = new Order();
    @ManagedProperty(value = "#{buOrder}")
    private IBuOrder buOrder;
    @ManagedProperty(value = "#{mBeanConnexion}")
    private MBeanConnexion mBeanConnexion;
    private boolean validOrder;
    private Customer customer = new Customer();
    @ManagedProperty(value="#{mBeanMail}")
    private MBeanMail mBeanMail;    
    private String deliveryTransporter;
    private String currencyWill;
    private String finalAmount;

    public MBeanCartManagement() {
        super();
        errorAddProduct = "";
        validOrder = false;
    }
    
    /**
     *
     * @param product
     * @return
     */
    @SuppressWarnings("unchecked")
    public String addProductCart() {
        String page = null;
        validOrder = false;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        String currency = (String) sessionMap.get("currency");
        String id = UtilDefParam.getProductParam(FacesContext.getCurrentInstance());
        Product product = null;
        try {
            product = mBeanProduct.getBuProduct()
                    .findById(Integer.parseInt(id));
        } catch (NumberFormatException e) {         
            e.printStackTrace();
        } catch (WineException e) {         
            e.printStackTrace();
        }
        product.setConvertedPrice(product.getPrice());
        if (currency != null) {
            convertCurrencyInObj(product, currency);
        }
        if (!product.getName().equalsIgnoreCase("")
                && product.getPrice() >= 0
                && product.getProductSuppliers() != null) {
            try {
                if (order == null || order.getPaidAt()!=null) {
                    order = new Order();
                    order.setCreatedAt(new Date());
                    order.setPaidAt(null);

                }
                order = buOrder.addItemCart(order, product);
                listOrderDetails = UtilConverter.retrieveListAsSet(order.getOrdersDetail());
                page = UtilFindPath.findURLPath("basket.jsf");
                return page;
            } catch (WineException ex) {
                errorAddProduct = "Product not available, stock empty";
            }
            if (order.getOrdersDetail().isEmpty()) {
                errorAddProduct = "Product not available, stock empty";
            }
        } else {
            errorAddProduct = "Product not available, stock empty";
        }
        return page;
    }
    
    private void convertCurrencyInObj(Product prod, String currency) {

        ICurrencyConverter client = (ICurrencyConverter) (new CurrencyConverterService()).getCurrencyConverterPort();
        try {
            log.info("=============================Conversion=========================");
            log.info(prod.getPrice());
            prod.setConvertedPrice(client.convert(prod.getPrice(), "EUR", currency));
            log.info(prod.getConvertedPrice());
        } catch (CurrenciesWSException_Exception paramE) {
            paramE.printStackTrace();
        }
    }


    /**
     * supprimer une ligne de commande
     *
     * @param orderDetail
     */
    public String removeProductCart(OrderDetail orderDetail) {
        String page = null;
        if (!order.getOrdersDetail().isEmpty()) {
            listOrderDetails.remove(orderDetail);
            Set<OrderDetail> set = UtilConverter.retrieveSetAsList(listOrderDetails);
            order.setOrdersDetail(set);
        }
        return "#?faces-redirect=true";
    }

    /**
     *
     * @param orderDetail
     * @return
     */
    public double calculDiscount(OrderDetail orderDetail) {
//      df.setMaximumFractionDigits ( 2 ) ; //arrondi à 2 chiffres apres la virgules 
        double discount = 0.0;
        double prix = 0.0;
        double pourcentage = 0.0;
        if (orderDetail != null
                && orderDetail.getProductOrdered()
                .getSpeEvent()!= null) 
        {
            prix = orderDetail.getProductOrdered().getConvertedPrice();
            pourcentage = orderDetail.getProductOrdered()
                    .getSpeEvent().getPourcentage();
            discount = (double)Math.round((prix/100.0 * pourcentage)*100d)/100d;
//          Double.parseDouble(df.format(discount));
            
        }
        return discount ;
    }

    /**
     *
     * @param orderDetail
     * @return
     */
    public double calculTotalLine(OrderDetail orderDetail) {
        double totalLine = 0.0;
        if (orderDetail != null) {
            totalLine = orderDetail.getQuantite()
                    * (orderDetail.getProductOrdered().getConvertedPrice() - calculDiscount(orderDetail));
        }
        return (double)Math.round(totalLine*100d)/100d;
    }

    /**
     * @return
     */
    public double calculSubTotal() {
        double subTotal = 0.0;
        if (!order.getOrdersDetail().isEmpty()) {
            for (OrderDetail o : order.getOrdersDetail()) {
                subTotal = subTotal + calculTotalLine(o);
            }
        }

        return (double)Math.round(subTotal*100d)/100d;
    }

    /**
     * quantite total des articles dans le panier
     *
     * @return
     */
    public int calculerNumTotalQantity() {
        int numTotalQuantity = 0;
        if (order != null && order.getCreatedAt() != null 
                && order.getPaidAt() == null) {
            for (OrderDetail o : this.order.getOrdersDetail()) {
                numTotalQuantity = numTotalQuantity + o.getQuantite();
            }
        }
        return numTotalQuantity;
    }
    
    
    public double callDelivery() throws DeliveriesWSException_Exception {
        double delivery = 0.0;
        System.out.println("---------------------"+deliveryTransporter+"---------------------------");
        if (mBeanConnexion.getUserConnected().getId() != null && order.getOrdersDetail().size()!=0) {
            IDeliveryCalculator client = (new DeliveryCalculatorService()).getDeliveryCalculatorPort();
            List<Adress> ads = mBeanConnexion.getUserConnected().getAdresses();
            for (Adress adress : ads) {
                if (!adress.isBilling()) {
                    if(deliveryTransporter!=null && deliveryTransporter.equals("chronopost")) {
                        delivery = client.getInternationalRateDelivery(adress.getCountry().getCode(), calculerNumTotalQantity());
                    } else {
                        delivery = client.getRateDeliveryTotal(adress.getCountry().getCode(), calculerNumTotalQantity());
                    }
                    System.out.println("---------------------"+delivery+"---------------------------");
                }
            }
//            ServiceTax client =  (new ServiceTaxBeanService()).getServiceTaxBeanPort();
//            List<Adress> ads = mBeanConnexion.getUserConnected().getAdresses();
//            for (Adress adress : ads) {
//                if (adress.isBilling()) {
//                    taxPays = client.calculTax(calculSubTotal(), adress.getCountry().getCode());
//                }
//            }
        }
        return delivery;
        
    }

    /**
     * Calculer frais transport mode livaison colissomo
 
     * @param orderDetail
     * @return
     */
    public double caclulShippingFree() {
        double shipping = 0.0;
//      if (calculerNumTotalQantity() != 0.0 /*& order.getShippingMethod().getId()==1*/) {
//          return 1.5;
//      }
        
        return (double)Math.round(shipping*100d)/100d;
    }
    
        
    public double calculTaxPays() throws TaxWSException_Exception {
        double taxPays = 0.0;
        if (mBeanConnexion.getUserConnected().getId() != null && order.getOrdersDetail().size()!=0) {
            ServiceTax client =  (new ServiceTaxBeanService()).getServiceTaxBeanPort();
            List<Adress> ads = mBeanConnexion.getUserConnected().getAdresses();
            for (Adress adress : ads) {
                if (adress.isBilling()) {
                    taxPays = client.calculTax(calculSubTotal(), adress.getCountry().getCode());
                }
            }
        }
        return taxPays;
    }

    /**
     * Calculer le total de la commande: total articles + frais transport
     *
     * @param orderDetail
     * @return
     */
    public double calculTotal() {
        double subtotal = 0.0;
        double result = 0.0;
        for (OrderDetail o : order.getOrdersDetail()) {
            subtotal = subtotal + calculTotalLine(o);
        }
        try {
            result=(double)Math.round((subtotal + callDelivery() + calculTaxPays())*100d)/100d;
        } catch (DeliveriesWSException_Exception paramE) {
            // TODO Auto-generated catch block
            paramE.printStackTrace();
        } catch (TaxWSException_Exception paramE) {
            // TODO Auto-generated catch block
            paramE.printStackTrace();
        }
        return result;
    }


    /**
     *verifier si le customer est connecté
     *si oui creer date order et diriger vers page valide adresse
     *sinon direger vers page register
     **/
    public String validePanier(){
        String page = null;
        if (mBeanConnexion.getUserConnected().getId() != null && order.getOrdersDetail().size()!=0) {
            order.setCustomer((Customer)mBeanConnexion.getUserConnected());
            order.setCreatedAt(new Date());
            page = "/pages/checkout1adress.jsf?faces-redirect=true";
        } else {
            page ="/pages/register.jsf?faces-redirect=true";
        }
        return page;
    }
    /**
     *customer non connecté, soit s'inscrire soit connecter a partir du register 
     *pour valider le panier et direger vers valide adresse
     **/

    public String connectedGoToCheckout1(){
        String page = null;

        if(mBeanConnexion.connect()!=null){
            order.setCustomer((Customer)mBeanConnexion.getUserConnected());
            page ="/pages/checkout1adress.jsf?faces-redirect=true";
        }
        return page;
    }

    /**
     * valider adresse livraison et direger vers la page paiement
     * */
    public String validerAdresse(){
        String page = null;
        if(!order.getCustomer().getAdresses().isEmpty() 
                && order.getOrdersDetail().size()!=0){
            //order.getCustomer().setAdress(adress);
            page ="/pages/checkout2livraison.jsf?faces-redirect=true";
        }
        return page;
    }
    /**
     * valider mode de livraison en colissomo et direger vers la page paiement
     * 
     * */
    public String validerLivraison(){
        String page = null;

        if(order.getCustomer().getId()!=null & order.getOrdersDetail().size()!=0){
            order.setShippingMethod(new ShippingMethod(1, "Colissomo"));
            page = "/pages/checkout3payment.jsf?faces-redirect=true";
        }
        return page;
    }

    /**
     * valider mode de paiement et date paiement, Ajouter une nouvelle commande a la base
     *apres l'etape validePanier, valide adress, valide transport, valide paiement
     * @param orderDetail
     * @return
     */
    public String addNewOrder() {
        String page = null;
        if (mBeanConnexion.getUserConnected().getId() != null && order.getCreatedAt()!=null 
                && order.getShippingMethod()!=null && order.getOrdersDetail().size()!=0) {          
            try {
                invokeOrchestrator();
                order.setPaidAt(new Date());
                order.setPaymentInfo(new PaymentInfo(1, "visa"));
                buOrder.addNewOrder(order);
                validOrder = true;
                getLastOrder(customer);             
                page = "/pages/checkout4confirmation.jsf?faces-redirect=true";
            } catch (WineException e) {
                e.printStackTrace();
            }
        } 
        return page;
    }
    
    private String invokeOrchestrator() {
        finalAmount = "0";
        OnWineServicesOrchestratorPortType proxy = new OnWineServicesOrchestrator().getOnWineServicesOrchestratorPort();
        BindingProvider bp = (BindingProvider)proxy;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://192.168.102.42:9090/ode/processes/OnWineServicesOrchestrator");
        OnWineServicesOrchestratorRequest request = new OnWineServicesOrchestratorRequest();
           for (Adress adress : mBeanConnexion.getUserConnected().getAdresses()) {
               if(adress.isBilling()) {
                   request.setCountryCodeBilling(adress.getCountry().getCode());
               } else {
                   request.setCountryCodeShipping(adress.getCountry().getCode());
               }
            }
        if (!order.getOrdersDetail().isEmpty()) {
           double HT = 0.0;
           Integer tQ = 0;
           for (OrderDetail o : order.getOrdersDetail()) {
               double prix = o.getQuantite() * ((double) Math.round(o.getProductOrdered().getPrice()*100d)/100d);
               if(o.getProductOrdered().getSpeEvent()!=null) { 
                   double discount = (double)Math.round((prix/100.0 * o.getProductOrdered().getSpeEvent().getPourcentage())*100d)/100d;
                   HT = HT + (prix - discount);
               } else {
                   HT = HT + prix;
               }
               tQ += o.getQuantite();
           }
            request.setAmount(HT);
            request.setSrcCurrency("EUR");
            request.setTrgtCurrency(currencyWill);
            request.setQuantity(new BigInteger(tQ.toString()));
            request.setTransporterName(deliveryTransporter);
            OnWineServicesOrchestratorResponse response = proxy.process(request);
            finalAmount = response.getResult();
        }
        return finalAmount;
    }

    /**
     * recuperer le dernier commande de client qui viens de passer pour le recap confirmation
     * */
    public Order getLastOrder(Customer customer){
        customer = (Customer) mBeanConnexion.getUserConnected();
        lastOrder = buOrder.getLastOrderByCustomer(customer);
        mBeanMail.sendFacture(lastOrder);
        return lastOrder;
    }    

    public void initializeOrder(){
        validOrder = true;      
    }


    //  ######################################################## //
    /**
     * ********************************************************
     * Methode pour initialiser le panier pour faire le parcours
     * panier/validation paiement/.
     * *
     * @return *******************************************************
     */


    public Order getOrder() {
        return order;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order getLastOrder() {
        return lastOrder;
    }

    public void setLastOrder(Order lastOrder) {
        this.lastOrder = lastOrder;
    }

    public List<OrderDetail> getListOrderDetails() {
        return listOrderDetails;
    }

    public void setListOrderDetails(List<OrderDetail> listOrderDetails) {
        this.listOrderDetails = listOrderDetails;
    }

    public MBeanConnexion getmBeanConnexion() {
        return mBeanConnexion;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getErrorAddProduct() {
        return errorAddProduct;
    }

    public void setErrorAddProduct(String errorAddProduct) {
        this.errorAddProduct = errorAddProduct;
    }

    public IBuOrder getBuOrder() {
        return buOrder;
    }

    public void setBuOrder(IBuOrder buOrder) {
        this.buOrder = buOrder;
    }

    public boolean isValidOrder() {
        return validOrder;
    }

    public void setValidOrder(boolean validOrder) {
        this.validOrder = validOrder;
    }

    public MBeanProduct getmBeanProduct() {
        return mBeanProduct;
    }

    public void setmBeanProduct(MBeanProduct mBeanProduct) {
        this.mBeanProduct = mBeanProduct;
    }
 
    public void setmBeanConnexion(MBeanConnexion mBeanConnexion) {
        this.mBeanConnexion = mBeanConnexion;
    }

    public MBeanMail getmBeanMail() {
        return mBeanMail;
    }

    public void setmBeanMail(MBeanMail mBeanMail) {
        this.mBeanMail = mBeanMail;
    }

    public String getDeliveryTransporter() {
        return deliveryTransporter;
    }

    public void setDeliveryTransporter(String paramDeliveryTransporter) {
        deliveryTransporter = paramDeliveryTransporter;
    }

    public String getCurrencyWill() {
        return currencyWill;
    }

    public void setCurrencyWill(String paramCurrencyWill) {
        currencyWill = paramCurrencyWill;
    }

    public String getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(String paramFinalAmount) {
        finalAmount = paramFinalAmount;
    }
    
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.mbeans.mbeanorder;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.xml.ws.BindingProvider;

import org.apache.log4j.Logger;

import fr.afcepf.atod.mbeans.mbeanproduct.MBeanProduct;
import fr.afcepf.atod.mbeans.mbeanuser.MBeanConnexion;
import fr.afcepf.atod.mbeans.mbeanuser.MBeanMail;
import fr.afcepf.atod.onwine.ws.soap.currency.CurrenciesWSException_Exception;
import fr.afcepf.atod.onwine.ws.soap.currency.CurrencyConverterService;
import fr.afcepf.atod.onwine.ws.soap.currency.ICurrencyConverter;
import fr.afcepf.atod.onwine.ws.soap.delivery.DeliveriesWSException_Exception;
import fr.afcepf.atod.onwine.ws.soap.delivery.DeliveryCalculatorService;
import fr.afcepf.atod.onwine.ws.soap.delivery.IDeliveryCalculator;
import fr.afcepf.atod.onwine.ws.soap.orchestre.OnWineServicesOrchestrator;
import fr.afcepf.atod.onwine.ws.soap.orchestre.OnWineServicesOrchestratorPortType;
import fr.afcepf.atod.onwine.ws.soap.orchestre.OnWineServicesOrchestratorRequest;
import fr.afcepf.atod.onwine.ws.soap.orchestre.OnWineServicesOrchestratorResponse;
import fr.afcepf.atod.onwine.ws.soap.tax.ServiceTax;
import fr.afcepf.atod.onwine.ws.soap.tax.ServiceTaxBeanService;
import fr.afcepf.atod.onwine.ws.soap.tax.TaxWSException_Exception;
import fr.afcepf.atod.util.SingletonSessionOrderTemp;
import fr.afcepf.atod.util.UtilConverter;
import fr.afcepf.atod.util.UtilDefParam;
import fr.afcepf.atod.util.UtilFindPath;
import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.business.order.api.IBuOrder;
import fr.afcepf.atod.wine.entity.Adress;
import fr.afcepf.atod.wine.entity.Customer;
import fr.afcepf.atod.wine.entity.Order;
import fr.afcepf.atod.wine.entity.OrderDetail;
import fr.afcepf.atod.wine.entity.PaymentInfo;
import fr.afcepf.atod.wine.entity.Product;
import fr.afcepf.atod.wine.entity.ShippingMethod;

@SessionScoped
@ManagedBean(name = "mBeanCartManagement")
public class MBeanCartManagement implements Serializable {

	private static final long serialVersionUID = -2317461571703883416L;
	// temporary
	private static Logger log = Logger.getLogger(MBeanCartManagement.class);
	// create a new command if necessary or 
	private Order order = SingletonSessionOrderTemp.getInstance().getOrder();
	// set transforme en list
	private List<OrderDetail> listOrderDetails;

	// global error adding product
	private String errorAddProduct;
	@ManagedProperty(value = "#{mBeanProduct}")
	private MBeanProduct mBeanProduct;
	private Order lastOrder = new Order();
	@ManagedProperty(value = "#{buOrder}")
	private IBuOrder buOrder;
	@ManagedProperty(value = "#{mBeanConnexion}")
	private MBeanConnexion mBeanConnexion;
	private boolean validOrder;
	private Customer customer = new Customer();
	@ManagedProperty(value="#{mBeanMail}")
	private MBeanMail mBeanMail;	
	private String deliveryTransporter;
	private String currencyWill;
	private String finalAmount;

	public MBeanCartManagement() {
		super();
		errorAddProduct = "";
		validOrder = false;
	}
    
    /**
	 *
	 * @param product
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String addProductCart() {
		String page = null;
		validOrder = false;
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
        String currency = (String) sessionMap.get("currency");
		String id = UtilDefParam.getProductParam(FacesContext.getCurrentInstance());
		Product product = null;
		try {
			product = mBeanProduct.getBuProduct()
					.findById(Integer.parseInt(id));
		} catch (NumberFormatException e) {			
			e.printStackTrace();
		} catch (WineException e) {			
			e.printStackTrace();
		}
		product.setConvertedPrice(product.getPrice());
		if (currency != null) {
		    convertCurrencyInObj(product, currency);
		}
		if (!product.getName().equalsIgnoreCase("")
				&& product.getPrice() >= 0
				&& product.getProductSuppliers() != null) {
			try {
				if (order == null || order.getPaidAt()!=null) {
					order = new Order();
					order.setCreatedAt(new Date());
					order.setPaidAt(null);

				}
				order = buOrder.addItemCart(order, product);
				listOrderDetails = UtilConverter.retrieveListAsSet(order.getOrdersDetail());
				page = UtilFindPath.findURLPath("basket.jsf");
				return page;
			} catch (WineException ex) {
				errorAddProduct = "Product not available, stock empty";
			}
			if (order.getOrdersDetail().isEmpty()) {
				errorAddProduct = "Product not available, stock empty";
			}
		} else {
			errorAddProduct = "Product not available, stock empty";
		}
		return page;
	}
	
	private void convertCurrencyInObj(Product prod, String currency) {

        ICurrencyConverter client = (ICurrencyConverter) (new CurrencyConverterService()).getCurrencyConverterPort();
        try {
            log.info("=============================Conversion=========================");
            log.info(prod.getPrice());
            prod.setConvertedPrice(client.convert(prod.getPrice(), "EUR", currency));
            log.info(prod.getConvertedPrice());
        } catch (CurrenciesWSException_Exception paramE) {
            paramE.printStackTrace();
        }
    }


	/**
	 * supprimer une ligne de commande
	 *
	 * @param orderDetail
	 */
	public String removeProductCart(OrderDetail orderDetail) {
		String page = null;
		if (!order.getOrdersDetail().isEmpty()) {
			listOrderDetails.remove(orderDetail);
			Set<OrderDetail> set = UtilConverter.retrieveSetAsList(listOrderDetails);
			order.setOrdersDetail(set);
		}
		return "#?faces-redirect=true";
	}

	/**
	 *
	 * @param orderDetail
	 * @return
	 */
	public double calculDiscount(OrderDetail orderDetail) {
//		df.setMaximumFractionDigits ( 2 ) ; //arrondi à 2 chiffres apres la virgules 
		double discount = 0.0;
		double prix = 0.0;
		double pourcentage = 0.0;
		if (orderDetail != null
				&& orderDetail.getProductOrdered()
				.getSpeEvent()!= null) 
		{
			prix = orderDetail.getProductOrdered().getConvertedPrice();
			pourcentage = orderDetail.getProductOrdered()
					.getSpeEvent().getPourcentage();
			discount = (double)Math.round((prix/100.0 * pourcentage)*100d)/100d;
//			Double.parseDouble(df.format(discount));
			
		}
		return discount ;
	}

	/**
	 *
	 * @param orderDetail
	 * @return
	 */
	public double calculTotalLine(OrderDetail orderDetail) {
		double totalLine = 0.0;
		if (orderDetail != null) {
			totalLine = orderDetail.getQuantite()
					* (orderDetail.getProductOrdered().getConvertedPrice() - calculDiscount(orderDetail));
		}
		return (double)Math.round(totalLine*100d)/100d;
	}

	/**
	 * @return
	 */
	public double calculSubTotal() {
		double subTotal = 0.0;
		if (!order.getOrdersDetail().isEmpty()) {
			for (OrderDetail o : order.getOrdersDetail()) {
				subTotal = subTotal + calculTotalLine(o);
			}
		}

		return (double)Math.round(subTotal*100d)/100d;
	}

	/**
	 * quantite total des articles dans le panier
	 *
	 * @return
	 */
	public int calculerNumTotalQantity() {
		int numTotalQuantity = 0;
		if (order != null && order.getCreatedAt() != null 
				&& order.getPaidAt() == null) {
			for (OrderDetail o : this.order.getOrdersDetail()) {
				numTotalQuantity = numTotalQuantity + o.getQuantite();
			}
		}
		return numTotalQuantity;
	}
	
	
	public double callDelivery() throws DeliveriesWSException_Exception {
	    double delivery = 0.0;
	    System.out.println("---------------------"+deliveryTransporter+"---------------------------");
	    if (mBeanConnexion.getUserConnected().getId() != null && order.getOrdersDetail().size()!=0) {
	        IDeliveryCalculator client = (new DeliveryCalculatorService()).getDeliveryCalculatorPort();
	        List<Adress> ads = mBeanConnexion.getUserConnected().getAdresses();
	        for (Adress adress : ads) {
	            if (!adress.isBilling()) {
	                if(deliveryTransporter!=null && deliveryTransporter.equals("chronopost")) {
	                    delivery = client.getInternationalRateDelivery(adress.getCountry().getCode(), calculerNumTotalQantity());
	                } else {
	                    delivery = client.getRateDeliveryTotal(adress.getCountry().getCode(), calculerNumTotalQantity());
	                }
	                System.out.println("---------------------"+delivery+"---------------------------");
	            }
	        }
//            ServiceTax client =  (new ServiceTaxBeanService()).getServiceTaxBeanPort();
//            List<Adress> ads = mBeanConnexion.getUserConnected().getAdresses();
//            for (Adress adress : ads) {
//                if (adress.isBilling()) {
//                    taxPays = client.calculTax(calculSubTotal(), adress.getCountry().getCode());
//                }
//            }
        }
        return delivery;
        
    }

	/**
	 * Calculer frais transport mode livaison colissomo
 
	 * @param orderDetail
	 * @return
	 */
	public double caclulShippingFree() {
		double shipping = 0.0;
//		if (calculerNumTotalQantity() != 0.0 /*& order.getShippingMethod().getId()==1*/) {
//		    return 1.5;
//		}
		
		return (double)Math.round(shipping*100d)/100d;
	}
	
		
	public double calculTaxPays() throws TaxWSException_Exception {
		double taxPays = 0.0;
		if (mBeanConnexion.getUserConnected().getId() != null && order.getOrdersDetail().size()!=0) {
			ServiceTax client =  (new ServiceTaxBeanService()).getServiceTaxBeanPort();
			List<Adress> ads = mBeanConnexion.getUserConnected().getAdresses();
			for (Adress adress : ads) {
				if (adress.isBilling()) {
					taxPays = client.calculTax(calculSubTotal(), adress.getCountry().getCode());
				}
			}
		}
		return taxPays;
	}

	/**
	 * Calculer le total de la commande: total articles + frais transport
	 *
	 * @param orderDetail
	 * @return
	 */
	public double calculTotal() {
		double subtotal = 0.0;
		double result = 0.0;
		for (OrderDetail o : order.getOrdersDetail()) {
			subtotal = subtotal + calculTotalLine(o);
		}
		try {
            result=(double)Math.round((subtotal + callDelivery() + calculTaxPays())*100d)/100d;
        } catch (DeliveriesWSException_Exception paramE) {
            // TODO Auto-generated catch block
            paramE.printStackTrace();
        } catch (TaxWSException_Exception paramE) {
            // TODO Auto-generated catch block
            paramE.printStackTrace();
        }
		return result;
	}


	/**
	 *verifier si le customer est connecté
	 *si oui creer date order et diriger vers page valide adresse
	 *sinon direger vers page register
	 **/
	public String validePanier(){
		String page = null;
		if (mBeanConnexion.getUserConnected().getId() != null && order.getOrdersDetail().size()!=0) {
			order.setCustomer((Customer)mBeanConnexion.getUserConnected());
			order.setCreatedAt(new Date());
			page = "/pages/checkout1adress.jsf?faces-redirect=true";
		} else {
			page ="/pages/register.jsf?faces-redirect=true";
		}
		return page;
	}
	/**
	 *customer non connecté, soit s'inscrire soit connecter a partir du register 
	 *pour valider le panier et direger vers valide adresse
	 **/

	public String connectedGoToCheckout1(){
		String page = null;

		if(mBeanConnexion.connect()!=null){
			order.setCustomer((Customer)mBeanConnexion.getUserConnected());
			page ="/pages/checkout1adress.jsf?faces-redirect=true";
		}
		return page;
	}

	/**
	 * valider adresse livraison et direger vers la page paiement
	 * */
	public String validerAdresse(){
		String page = null;
		if(!order.getCustomer().getAdresses().isEmpty() 
				&& order.getOrdersDetail().size()!=0){
			//order.getCustomer().setAdress(adress);
			page ="/pages/checkout2livraison.jsf?faces-redirect=true";
		}
		return page;
	}
	/**
	 * valider mode de livraison en colissomo et direger vers la page paiement
	 * 
	 * */
	public String validerLivraison(){
		String page = null;

		if(order.getCustomer().getId()!=null & order.getOrdersDetail().size()!=0){
			order.setShippingMethod(new ShippingMethod(1, "Colissomo"));
			page = "/pages/checkout3payment.jsf?faces-redirect=true";
		}
		return page;
	}

	/**
	 * valider mode de paiement et date paiement, Ajouter une nouvelle commande a la base
	 *apres l'etape validePanier, valide adress, valide transport, valide paiement
	 * @param orderDetail
	 * @return
	 */
	public String addNewOrder() {
		String page = null;
		if (mBeanConnexion.getUserConnected().getId() != null && order.getCreatedAt()!=null 
				&& order.getShippingMethod()!=null && order.getOrdersDetail().size()!=0) {        	
			try {
			    invokeOrchestrator();
			    order.setPaidAt(new Date());
				order.setPaymentInfo(new PaymentInfo(1, "visa"));
				buOrder.addNewOrder(order);
				validOrder = true;
				getLastOrder(customer);				
				page = "/pages/checkout4confirmation.jsf?faces-redirect=true";
			} catch (WineException e) {
				e.printStackTrace();
			}
		} 
		return page;
	}
	
	private String invokeOrchestrator() {
	    finalAmount = "0";
    	OnWineServicesOrchestratorPortType proxy = new OnWineServicesOrchestrator().getOnWineServicesOrchestratorPort();
        BindingProvider bp = (BindingProvider)proxy;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://192.168.102.42:9090/ode/processes/OnWineServicesOrchestrator");
        OnWineServicesOrchestratorRequest request = new OnWineServicesOrchestratorRequest();
           for (Adress adress : mBeanConnexion.getUserConnected().getAdresses()) {
               if(adress.isBilling()) {
                   request.setCountryCodeBilling(adress.getCountry().getCode());
               } else {
                   request.setCountryCodeShipping(adress.getCountry().getCode());
               }
            }
        if (!order.getOrdersDetail().isEmpty()) {
           double HT = 0.0;
           Integer tQ = 0;
           for (OrderDetail o : order.getOrdersDetail()) {
               double prix = o.getQuantite() * ((double) Math.round(o.getProductOrdered().getPrice()*100d)/100d);
               if(o.getProductOrdered().getSpeEvent()!=null) { 
                   double discount = (double)Math.round((prix/100.0 * o.getProductOrdered().getSpeEvent().getPourcentage())*100d)/100d;
                   HT = HT + (prix - discount);
               } else {
                   HT = HT + prix;
               }
               tQ += o.getQuantite();
           }
            request.setAmount(HT);
            request.setSrcCurrency("EUR");
            request.setTrgtCurrency(currencyWill);
            request.setQuantity(new BigInteger(tQ.toString()));
            request.setTransporterName(deliveryTransporter);
            OnWineServicesOrchestratorResponse response = proxy.process(request);
            finalAmount = response.getResult();
        }
        return finalAmount;
    }

	/**
	 * recuperer le dernier commande de client qui viens de passer pour le recap confirmation
	 * */
	public Order getLastOrder(Customer customer){
		customer = (Customer) mBeanConnexion.getUserConnected();
		lastOrder = buOrder.getLastOrderByCustomer(customer);
		mBeanMail.sendFacture(lastOrder);
		return lastOrder;
	}    

	public void initializeOrder(){
		validOrder = true;		
	}


	//  ######################################################## //
	/**
	 * ********************************************************
	 * Methode pour initialiser le panier pour faire le parcours
	 * panier/validation paiement/.
	 * *
	 * @return *******************************************************
	 */


	public Order getOrder() {
		return order;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Order getLastOrder() {
		return lastOrder;
	}

	public void setLastOrder(Order lastOrder) {
		this.lastOrder = lastOrder;
	}

	public List<OrderDetail> getListOrderDetails() {
		return listOrderDetails;
	}

	public void setListOrderDetails(List<OrderDetail> listOrderDetails) {
		this.listOrderDetails = listOrderDetails;
	}

	public MBeanConnexion getmBeanConnexion() {
		return mBeanConnexion;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getErrorAddProduct() {
		return errorAddProduct;
	}

	public void setErrorAddProduct(String errorAddProduct) {
		this.errorAddProduct = errorAddProduct;
	}

	public IBuOrder getBuOrder() {
		return buOrder;
	}

	public void setBuOrder(IBuOrder buOrder) {
		this.buOrder = buOrder;
	}

	public boolean isValidOrder() {
		return validOrder;
	}

	public void setValidOrder(boolean validOrder) {
		this.validOrder = validOrder;
	}

	public MBeanProduct getmBeanProduct() {
		return mBeanProduct;
	}

	public void setmBeanProduct(MBeanProduct mBeanProduct) {
		this.mBeanProduct = mBeanProduct;
	}
 
	public void setmBeanConnexion(MBeanConnexion mBeanConnexion) {
		this.mBeanConnexion = mBeanConnexion;
	}

	public MBeanMail getmBeanMail() {
		return mBeanMail;
	}

	public void setmBeanMail(MBeanMail mBeanMail) {
		this.mBeanMail = mBeanMail;
	}

    public String getDeliveryTransporter() {
        return deliveryTransporter;
    }

    public void setDeliveryTransporter(String paramDeliveryTransporter) {
        deliveryTransporter = paramDeliveryTransporter;
    }

    public String getCurrencyWill() {
        return currencyWill;
    }

    public void setCurrencyWill(String paramCurrencyWill) {
        currencyWill = paramCurrencyWill;
    }

    public String getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(String paramFinalAmount) {
        finalAmount = paramFinalAmount;
    }
    
>>>>>>> refs/heads/master
    
}
