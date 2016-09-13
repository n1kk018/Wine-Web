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

/**
 *
 * @author ronan
 */
@SessionScoped
@ManagedBean
public class MBeanCartManagement {

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
	private List<OrderDetail> shoppingCart;
	private List<Product> orderDetail;
	private int quantity = 1;
	private Double totalLine = 0.0;
	private Double totalCart = 0.0;
	private Product prodcut ;
	private Double totalTransportFree;
	private int totalQuantity=0;
	
	

	public void initCart() {
		
		
		ProductWine redWine = new ProductWine(null, "bourgogne", 200.0, "bourgogne", "bourgogne", null, null, null, 512);
		ProductWine whiteWine = new ProductWine(null, "provence", 100.0, "provence", "provence", null, null, null, 512);
		
		orderDetail.add(whiteWine);
		orderDetail.add(redWine);
		
		shoppingCart.add((OrderDetail) orderDetail);
		
//		Customer customer = (Customer) buCustomer.findUserById(1);
//		
//		orderDetail1 = new OrderDetail(null, 1, shoppingCart, redWine);
//		orderDetail2 = new OrderDetail(null, 3, shoppingCart, whiteWine);
//		
//		shoppingCart = new Order(1, new Date(), null,
//	            1, customer,
//	            PaymentInfo paymentInfo);
	}

	/**
	 * remove item shopping cart
	 *
	 */
	public void removeOrderTetail(OrderDetail orderLine) {
	shoppingCart.remove(orderLine);
	}

	/**
	 * Ajax : nombre de produit change listener
	 *
	 */

	public void onChangeNombreProduit(ValueChangeEvent event) {
		quantity = orderDetail.size() ;
		quantity = (int) event.getNewValue();
	}

	/**
	 * calculer prix d'une ligne de commande
	 */
	public Double calculerOrderLine( int quantity) {
		totalLine = prodcut.getPrice() * quantity;
		return totalLine;

	}

	/**
	 * calculer prix d'une ligne de commande
	 */
	public Double calculerTotalOrder(int quantity) {

		for (OrderDetail o : shoppingCart) {
			totalCart = totalCart + o.getQuantite() * o.getProductOrdered().getPrice() + totalTransportFree;
		}

		return totalCart;
	}

	/**
	 * calculer prix du frais transport
	 */
	public Double calculerTransportFree(int quantity) {
		totalTransportFree = (double) (quantity * 2);
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

	public List<Product> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(List<Product> orderDetail) {
		this.orderDetail = orderDetail;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
