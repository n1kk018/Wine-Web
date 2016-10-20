package fr.afcepf.atod.util;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ComponentSystemEvent;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.afcepf.atod.i18n.LocaleBean;

@ManagedBean
@RequestScoped
public class AjaxBean {
    @ManagedProperty(value = "#{localeBean}")
    private LocaleBean localeBean;
    private Gson gson = new Gson();
    private Logger log = Logger.getLogger(AjaxBean.class);
        
    public String handleEvent(ComponentSystemEvent event) {
        log.info("=====================AjaxEvent-==================");
        String currency = FacesContext.getCurrentInstance().getExternalContext()
        .getRequestParameterMap().get("currency");
        //String oldOne = localeBean.getCurrency();
        return gson.toJson("Hello");
    }

    public LocaleBean getLocaleBean() {
        return localeBean;
    }

    public void setLocaleBean(LocaleBean paramLocaleBean) {
        localeBean = paramLocaleBean;
    }
}
