package fr.afcepf.atod.mbeans.mbeanuser;

import java.io.Serializable;
import java.util.Date;

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

	private Adress adress;
	private Customer customer;
	private User user;
	@SuppressWarnings("unused")
    private Civility[] civilities;
	private Country country;
	
	
    @PostConstruct
	public void initInscription() {
		customer = new Customer();
		adress = new Adress();
		country = new Country();
	}

	public void addCustomer() {
		try {
			customer.setActivated(true);
			customer.setCreatedAt(new Date());
			customer.setUpdatedAt(new Date());
			customer = buCustomer.addNewCustomer(customer);
			adress.setUser(customer);
			adress = buAdress.addNewAdress(adress);
		} catch (WineException e) {
			e.printStackTrace();
		}
	}

	public void validatePassword(ComponentSystemEvent event) {

		FacesContext fc = FacesContext.getCurrentInstance();

		UIComponent components = event.getComponent();

		// get password
		UIInput uiInputPassword = (UIInput) components.findComponent("pwd");
		String pwd = uiInputPassword.getLocalValue() == null ? "" : uiInputPassword.getLocalValue().toString();
		String passwordId = uiInputPassword.getClientId();

		// get confirm password
		UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmedPwd");
		String confirmedPwd = uiInputConfirmPassword.getLocalValue() == null ? ""
				: uiInputConfirmPassword.getLocalValue().toString();

		// Let required="true" do its job.
		if (pwd.isEmpty() || confirmedPwd.isEmpty()) {
			return;
		}

		if (!pwd.equals(confirmedPwd)) {

			FacesMessage msg = new FacesMessage("Les mots de passe doivent etre correspondre");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(passwordId, msg);
			fc.renderResponse();

		}
	}

	public void validateMail(ComponentSystemEvent event) {

		FacesContext fc = FacesContext.getCurrentInstance();

		UIComponent components = event.getComponent();

		// get password
		UIInput uiInputPassword = (UIInput) components.findComponent("mail1");
		String mail1 = uiInputPassword.getLocalValue() == null ? "" : uiInputPassword.getLocalValue().toString();
		String passwordId = uiInputPassword.getClientId();

		// get confirm password
		UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmedMail");
		String confirmedMail = uiInputConfirmPassword.getLocalValue() == null ? ""
				: uiInputConfirmPassword.getLocalValue().toString();

		// Let required="true" do its job.
		if (mail1.isEmpty() || confirmedMail.isEmpty()) {
			return;
		}

		if (!mail1.equals(confirmedMail)) {

			FacesMessage msg = new FacesMessage("Les mails doivent etre correspondre");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(passwordId, msg);
			fc.renderResponse();

		}
	}

	// ----------- Getters && Setters ----------------//

    public IBuAdress getBuAdress() {
        return buAdress;
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
