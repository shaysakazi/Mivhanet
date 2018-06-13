package sample.Model.Email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Idosakazi on 30/12/2017.
 */
public class SendEmail {

    String username;
    String emailToSend;
    String userNameEveryT4R;
    String passwordEveryT4R ;

    public SendEmail(String name, String Email) {
        emailToSend = Email;
        username = name;
        userNameEveryT4R = "nituz2018@gmail.com";
        passwordEveryT4R = "nituz!@#$";
    }

    public void send() {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userNameEveryT4R, passwordEveryT4R);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userNameEveryT4R));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailToSend));
            message.setSubject("Hello "+ username +" Your account has been successfully created!");
            message.setText("Dear "+ username+", "
                    + "\n\n Your password is 1234!"
                    + "\n\n Please log in with initial password that we gave you,and then choose any password that will be 8 characters or more" );

            Transport.send(message);

            //System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
