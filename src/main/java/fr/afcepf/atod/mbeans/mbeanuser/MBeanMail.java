package fr.afcepf.atod.mbeans.mbeanuser;

import java.io.Serializable;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import fr.afcepf.atod.business.customer.test.JavaMail;
import fr.afcepf.atod.wine.entity.Customer;
import fr.afcepf.atod.wine.entity.Order;


@ManagedBean(name ="mBeanMail")
@SessionScoped
public class MBeanMail implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mailTo;
	

	public String getMailTo() {
		return mailTo;
	}


	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}




	public void sendMail(){
		JavaMail.sendMail(mailTo);
		mailTo = null;
	}
	
	
	public void sendFacture(Order order){
		

		final String username = "webwinemania@gmail.com";
		final String password = "Webwinemania2016";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("webwinemania@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(order.getCustomer().getMail()));
			message.setSubject("Votre facture OnWine n° " + order.getId());
			message.setText("Bonjour " +order.getCustomer().getCivility() + " " +order.getCustomer().getLastname() +", "
					+ "\n\n Vous avez récemment effectué un achat sur notre site Internet OnWine.fr"
					+ "\n\n et nous vous remercions de votre confiance."
					+ "\n\n Nous avons le plaisir de vous adresser ci-joint la facture de votre commande: n°" +order.getId()
					+ "\n\n En espérant avoir le plaisir de vous retrouver bientôt sur www.onwine.fr"
					+ "\n\n Avec tous nos remerciements."
					+ "\n\n L'équipe OnWine");
			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}


    public void sendWelcomeMail(Customer customer) {
        final String username = "webwinemania@gmail.com";
        final String password = "Webwinemania2016";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("webwinemania@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(customer.getMail()));
            message.setSubject("Votre inscription sur OnWine");
            message.setText("Bonjour " +customer.getCivility() + " " +customer.getLastname() +", "
                    + "\n\n Nous vous remercions pour votre inscription sur le site Internet OnWine.fr"
                    + "\n\n Rappel de votre identifiant : " +customer.getLogin()
                    + "\n\n Vous pourrez vous désinscrire de ce service à tout moment."
                    + "\n\n Nous vous remercions d'avoir utilisé www.OnWine.fr et vous souhaitons une agréable visite sur notre site."
                    + "\n\n Avec tous nos remerciements."
                    + "\n\n L'équipe OnWine");
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        
    }

}
