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
import fr.afcepf.atod.wine.entity.Order;
import fr.afcepf.atod.wine.entity.OrderDetail;
import fr.afcepf.atod.wine.entity.Product;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author ronan
 */
/**
 * @author fen
 *
 */
@SessionScoped
@ManagedBean(name = "mBeanCartManagement")
public class MBeanCartManagement implements Serializable {

    private static final long serialVersionUID = -2317461571703883416L;
    // temporary
    private static Logger log
            = Logger.getLogger(MBeanCartManagement.class);
    // create a new command if necessary or 
    private Order order = SingletonSessionOrderTemp.getInstance().getOrder();
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
        return orderDetail.getProductOrdered().getPrice()
                * (orderDetail.getProductOrdered()
                .getSpeEvent().getPourcentage() / 100);
    }

    /**
     *
     * @param orderDetail
     * @return
     */
    public double calculTotalLine(OrderDetail orderDetail) {
        return orderDetail.getQuantite()
                * (orderDetail.getProductOrdered().getPrice() - calculDiscount(orderDetail));
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

        return subTotal;
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
        return numTotalQuantity;
    }

    /**
     * Calculer frais transport
     *
     * @param orderDetail
     * @return
     */
    public double caclulShippingFree() {
        double shipping = 0.0;
        if (calculerNumTotalQantity() != 0.0) {
            shipping = calculerNumTotalQantity() * 0.5;
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
        return subtotal + caclulShippingFree();
    }

    /**
     * Ajouter une nouvelle commande a la base
     *
     * @param orderDetail
     * @return
     */
    public String addNewOrder(Order o) {
        String suite = null;
        if (mBeanConnexion.getUserConnected().getId() != null && mBeanConnexion.getUserConnected().getFirstname() != null) {
            /*try {
				buOrder.addNewOrder(o);
				suite = "checkout1adress.xhtml";
			} catch (WineException e) {
				e.printStackTrace();
			}*/
        } else {
            suite = "register.xhtml";
        }
        return suite;
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

    /*
    public int getNumTotalQuantity() {
		return numTotalQuantity;
	}



	public void setNumTotalQuantity(int numTotalQuantity) {
		this.numTotalQuantity = numTotalQuantity;
	} */
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
