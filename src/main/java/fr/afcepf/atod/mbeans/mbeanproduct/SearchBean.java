package fr.afcepf.atod.mbeans.mbeanproduct;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import fr.afcepf.atod.es.domain.Wine;
import fr.afcepf.atod.es.service.WineService;
     
@ManagedBean(name = "searchBean")
@SessionScoped
public class SearchBean implements Serializable, Converter {
   
    private static final long serialVersionUID = -7443518193969296647L;
    @ManagedProperty("#{wineService}")
    private WineService service;
    private List<Wine> foundWines;
    private String lastQuery;
    
    public List<Wine> completeWine(String query) {
        lastQuery = query;
        foundWines = service.searchByStringQuery(query);
        return foundWines;
    }
    
    @Override
    public Object getAsObject(FacesContext paramArg0, UIComponent paramArg1, String paramArg2) {
        if(!foundWines.isEmpty() && paramArg2 != null && paramArg2.trim().length() > 0) {
            try {
                for (Wine wine : foundWines) {
                    if(wine.getId().equals(Integer.parseInt(paramArg2))) {
                        return wine;
                    }
                }
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid wine."));
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext paramArg0, UIComponent paramArg1, Object paramArg2) {
        if(paramArg2 != null) {
            return String.valueOf(((Wine) paramArg2).getId());
        }
        return null;
    }
        
    public WineService getService() {
        return service;
    }

    public void setService(WineService paramService) {
        service = paramService;
    }

    public List<Wine> getFoundWines() {
        return foundWines;
    }

    public void setFoundWines(List<Wine> paramFoundWines) {
        foundWines = paramFoundWines;
    }

    public String getLastQuery() {
        return lastQuery;
    }

    public void setLastQuery(String paramLastQuery) {
        lastQuery = paramLastQuery;
    }
}
