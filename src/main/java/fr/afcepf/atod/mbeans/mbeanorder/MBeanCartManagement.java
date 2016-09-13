/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.mbeans.mbeanorder;

import fr.afcepf.atod.wine.entity.Product;
import fr.afcepf.atod.wine.entity.ProductAccessories;
import fr.afcepf.atod.wine.entity.ProductWine;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ronan
 */
@SessionScoped
@ManagedBean
public class MBeanCartManagement {

    public MBeanCartManagement() {
        super();

    }

    //  ######################################################## //
    /**
     * ********************************************************
     * Methode pour initialiser le panier pour faire le parcours
     * panier/validation paiement/.
  *********************************************************
     */
    private List<ProductWine> listWines = null;
    private List<ProductAccessories> listAccessories = null;
    private int quantity = 1;
    private double total = 0;

    public void initCart() {
        listWines = new ArrayList<>();
        ProductWine redWine = new ProductWine(1, "bourgogne", 200.0,
                "bourgogne", "bourgogne", null, null, null, 512);
        ProductWine whiteWine = new ProductWine(1, "provence", 100.0,
                "provence", "provence", null, null, null, 512);

        listWines.add(redWine);
        listWines.add(whiteWine);

    }

    /**
     * remove item shopping cart
     *
     */
    public void removeItem(Product item) {
        listWines.remove(item);
    }

    // ---------------- Getters && Setters ------------ //
    public List<ProductWine> getListWines() {
        return listWines;
    }

    public void setListWines(List<ProductWine> listWines) {
        this.listWines = listWines;
    }

    public List<ProductAccessories> getListAccessories() {
        return listAccessories;
    }

    public void setListAccessories(List<ProductAccessories> listAccessories) {
        this.listAccessories = listAccessories;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
