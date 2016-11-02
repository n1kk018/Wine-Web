package fr.afcepf.atod.mbeans.mbeanuser;

import java.io.Serializable;
import java.util.ArrayList;
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
    
	private Adress adress;
	private Customer customer;
	private User user;
	@SuppressWarnings("unused")
    private Civility[] civilities;
	private Country country;
	private List<Country> mesCountries = new ArrayList<>();

    @PostConstruct
    public void initInscription() {
        customer = new Customer();
        adress = new Adress();
        country = new Country();
        try {
            mesCountries = buCountry.listAllCountries();
        } catch (WineException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void addCustomer() {
        try {
            adress.setCountry(country);
            adress.setUser(customer);
            customer.setActivated(true);
            customer.setCreatedAt(new Date());
            customer.setUpdatedAt(new Date());
            System.out.println(customer.getCivility() + " " + customer.getFirstname() + " " + customer.getMail());
            System.out.println(customer.getBirthdate() + " **************");
            customer = buCustomer.addNewCustomer(customer);
            adress = buAdress.addNewAdress(adress);
        } catch (WineException e) {
            e.printStackTrace();
        }
    }

    public void validatePassword(ComponentSystemEvent event) {

        FacesContext fc = FacesContext.getCurrentInstance();

        UIComponent components = event.getComponent();

        // get password
        UIInput uiInputPassword = (UIInput) components.findComponent("password");
        String password = uiInputPassword.getLocalValue() == null ? "" : uiInputPassword.getLocalValue().toString();
        String passwordId = uiInputPassword.getClientId();

        // get confirm password
        UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmPassword");
        String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
                : uiInputConfirmPassword.getLocalValue().toString();

        // Let required="true" do its job.
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            return;
        }

        if (!password.equals(confirmPassword)) {

            FacesMessage msg = new FacesMessage("Password must match confirm password");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(passwordId, msg);
            fc.renderResponse();

        }

    }

//	public void validateMail(ComponentSystemEvent event) {
//
//		FacesContext fc = FacesContext.getCurrentInstance();
//
//		UIComponent components = event.getComponent();
//
//		// get password
//		UIInput uiInputPassword = (UIInput) components.findComponent("mail1");
//		String mail1 = uiInputPassword.getLocalValue() == null ? "" : uiInputPassword.getLocalValue().toString();
//		String passwordId = uiInputPassword.getClientId();
//
//		// get confirm password
//		UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmedMail");
//		String confirmedMail = uiInputConfirmPassword.getLocalValue() == null ? ""
//				: uiInputConfirmPassword.getLocalValue().toString();
//
//		// Let required="true" do its job.
//		if (mail1.isEmpty() || confirmedMail.isEmpty()) {
//			return;
//		}
//
//		if (!mail1.equals(confirmedMail)) {
//
//			FacesMessage msg = new FacesMessage("Les mails doivent etre correspondre");
//			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
//			fc.addMessage(passwordId, msg);
//			fc.renderResponse();
//
//		}
//	}

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
}
