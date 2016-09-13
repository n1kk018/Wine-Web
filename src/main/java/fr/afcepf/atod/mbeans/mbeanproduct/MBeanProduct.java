/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.mbeans.mbeanproduct;

import fr.afcepf.atod.business.product.api.IBuProduct;
import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.entity.Product;

import java.util.List;

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
    private List<Product> promotedWinesList;
    
    public MBeanProduct() {
        nameProd = "";
    }
    
    public void initIndex() throws WineException{
    	promotedWinesList = buProduct.getPromotedProductsSelection();
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

	public List<Product> getPromotedWinesList() {
		return promotedWinesList;
	}

	public void setPromotedWinesList(List<Product> promotedWinesList) {
		this.promotedWinesList = promotedWinesList;
	}
    
   
    
}
