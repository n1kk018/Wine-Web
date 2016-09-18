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
import fr.afcepf.atod.wine.entity.ProductAccessories;
import fr.afcepf.atod.wine.entity.ProductType;
import fr.afcepf.atod.wine.entity.ProductVarietal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author ronan
 */
@ManagedBean
@SessionScoped
public class MBeanProduct implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8118205383226441401L;
    private Logger log = Logger.getLogger(MBeanConnexion.class);

    @ManagedProperty(value = "#{buProduct}")
    private IBuProduct buProduct;

    private ProductAccessories accessory;
    private Product currentProd;
    private String nameProd;
    private List<Product> expensiveProducts;
    private String errorSearch;
    private List<Product> promotedWinesList;
    private List<Product> winesList;
    private List<ProductType> wineTypes;
    private Map<ProductType, List<String>> appellations;
    private Map<ProductType, List<ProductVarietal>> varietals;

    public MBeanProduct() {
        super();
        nameProd = "";
        errorSearch = "";
        accessory = new ProductAccessories();
    }

    @PostConstruct
    public void initIndex() {
        if (promotedWinesList == null) {
            try {
                promotedWinesList = buProduct.getPromotedProductsSelection();

            } catch (WineException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //Données Nav
        if (wineTypes == null) {
            try {
                wineTypes = buProduct.getWineTypes();
                appellations = buProduct.getAppellationsByType(wineTypes);
                varietals = buProduct.getVarietalsByType(wineTypes);
                log.info(appellations);
            } catch (WineException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public String findByNameProduct() throws WineException {
        String str = null;
        if (!nameProd.equalsIgnoreCase("")) {
            buProduct.findByName(nameProd);
        }
        return str;
    }
    
    public String article(Integer id) throws WineException {
    	String str = null;
        if (id>0) {
        	currentProd = buProduct.findById(id);
        	str = "pages/article.jsf";
        }
        return str;
    }
    
    public String category(Object o) throws WineException {
    	String str = null;
    	log.info(o.getClass());
    	
    	return str;
    }
    
    public String category(ProductType type, Object o) throws WineException {
    	String str = null;
    	log.info(o.getClass());
    	
    	return str;
    }

    /**
     *
     * @param min
     * @return
     */
    public String findExpensiveProducts(double min) {
        String str = null;
        expensiveProducts = new ArrayList<>();
        if (min >= 0.0) {
            try {
                expensiveProducts = buProduct.findExpensive(min);
            } catch (WineException ex) {
                errorSearch = "Research not found in the Database.";
            }
            if (!expensiveProducts.isEmpty()) {

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

    public List<Product> getWinesList() {
		return winesList;
	}

	public void setWinesList(List<Product> winesList) {
		this.winesList = winesList;
	}

	public void setWineTypes(List<ProductType> wineTypes) {
        this.wineTypes = wineTypes;
    }

    public Map<ProductType, List<String>> getAppellations() {
        return appellations;
    }

    public void setAppellations(Map<ProductType, List<String>> appellations) {
        this.appellations = appellations;
    }

    public Map<ProductType, List<ProductVarietal>> getVarietals() {
        return varietals;
    }

	public void setVarietals(Map<ProductType, List<ProductVarietal>> varietals) {
		this.varietals = varietals;
	}

	public ProductAccessories getAccessory() {
		return accessory;
	}

	public void setAccessory(ProductAccessories accessory) {
		this.accessory = accessory;
	}

	public Product getCurrentProd() {
		return currentProd;
	}
	
	public void getWinesBy(Integer num, Integer page){
		this.winesList = buProduct.getWinesBy(4, 1);
		RequestContext.getCurrentInstance().update("ajaxcontent");
	}
}
