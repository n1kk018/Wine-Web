package fr.afcepf.atod.util;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import fr.afcepf.atod.es.domain.Wine;
import fr.afcepf.atod.es.service.WineService;

@ManagedBean
public class WineConverter implements Converter {
    @ManagedProperty("#{wineService}")
    private WineService service;
    
    @Override
    public Object getAsObject(FacesContext paramArg0, UIComponent paramArg1, String paramArg2) {
        if(paramArg2 != null && paramArg2.trim().length() > 0) {
            try {
                return service.findById(Integer.parseInt(paramArg2));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid wine."));
            }
        }
        else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext paramArg0, UIComponent paramArg1, Object paramArg2) {
        if(paramArg2 != null) {
            return String.valueOf(((Wine) paramArg2).getId());
        }
        else {
            return null;
        }
    }

    public WineService getService() {
        return service;
    }

    public void setService(WineService paramService) {
        service = paramService;
    }
    
    
}
