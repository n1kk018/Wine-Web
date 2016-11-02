package fr.afcepf.atod.mbeans.mbeanuser;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import fr.afcepf.atod.business.customer.api.IBuCustomer;
import fr.afcepf.atod.business.product.api.IBuAdress;
import fr.afcepf.atod.business.product.api.IBuCountry;
import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.entity.Adress;
import fr.afcepf.atod.wine.entity.Civility;
import fr.afcepf.atod.wine.entity.Country;
import fr.afcepf.atod.wine.entity.Customer;
import fr.afcepf.atod.wine.entity.User;

@ManagedBean(name = "mBeanClient")
@SessionScoped
public class MBeanClient implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{buCustomer}")
	private IBuCustomer buCustomer;
    @ManagedProperty(value = "#{buAdress}")
    private IBuAdress buAdress;
    @ManagedProperty(value = "#{buCountry}")
    private IBuCountry buCountry;
    
    private String sdate;
    
	private Adress adress = new Adress();
	private Customer customer = new Customer();
	private User user;
	@SuppressWarnings("unused")
    private Civility[] civilities;
	private Country country = new Country();
	private List<Country> mesCountries ;

    @PostConstruct
    public void initInscription() {
        try {
            mesCountries = buCountry.listAllCountries();
        } catch (WineException e) {
            e.printStackTrace();
        }
    }

    public void addCustomer(){
            SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
            try {
                Date date = sdf.parse(sdate);
                customer.setBirthdate(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            adress.setCountry(country);
            adress.setUser(customer);
            customer.setActivated(true);
            customer.setCreatedAt(new Date());
            customer.setUpdatedAt(new Date());
            try {
                customer = buCustomer.addNewCustomer(customer);
            } catch (WineException e) {
                e.printStackTrace();
            }
            adress = buAdress.addNewAdress(adress);
            adress.setBilling(true);
            adress =buAdress.addNewAdress(adress);
    }

    public void validatePassword(ComponentSystemEvent event) {

        FacesContext fc = FacesContext.getCurrentInstance();

        UIComponent components = event.getComponent();

        // get password
        UIInput uiInputPassword = (UIInput) components.findComponent("passwordZOZO");
        String passwordZOZO = uiInputPassword.getLocalValue() == null ? "" : uiInputPassword.getLocalValue().toString();
        String passwordId = uiInputPassword.getClientId();

        // get confirm password
        UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmPassword");
        String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
                : uiInputConfirmPassword.getLocalValue().toString();

        // Let required="true" do its job.
        if (passwordZOZO.isEmpty() || confirmPassword.isEmpty()) {
            return;
        }

        if (!passwordZOZO.equals(confirmPassword)) {

            FacesMessage msg = new FacesMessage("Password must match confirm password");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(passwordId, msg);
            fc.renderResponse();

        }

    }

	// ----------- Getters && Setters ----------------//
    
    
    public IBuAdress getBuAdress() {
        return buAdress;
    }

    public IBuCountry getBuCountry() {
        return buCountry;
    }

    public void setBuCountry(IBuCountry buCountry) {
        this.buCountry = buCountry;
    }

    public List<Country> getMesCountries() {
        return mesCountries;
    }

    public void setMesCountries(List<Country> mesCountries) {
        this.mesCountries = mesCountries;
    }

    public void setBuAdress(IBuAdress buAdress) {
        this.buAdress = buAdress;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public IBuCustomer getBuCustomer() {
		return buCustomer;
	}

	public void setBuCustomer(IBuCustomer buCustomer) {
		this.buCustomer = buCustomer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Civility[] getCivilities() {
		return Civility.values();
	}

	public void setCivilities(Civility[] civilities) {
		this.civilities = civilities;
	}

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }
	
}
