package fr.afcepf.atod.mbeans.mbeanproduct;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UICommand;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;

import org.apache.log4j.Logger;

import fr.afcepf.atod.business.product.api.IBuProduct;
import fr.afcepf.atod.mbeans.mbeanuser.MBeanConnexion;
import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.entity.Product;
import fr.afcepf.atod.wine.entity.ProductType;
import fr.afcepf.atod.wine.entity.ProductVarietal;
import fr.afcepf.atod.wine.entity.ProductWine;
 
/**
 *
 * @author admin
 */
@ManagedBean(name = "mBeanPagination")
@SessionScoped
public class JsfPaginationBean implements Serializable {
	@ManagedProperty(value = "#{buProduct}")
    private IBuProduct buProduct;
    /**
	 * 
	 */
	private static final long serialVersionUID = 2942714219711641668L;
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
    
    private Logger log = Logger.getLogger(JsfPaginationBean.class);

 
    /**
     * Creates a new instance of JsfPaginationBean
     */
    public JsfPaginationBean() {
        // Set default values somehow (properties files?).
        rowsPerPage = 6; // Default rows per page (max amount of rows to be displayed at once).
        pageRange = 5; // Default page range (max amount of page links to be displayed at once).
    }
    
   /* public void test(ComponentSystemEvent event){
    	List<ProductType> wineTypes;
		try {
			wineTypes = buProduct.getWineTypes();
			Map<ProductType, List<ProductVarietal>> varietals= buProduct.getVarietalsByType(wineTypes);
			winesList = buProduct.categoryAccordingToObjectType(wineTypes.get(0), varietals.get(wineTypes.get(0)).get(0),0,5);
		} catch (WineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }*/
    
    public String category(ProductType type){
    	currentProdType = type;
    	getWinesList();
    	return "pages/category.jsf";
    }
    
    public String category(ProductType type, Object o){
    	currentProdType = type;
    	currentSubCategory = o;
		getWinesList();
		log.info(totalRows);
		log.info(currentPage);
		log.info(winesList);
    	return "pages/category.jsf";
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
   
    public List<ProductWine> getWinesList() {
    	if (winesList == null) {
			loadList();
        }
        return winesList;
	}
	
	public void setWinesList(List<ProductWine> winesList) {
		this.winesList = winesList;
	}
	
	public IBuProduct getBuProduct() {
        return buProduct;
    }

    public void setBuProduct(IBuProduct buProduct) {
        this.buProduct = buProduct;
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
        page(((Integer) ((UICommand) event.getComponent()).getValue() - 1) * rowsPerPage);
    }
 
    private void page(int firstRow) {
        this.firstRow = firstRow;
        loadList();
    }
    
    
    
}
