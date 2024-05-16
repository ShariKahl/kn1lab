import java.util.Properties;
import javax.mail.*;

public class Receive_Mail {
	public static void main(String[] args) throws Exception {
		fetchMail();
	}

	public static void fetchMail() {
		try {
			// Initialize mail session
			Properties properties = new Properties();
			Session session = Session.getDefaultInstance(properties);

			// Connect to POP3 server
			Store store = session.getStore("pop3");
			store.connect("localhost", "labrat", "kn1lab");

			// Open the INBOX folder
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);

			// Fetch the messages in the folder
			Message[] messages = folder.getMessages();

			System.out.println("Log::Messages were fetched successfully\n");
			System.out.println("[Mails]		" + messages.length);

			// Pretty print the messages in the folder
			for (var message : messages) {
				System.out.println("-----------------------------------------------------------");
				System.out.println("[ID]		" + message.getMessageNumber());
				System.out.println("[Sender]	" + message.getFrom()[0]);
				System.out.println("[Subject]	" + message.getSubject());
				System.out.println("[Sent Date]	" + message.getSentDate().toString());
				System.out.print("[Body]		" + message.getContent().toString());

				// The last element should have a trailing delimiter (so it looks better)
				if (message.getMessageNumber() == messages.length) {
					System.out.println("-----------------------------------------------------------");
				}
			}

			folder.close();
			store.close();

			System.out.println("\nLog::Closed connection.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}