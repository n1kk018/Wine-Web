package fr.afcepf.atod.i18n;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

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
import fr.afcepf.atod.vin.data.exception.WineException;
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
    private Logger log = Logger.getLogger(MBeanConnexion.class);
    
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

    public MBeanProduct getmBeanProduct() {
        return mBeanProduct;
    }

    public void setmBeanProduct(MBeanProduct paramMBeanProduct) {
        mBeanProduct = paramMBeanProduct;
    }    
    
}