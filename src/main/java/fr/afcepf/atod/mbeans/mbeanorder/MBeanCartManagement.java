/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.mbeans.mbeanorder;

import fr.afcepf.atod.business.customer.api.IBuCustomer;
import fr.afcepf.atod.wine.entity.Customer;
import fr.afcepf.atod.wine.entity.Order;
import fr.afcepf.atod.wine.entity.OrderDetail;
import fr.afcepf.atod.wine.entity.PaymentInfo;
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
	public static Logger log = Logger.getLogger(MBeanCartManagement.class);
	
	@ManagedProperty(value = "#{buCustomer}")
    private IBuCustomer buCustomer;
	
	
	public MBeanCartManagement() {
		super();

	}

	// ######################################################## //
	/**
	 * ******************************************************** Methode pour
	 * initialiser le panier pour faire le parcours panier/validation paiement/.
	 *********************************************************
	 */
	private List<OrderDetail> shoppingCart = new ArrayList<>();
	private OrderDetail orderDetail1;
	private OrderDetail orderDetail2;
	private Double totalLine = 0.0;
	private Double totalCart = 0.0;
	private Product prodcut ;
	private Double totalTransportFree;
	private int totalQuantity=0;
	private int quantity = 1;
	

	public void initCart() {
		
		if(shoppingCart.size()==0){
			ProductWine redWine = new ProductWine(null, "bourgogne", 200.0, "bourgogne", "bourgogne", null, null, null, 512);
			ProductWine whiteWine = new ProductWine(null, "provence", 100.0, "provence", "provence", null, null, null, 512);
//			orderDetail2 = new OrderDetail(null,3, null, redWine);
			orderDetail1 = new OrderDetail(null, 2, null, whiteWine);
		
		shoppingCart.add(orderDetail1);
//		shoppingCart.add(orderDetail2);
		}
		
//		quantity = orderDetail1.getQuantite();
		log.info("*******************************init cart**************************************");
	}

	/**
	 * remove item shopping cart
	 *
	 */
	public void removeOrderTetail(OrderDetail o) {
	shoppingCart.remove(o);
	}

	/**
	 * Ajax : nombre de produit change listener
	 *
	 */

	public void updateBasket() {
		log.info("*****************************************"+ quantity);
		orderDetail1.setQuantite(quantity);
		
		calculerOrderLine();
	
	}

	/**
	 * calculer prix d'une ligne de commande
	 */
	public Double calculerOrderLine() {
		totalLine = (orderDetail1.getProductOrdered().getPrice())*(orderDetail1.getQuantite());
		log.info("*******************************orderline**************************************");
		return totalLine;
		
	}

	/**
	 * calculer prix d'une ligne de commande
	 */
	public Double calculerTotalOrder(int quantity) {

		for (OrderDetail o : shoppingCart) {
			totalCart = totalCart + o.getQuantite() * (o.getProductOrdered().getPrice() )+ totalTransportFree;
		}

		return totalCart;
	}

	/**
	 * calculer prix du frais transport
	 */
	public Double calculerTransportFree() {
		totalTransportFree = (double) (totalQuantity * 2);
		return totalTransportFree;
	}
	
	/**
	 * calculer nombre d'article
	 */
	public int calculerTotalQuantity(int quantity) {
		for(OrderDetail o : shoppingCart){
			
			totalQuantity = o.getQuantite() + totalQuantity;
		}
		
		
		return totalQuantity;
	}

	// ---------------- Getters && Setters ------------ //
	
	
	public List<OrderDetail> getShoppingCart() {
		return shoppingCart;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public IBuCustomer getBuCustomer() {
		return buCustomer;
	}

	public void setBuCustomer(IBuCustomer buCustomer) {
		this.buCustomer = buCustomer;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public void setShoppingCart(List<OrderDetail> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}



	public OrderDetail getOrderDetail1() {
		return orderDetail1;
	}

	public void setOrderDetail1(OrderDetail orderDetail1) {
		this.orderDetail1 = orderDetail1;
	}

	public OrderDetail getOrderDetail2() {
		return orderDetail2;
	}

	public void setOrderDetail2(OrderDetail orderDetail2) {
		this.orderDetail2 = orderDetail2;
	}

	public Double getTotalLine() {
		return totalLine;
	}

	public void setTotalLine(Double totalLine) {
		this.totalLine = totalLine;
	}

	public Double getTotalCart() {
		return totalCart;
	}

	public void setTotalCart(Double totalCart) {
		this.totalCart = totalCart;
	}

	public Product getProdcut() {
		return prodcut;
	}

	public void setProdcut(Product prodcut) {
		this.prodcut = prodcut;
	}

	public Double getTotalTransportFree() {
		return totalTransportFree;
	}

	public void setTotalTransportFree(Double totalTransportFree) {
		this.totalTransportFree = totalTransportFree;
	}



}
