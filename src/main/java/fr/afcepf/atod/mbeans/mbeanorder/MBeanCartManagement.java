/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.mbeans.mbeanorder;

import fr.afcepf.atod.wine.entity.Product;
import fr.afcepf.atod.wine.entity.ProductWine;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ronan
 */
@SessionScoped
@ManagedBean
public class MBeanCartManagement {
    
 //  ############################################################# //
 
 /**********************************************************
  * Methode pour initialiser le panier pour faire le parcours
  * panier/validation paiement/.
  **********************************************************/
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

    public List<Product> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }
    
}
