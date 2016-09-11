/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.mbeans.mbeanproduct;

import fr.afcepf.atod.business.product.api.IBuProduct;
import fr.afcepf.atod.vin.data.exception.WineException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ronan
 */
@ManagedBean(name = "mBeanProduct")
@SessionScoped
public class MBeanProduct {
    @ManagedProperty(value="#{buProduct}")
    private IBuProduct buProduct;
    private String nameProd;
    
    public MBeanProduct() {
        nameProd = "";
    }
    
    public String findByNameProduct() throws WineException {
        String str = null;
        if(!nameProd.equalsIgnoreCase("")) {
            buProduct.findByName(nameProd);
        }
        return str;
    }
    
     // ----------- Getters && Setters ----------------//

    public String getNameProd() {
        return nameProd;
    }

    public void setNameProd(String nameProd) {
        this.nameProd = nameProd;
    }

    public IBuProduct getBuProduct() {
        return buProduct;
    }

    public void setBuProduct(IBuProduct buProduct) {
        this.buProduct = buProduct;
    }
    
    
}
