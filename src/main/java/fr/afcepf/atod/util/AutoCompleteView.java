package fr.afcepf.atod.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.event.SelectEvent;

import fr.afcepf.atod.es.domain.Wine;
import fr.afcepf.atod.es.service.WineService;
     
@ManagedBean
public class AutoCompleteView {
    @ManagedProperty("#{wineService}")
    private WineService service;
    
    public List<Wine> completeWine(String query) {
        List<Wine> searchList = new ArrayList<Wine>();
        searchList = service.searchByStringQuery(query);
        return searchList;
    }
        
    public WineService getService() {
        return service;
    }

    public void setService(WineService paramService) {
        service = paramService;
    }
    
    public void onMoreText(AjaxBehaviorEvent event) {
        System.out.println(((AutoComplete) event.getSource()).getMoreText());
    }
}
