package fr.afcepf.atod.util;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name="selectHandler")
public class searchSelectHandler {
    @ManagedProperty("#{param.product}")
    private int product;
    public void handle(Integer id) {
        try {
            product=id;
            FacesContext.getCurrentInstance()
            .getExternalContext()
            .redirect(UtilFindPath
                    .findURLPath("article.jsf")+"&includeViewParams=true");
        } catch (IOException paramE) {
            // TODO Auto-generated catch block
            paramE.printStackTrace();
        }
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int paramProduct) {
        product = paramProduct;
    } 
    
    
}
