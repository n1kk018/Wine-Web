package fr.afcepf.atod.i18n;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.SpringSessionContext;

import fr.afcepf.atod.business.product.api.IBuProduct;
import fr.afcepf.atod.mbeans.mbeanproduct.MBeanProduct;
import fr.afcepf.atod.mbeans.mbeanuser.MBeanConnexion;
import fr.afcepf.atod.util.UtilFindPath;
import fr.afcepf.atod.wine.entity.ProductAccessories;
import fr.afcepf.atod.wine.entity.ProductType;

@ManagedBean
@SessionScoped
public class LocaleBean implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Locale locale = FacesContext.getCurrentInstance().getApplication().getDefaultLocale();
    @ManagedProperty(value = "#{mBeanProduct}")
    private MBeanProduct mBeanProduct;
    private String language;
    private String currency = "EUR";
    private Logger log = Logger.getLogger(LocaleBean.class);
    
    public LocaleBean() {
        super();
        
        log.info("========================LocaleBean=======================");
    }
    
    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        mBeanProduct.setWineTypes(null);
        /*if(mBeanProduct.getCurrentProd()!=null){
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
            .put("product", mBeanProduct.getCurrentProd().getId().toString());
            mBeanProduct.setCurrentProd(null);
        }*/
        if(language.contains("en"))
            locale = Locale.US;
        else
            locale = Locale.FRANCE;
        FacesContext.getCurrentInstance().getApplication().setDefaultLocale(locale);
        this.language = locale.toString();
        try {
            /*ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());*/
           FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
        } catch (IOException paramE) {
            // TODO Auto-generated catch block
            paramE.printStackTrace();
        }
    }
    
   
    
    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param paramCurrency the currency to set
     */
    public void setCurrency(String paramCurrency) {
        currency = paramCurrency;
    }

    public void execute()
    {
        log.info("========================Execute in LocaleBean=======================");
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        currency = (String) map.get("trgt");
        log.info(currency);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put("currency", currency);
    }

    public MBeanProduct getmBeanProduct() {
        return mBeanProduct;
    }

    public void setmBeanProduct(MBeanProduct paramMBeanProduct) {
        mBeanProduct = paramMBeanProduct;
    } 
    
    public String getCurrencyLabel(String code)
    {
        HashMap<String, String> devises = new HashMap<String,String>();
        devises.put("EUR","Euro");
        devises.put("GBP","Livre Sterling");
        devises.put("USD","Dollar U.S.");
        devises.put("JPY","Yen japonais");
        devises.put("BGN","Lev bulgare");
        devises.put("DKK","Couronne danoise");
        devises.put("EEK","Couronne estonienne");
        devises.put("HUF","Forint hongrois");
        devises.put("LVL","Lats letton");
        devises.put("LTL","Litas lithuanien");
        devises.put("PLN","Zloty polonais");
        devises.put("CZK","Couronne tchèque");
        devises.put("SEK","Couronne suédoise");
        
        return devises.get(code);
    }
    
}