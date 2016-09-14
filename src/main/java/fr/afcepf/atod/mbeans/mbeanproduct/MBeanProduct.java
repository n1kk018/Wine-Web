/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.mbeans.mbeanproduct;

import fr.afcepf.atod.business.product.api.IBuProduct;
import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.entity.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ronan
 */
@ManagedBean(name = "mBeanProduct")
@RequestScoped
public class MBeanProduct {
    
    @ManagedProperty(value="#{buProduct}")
    private IBuProduct buProduct;
    
    private String nameProd;
    private List<Product> expensiveProducts;
    private String errorSearch;
    private List<Product> promotedWinesList;
    
    public MBeanProduct() {
        super();
        nameProd = "";
        errorSearch = "";
    }
    
    /**
     * 
     * @return
     * @throws WineException 
     */
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
    
    
    
    /**
     * 
     * @param min
     * @return 
     */
    public String findExpensiveProducts(double min){
        String str = null;
        expensiveProducts = new ArrayList<>();
        if (min >= 0.0) {
            try {
                expensiveProducts = buProduct.findExpensive(min);
            } catch (WineException ex) {
               errorSearch = "Research not found in the Database.";
            }
            if (!expensiveProducts.isEmpty()){
                
            } else {
                errorSearch = "Research not found in the Database.";
            }
        } else {
            errorSearch = "Define positive criteria...";
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
