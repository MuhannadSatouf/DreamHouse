package src.Models;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailSend {


    public EmailSend() {

    }

    public void sendEmail(String to, String messageText) {
        try {
            // The agency's email  is dreamhousesup@gmail.com and the password: asd112211

            String host = "smtp.gmail.com";
            String from = "dreamhousesup@gmail.com";
            String password = "asd112211";
            String subject = "Welcome to DREAM HOUSE";
            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.user", from);
            props.put("mail.smtp.password", password);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

    // this email only for testing, it will replace with customers and employee email then.
            String toEmail = "mohanad2satouf@gmail.com";

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(false);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText(messageText);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, from, password);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("message send successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
