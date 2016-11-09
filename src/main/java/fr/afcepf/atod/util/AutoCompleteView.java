package fr.afcepf.atod.util;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
 
import org.primefaces.event.SelectEvent;
import fr.afcepf.atod.es.domain.Wine;
import fr.afcepf.atod.es.service.WineService;

 
@ManagedBean
public class AutoCompleteView {
    private Wine wine;
    private List<Wine> selectedWines;
     
    @ManagedProperty("#{wineService}")
    private WineService service;
     
    public List<String> completeText(String query) {
        List<String> results = new ArrayList<String>();
        for(int i = 0; i < 10; i++) {
            results.add(query + i);
        }
         
        return results;
    }
     
    /*public List<Wine> getResults(String query) {
        List<Wine> selectedWines = new ArrayList<Wine>();
        
        service.
         
        for (int i = 0; i < allThemes.size(); i++) {
            Theme skin = allThemes.get(i);
            if(skin.getName().toLowerCase().startsWith(query)) {
                filteredThemes.add(skin);
            }
        }
         
        return filteredThemes;
    }*/
     
    public void onItemSelect(SelectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
    }

    public Wine getWine() {
        return wine;
    }
 
    public void setTheme1(Wine wine) {
        this.wine = wine;
    }
  
    public List<Wine> getSelectedWines() {
        return selectedWines;
    }
 
    public void setSelectedWines(List<Wine> selectedWines) {
        this.selectedWines = selectedWines;
    }
     
    public void setService(WineService service) {
        this.service = service;
    }
 
    /*public char getThemeGroup(Theme theme) {
        return theme.getDisplayName().charAt(0);
    }*/
}
