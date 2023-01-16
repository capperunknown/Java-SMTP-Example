//lines 2-23 are imports that are required to send and email or text
import java.util.Scanner;  
import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import javax.mail.internet.MimeBodyPart;

public class emailer {

	public static void sendEmail(){

		final String username = "REDACTED";// the email address of the sender
		final String password = "REDACTED";// password for senders email
				
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, // lines 40-45 are for verifying the password of the email/text sender
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
                
        System.out.println("Enter the recipients' addresses separated with a comma. When finished hit enter");
        String response = scan.nextLine();
        response = response.replaceAll(", ",",");
                
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));// who is the email from
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(response));// who is the email going to
        
        System.out.println("Enter the subject of the email");
        String subject = scan.nextLine();
        
        System.out.println("Do you want to attach a file 1.YES 2.NO");//gotta work on file upload option
        int attatch = scan.nextInt();
        scan.nextLine();

        
        System.out.println("Enter the body of the email");
        String body = scan.nextLine();
        
        message.setSubject(subject);//this is the email/text subject field
        message.setText(body);//this is the email/text body field	
        
        Transport.send(message);
        
        System.out.println("Message Was Sent Succesfully");
        System.out.println("____________________________");
        System.out.println("TO: " + response);
        System.out.println("FROM: " + username);
        System.out.println("____________________________");
        System.out.println("Subject: "+subject);
        System.out.println("Body: ");
        System.out.println(body);
				
	}
	
	public static void main(String[] args){
		sendEmail();
	}
}
