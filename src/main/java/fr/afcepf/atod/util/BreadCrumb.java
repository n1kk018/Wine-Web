package fr.afcepf.atod.util;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

@ManagedBean(name ="breadCrumb")
@SessionScoped
public class BreadCrumb {
    private MenuModel menuModel;

    public BreadCrumb(){
        // Initialize
        this.menuModel = new DefaultMenuModel();

        // Create index menuItem
        DefaultMenuItem index = new DefaultMenuItem();
        index.setValue("Index");
        index.setUrl("index.jsf");

        // Add menuItems
        this.menuModel.addElement(index);
    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }
    
    public void AddItem(String value, String url){
        DefaultMenuItem item = new DefaultMenuItem();
        item.setValue(value);
        item.setUrl(url);
        this.menuModel.addElement(item);
    }
}
