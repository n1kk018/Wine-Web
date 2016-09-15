/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.mbeans.mbeanproduct;

import fr.afcepf.atod.business.product.api.IBuProduct;
import fr.afcepf.atod.mbeans.mbeanuser.MBeanConnexion;
import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.entity.Product;
import fr.afcepf.atod.wine.entity.ProductType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
/**
 *
 * @author ronan
 */
@ManagedBean
@SessionScoped
public class MBeanProduct implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -8118205383226441401L;
	private Logger log = Logger.getLogger(MBeanConnexion.class);
	
	@ManagedProperty(value="#{buProduct}")
    private IBuProduct buProduct;
    
    private String nameProd;
    private List<Product> expensiveProducts;
    private String errorSearch;
    private List<Product> promotedWinesList;
    private List<ProductType> wineTypes;
    
    public MBeanProduct() {
        super();
        nameProd = "";
        errorSearch = "";
    }
    
    @PostConstruct
    public void initIndex(){
    	if(promotedWinesList==null){
    		try {
				promotedWinesList = buProduct.getPromotedProductsSelection();
				
			} catch (WineException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	if(wineTypes==null){
    		try {
				wineTypes = buProduct.getWineTypes();
				log.info(wineTypes);
			} catch (WineException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
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

	public List<ProductType> getWineTypes() {
		return wineTypes;
	}

	public void setWineTypes(List<ProductType> wineTypes) {
		this.wineTypes = wineTypes;
	}
	
   
}
