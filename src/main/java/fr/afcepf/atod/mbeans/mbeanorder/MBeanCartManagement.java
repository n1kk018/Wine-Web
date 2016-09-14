/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.mbeans.mbeanorder;

import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.business.order.api.IBuOrder;
import fr.afcepf.atod.wine.entity.Order;
import fr.afcepf.atod.wine.entity.OrderDetail;
import fr.afcepf.atod.wine.entity.Product;
import fr.afcepf.atod.wine.entity.ProductWine;
import fr.afcepf.atod.wine.entity.ShippingMethod;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;

/**
 *
 * @author ronan
 */
@SessionScoped
@ManagedBean(name="mBeanCartManagement")
public class MBeanCartManagement {


    // create a new command if necessary or 
    private Order order;
    // global error adding product
    private String errorAddProduct;
    @ManagedProperty(value = "#{buOrder}")
    private IBuOrder buOrder;


    public MBeanCartManagement() {
        super();
        errorAddProduct = "";
    }


    /**
     *
     * @param product
     * @return
     */
    public String addProductCart(Product product) {
        String page = null;
        if (!product.getName().equalsIgnoreCase("")
                && product.getPrice() >= 0
                && !product.getProductSuppliers().isEmpty()) {
            try {
                order = buOrder.addItemCart(order, product);
                //page = "pages/basket";
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

    public void removeProductCart(OrderDetail orderDetail) {
        if (!order.getOrdersDetail().isEmpty()) {
            order.getOrdersDetail().remove(orderDetail);
        }
    }

    //  ######################################################## //
    /**
     * ********************************************************
     * Methode pour initialiser le panier pour faire le parcours
     * panier/validation paiement/.
     * ********************************************************
     */
    private List<Product> listProducts = null;

    public void initCart() {
        listProducts = new ArrayList<>();
        Product redWine = new ProductWine(1, "bourgogne", 200.0,
                "bourgogne", "bourgogne", null, null, null, 1);
        Product whiteWine = new ProductWine(1, "provence", 100.0,
                "provence", "provence", null, null, null, 1);
        listProducts.add(redWine);
        listProducts.add(whiteWine);
    }


    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }

    public Order getOrder() {
        return order;
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
