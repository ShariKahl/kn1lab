import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public class Send_Mail {
    public static void main(String[] args) {
        sendMail();
    }

    public static void sendMail() {
        try {
            // Initialize the mail session
            Properties properties = new Properties();
            Session session = Session.getDefaultInstance(properties);
            MimeMessage msg = new MimeMessage(session);

            // Set all of the neccesary data
            msg.setFrom("backofen@localhost");
            msg.setRecipients(Message.RecipientType.TO,
                    "labrat@localhost");
            msg.setSubject("Dies ist eine Erinnerung!");
            msg.setText("Deine Pizza ist jetzt fertig gebacken!");
            msg.setSentDate(new Date());

            // Send message
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
