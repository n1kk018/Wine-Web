/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.mbeans.mbeanproduct;

import fr.afcepf.atod.business.product.api.IBuProduct;
import fr.afcepf.atod.mbeans.mbeanuser.MBeanConnexion;
import fr.afcepf.atod.util.UtilFindPath;
import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.entity.Product;
import fr.afcepf.atod.wine.entity.ProductAccessories;
import fr.afcepf.atod.wine.entity.ProductType;
import fr.afcepf.atod.wine.entity.ProductVarietal;
import fr.afcepf.atod.wine.entity.ProductWine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UICommand;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

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
    //private List<Product> winesList;
    private List<ProductType> wineTypes;
    private Map<ProductType, List<String>> appellations;
    private Map<ProductType, List<ProductVarietal>> varietals;
    
    //Test
    private List<ProductWine> winesList;
	private ProductType currentProdType;
    private Object currentSubCategory;
 
    /**
     * pagination stuff
     */
    private int totalRows;
    private int firstRow;
    private int rowsPerPage;
    private int totalPages;
    private int pageRange;
    private Integer[] pages;
    private int currentPage;
    

    public MBeanProduct() {
        super();
        nameProd = "";
        errorSearch = "";
        accessory = new ProductAccessories();
        rowsPerPage = 8; // Default rows per page (max amount of rows to be displayed at once).
        pageRange = 5;
    }

    @PostConstruct
    public void initExpensive() {
    	try {
			expensiveProducts = buProduct.findExpensive(500.0);
		} catch (WineException e) {			
			e.printStackTrace();
		}
    }
    

	@PostConstruct
	public void initIndex() {


		if (promotedWinesList == null ) {
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
    
    public String getProductParam(FacesContext fc){
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("product");
	}



    public String findByNameProduct() throws WineException {
        String str = null;
        if (!nameProd.equalsIgnoreCase("")) {
            buProduct.findByName(nameProd);
        }
        return str;
    }
    
    public String article() throws WineException {
    	String str = null;
    	FacesContext fc = FacesContext.getCurrentInstance();
		Integer id = Integer.valueOf(getProductParam(fc));
        if (id>0) {
        	currentProd = buProduct.findById(id);
        	String referrer = FacesContext.getCurrentInstance().getExternalContext().getRequestHeaderMap().get("referer");
        	log.info(referrer);
        	str = "article.jsf?faces-redirect=true";
        	if(!referrer.contains("/pages/")){
        		str="pages/"+str;
        	}
        }
        return str;
    }
    
    public String category(ProductType type){
    	String str = null;
    	currentProdType = type;
    	getWinesList();
    	String referrer = FacesContext.getCurrentInstance().getExternalContext().getRequestHeaderMap().get("referer");
    	log.info(referrer);
    	str = "category.jsf?faces-redirect=true";
    	if(!referrer.contains("/pages/")){
    		str="pages/"+str;
    	}
    	return str;
    }
    
    public String category(ProductType type, Object o){
    	String str = null;
    	currentProdType = type;
    	currentSubCategory = o;
		getWinesList();
		log.info(totalRows);
		log.info(currentPage);
		log.info(winesList);
		/*String referrer = FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRequestHeaderMap().get("referer");
    	log.info(referrer);
    	str = "?faces-redirect=true";
    	if(!referrer.contains("/pages/")){
    		str="pages/"+str;
    	}*/
    	str = UtilFindPath.findURLPath("category.jsf");
    	return str;
    			
    }
        
   private void  loadList() {
    		try {
				winesList = buProduct.categoryAccordingToObjectType(currentProdType, currentSubCategory, firstRow, rowsPerPage);
	    		totalRows = buProduct.countCategoryAccordingToObjectType(currentProdType,currentSubCategory);
	
	    	    // Set currentPage, totalPages and pages.
	            currentPage = (totalRows / rowsPerPage) - ((totalRows - firstRow) / rowsPerPage) + 1;
	            totalPages = (totalRows / rowsPerPage) + ((totalRows % rowsPerPage != 0) ? 1 : 0);
	            int pagesLength = Math.min(pageRange, totalPages);
	            pages = new Integer[pagesLength];
	     
	            // firstPage must be greater than 0 and lesser than totalPages-pageLength.
	            int firstPage = Math.min(Math.max(0, currentPage - (pageRange / 2)), totalPages - pagesLength);
	     
	            // Create pages (page numbers for page links).
	            for (int i = 0; i < pagesLength; i++) {
	                pages[i] = ++firstPage;
	            }
    		} catch (WineException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    
    


	public String article(Integer id) throws WineException {
		String str = null;
		if (id>0) {
			currentProd = buProduct.findById(id);
			str = "pages/article.jsf";
		}
		return str;
	}
    
    private void page(int firstRow) {
        this.firstRow = firstRow;
        loadList();
    }
    
 // Paging actions -----------------------------------------------------------------------------
    public void pageFirst() {
        page(0);
    }
 
    public void pageNext() {
        page(firstRow + rowsPerPage);
    }
 
    public void pagePrevious() {
        page(firstRow - rowsPerPage);
    }
 
    public void pageLast() {
        page(totalRows - ((totalRows % rowsPerPage != 0) ? totalRows % rowsPerPage : rowsPerPage));
    }
 
    public void page(ActionEvent event) {  
    	log.info(((UICommand)event.getComponent()).getValue());
        page(((Integer) ((UICommand) event.getComponent()).getValue() - 1) * rowsPerPage);
    }
 
   

	/**
	 *
	 * @param min
	 * @return
	 */
	public List<Product> findExpensiveProducts(double min) {
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
		return expensiveProducts;
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


	public List<Product> getExpensiveProducts() {
		return expensiveProducts;
	}

	
    public List<ProductWine> getWinesList() {
		loadList();
        return winesList;
	}

	public void setExpensiveProducts(List<Product> expensiveProducts) {
		this.expensiveProducts = expensiveProducts;
	}


	//	public void getWinesBy(Integer num, Integer page){
	//		this.winesList = buProduct.getWinesBy(4, 1);
	//		RequestContext.getCurrentInstance().update("ajaxcontent");
	//	}

	
	public void setWinesList(List<ProductWine> winesList) {
		this.winesList = winesList;
	}
	
	

	public int getTotalRows() {
        return totalRows;
    }
 
    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }
 
    public int getFirstRow() {
        return firstRow;
    }
 
    public void setFirstRow(int firstRow) {
        this.firstRow = firstRow;
    }
 
    public int getRowsPerPage() {
        return rowsPerPage;
    }
 
    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }
 
    public int getTotalPages() {
        return totalPages;
    }
 
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
 
    public int getPageRange() {
        return pageRange;
    }
 
    public void setPageRange(int pageRange) {
        this.pageRange = pageRange;
    }
 
    public Integer[] getPages() {
        return pages;
    }
 
    public void setPages(Integer[] pages) {
        this.pages = pages;
    }
 
    public int getCurrentPage() {
        return currentPage;
    }
 
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
