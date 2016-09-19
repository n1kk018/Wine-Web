/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.mbeans.mbeanorder;

import fr.afcepf.atod.mbeans.mbeanuser.MBeanConnexion;
import fr.afcepf.atod.util.SingletonSessionOrderTemp;
import fr.afcepf.atod.util.UtilConverter;
import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.business.order.api.IBuOrder;
import fr.afcepf.atod.wine.entity.Customer;
import fr.afcepf.atod.wine.entity.Order;
import fr.afcepf.atod.wine.entity.OrderDetail;
import fr.afcepf.atod.wine.entity.PaymentInfo;
import fr.afcepf.atod.wine.entity.Product;
import fr.afcepf.atod.wine.entity.ShippingMethod;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;

@SessionScoped
@ManagedBean(name = "mBeanCartManagement")
public class MBeanCartManagement implements Serializable {

    private static final long serialVersionUID = -2317461571703883416L;
    // temporary
    private static Logger log
            = Logger.getLogger(MBeanCartManagement.class);
    // create a new command if necessary or 
    private Order order = SingletonSessionOrderTemp.getInstance().getOrder();
    // set transforme en list
    private List<OrderDetail> listOrderDetails;

    // global error adding product
    private String errorAddProduct;

    @ManagedProperty(value = "#{buOrder}")
    private IBuOrder buOrder;
    @ManagedProperty(value = "#{mBeanConnexion}")
    private MBeanConnexion mBeanConnexion;

    public void setmBeanConnexion(MBeanConnexion mBeanConnexion) {
        this.mBeanConnexion = mBeanConnexion;
    }

    public MBeanCartManagement() {
        super();
        errorAddProduct = "";
    }

    /**
     *
     * @param product
     * @return
     */
    @SuppressWarnings("unchecked")
    public String addProductCart(Product product) {
        String page = null;
        if (!product.getName().equalsIgnoreCase("")
                && product.getPrice() >= 0
                && !product.getProductSuppliers().isEmpty()) {
            try {
                if (order == null) {
                    order = new Order();
                }
                order = buOrder.addItemCart(order, product);
                listOrderDetails = UtilConverter.retrieveListAsSet(order.getOrdersDetail());

                /*The symptoms indicate that the page was requested by a POST request and that
                you're ignoring the webbrowser's warning that the data will be resent when refreshing
                the request. Refreshing a POST request will of course result in it being re-executed.
                This is not a JSF specific problem.The common solution to that is to send a redirect
                to a GET request after executing the POST request. This way the client will end up
                having the GET request in the browser view. Refreshing this will then only re-execute
                the GET request which doesn't (shouldn't) modify anything (unless you're doing this in
                the constructor of a request scoped bean associated with the view). This is also known
                as the POST-Redirect-GET pattern.With JSF 2.0, you can achieve this by simply adding
                faces-redirect=true parameter to the bean action's outcome.
                
                N.B:1)If you're still using old fashioned <navigation-case>s in faces-config.xml,
                then the same effect can be achieved by adding <redirect/> to the case
                    2) In JSF 2.0+ you could instead use the flash scope for this or to just let
                the POST take place by <f:ajax> submit instead of a normal submit.
                    3) Another method
                 */
                page = "pages/basket.jsf?faces-redirect=true";
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
        double discount = 0.0;
        if (orderDetail != null) {
            discount = orderDetail.getProductOrdered().getPrice()
                    * (orderDetail.getProductOrdered()
                    .getSpeEvent().getPourcentage() / 100);
        }
        return Math.round(discount * 100) / 100;
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
                    * (orderDetail.getProductOrdered().getPrice() - calculDiscount(orderDetail));
        }
        return Math.round(totalLine * 100) / 100;
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

        return Math.round(subTotal*100)/100;
    }

    /**
     * quantite total des articles dans le panier
     *
     * @return
     */
    public int calculerNumTotalQantity() {
        int numTotalQuantity = 0;
        if (order != null && !order.getOrdersDetail().isEmpty()) {
            for (OrderDetail o : this.order.getOrdersDetail()) {
                numTotalQuantity = numTotalQuantity + o.getQuantite();
            }
        }
        return Math.round(numTotalQuantity*100)/100;
    }

    /**
     * Calculer frais transport mode livaison colissomo
     *
     * @param orderDetail
     * @return
     */
    public double caclulShippingFree() {
        double shipping = 0.0;
//        if (calculerNumTotalQantity() != 0.0 & order.getShippingMethod().getId()==1) 
        if (calculerNumTotalQantity() != 0.0) {
            shipping = calculerNumTotalQantity() * 0.75;
        }
        return shipping;
    }

    /**
     * Calculer le total de la commande: total articles + frais transport
     *
     * @param orderDetail
     * @return
     */
    public double calculTotal() {
        double subtotal = 0.0;
        for (OrderDetail o : order.getOrdersDetail()) {
            subtotal = subtotal + calculTotalLine(o);
        }
        return Math.round((subtotal + caclulShippingFree())*100)/100;
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
 			order.setCreatedAt(new Date());
    		page ="/pages/checkout1adress.jsf?faces-redirect=true";
    	}
    	return page;
    }
    
    /**
     * valider adresse livraison et direger vers la page paiement
     * */
    public String validerAdresse(){
    	String page = null;
    	if(order.getCustomer().getAdress()!=null & order.getOrdersDetail().size()!=0){
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
    	log.info("****************************************** add order debut********************************");
        String page = null;
        if (mBeanConnexion.getUserConnected().getId() != null && order.getCreatedAt()!=null 
        		&& order.getShippingMethod()!=null && order.getOrdersDetail().size()!=0) {
        	log.info("****************************************** add order deja connecter********************************");
            try {
            	order.setPaidAt(new Date());
            	order.setPaymentInfo(new PaymentInfo(1, "visa"));
				buOrder.addNewOrder(order);
				page = "/pages/checkout4confirmation.jsf?faces-redirect=true";
			} catch (WineException e) {
				e.printStackTrace();
			}
        } else {
        	log.info("****************************************** pas ajouter********************************");
        }
        return page;
    }
     
    
    //  ######################################################## //
    /**
     * ********************************************************
     * Methode pour initialiser le panier pour faire le parcours
     * panier/validation paiement/.
     * ********************************************************
     */
    public Order getOrder() {
        return order;
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

}
