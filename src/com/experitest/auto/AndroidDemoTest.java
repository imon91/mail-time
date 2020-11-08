package com.experitest.auto;

import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Calendar;
import java.util.Formatter;

public class AndroidDemoTest  extends BaseTest {
	protected AndroidDriver<AndroidElement> driver = null;
	
	@Test
	public void setUp() throws Exception  {
		 dc.setCapability(MobileCapabilityType.UDID, "PDAGAA48C1801900");      
	        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.zhiliaoapp.musically");
	        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.ss.android.ugc.aweme.splash.SplashActivity");
	        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
	        driver.setLogLevel(Level.INFO);

	        Thread.sleep(5000);
	}
	
	@Test
	public void test(){
		
		 //sendPDFReportByGMail("tasnim.hosen.ewu@gmail.com", "*******", "tasnim.hosen.upwork@gmail.com", "PDF Report", "testt");
		time();
		
	}
	
	
	public void time(){
		
		  Formatter fmt = new Formatter();
	      Calendar cal = Calendar.getInstance();
	      fmt = new Formatter();
	      fmt.format("%tl", cal);
	      System.out.println(fmt);
	}
	
	private static void sendPDFReportByGMail(String from, String pass, String to, String subject, String body) {

		Properties props = System.getProperties();

		String host = "smtp.gmail.com";

		props.put("mail.smtp.starttls.enable", "true");

		props.put("mail.smtp.host", host);
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		props.put("mail.smtp.user", from);

		props.put("mail.smtp.password", pass);

		props.put("mail.smtp.port", "587");

		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);

		MimeMessage message = new MimeMessage(session);

		try {

		    //Set from address

		message.setFrom(new InternetAddress(from));

		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		//Set subject

		message.setSubject(subject);

		message.setText(body);

		BodyPart objMessageBodyPart = new MimeBodyPart();

		objMessageBodyPart.setText("Please Find The Attached Report File!");

		Multipart multipart = new MimeMultipart();

		multipart.addBodyPart(objMessageBodyPart);

		objMessageBodyPart = new MimeBodyPart();

		//Set path to the pdf report file

		String filename = System.getProperty("user.dir")+"\\create_table.docx";

		//Create data source to attach the file in mail

		DataSource source = new FileDataSource(filename);

		objMessageBodyPart.setDataHandler(new DataHandler(source));

		objMessageBodyPart.setFileName(filename);

		multipart.addBodyPart(objMessageBodyPart);

		message.setContent(multipart);

		Transport transport = session.getTransport("smtp");

		transport.connect(host, from, pass);

		transport.sendMessage(message, message.getAllRecipients());

		transport.close();

		}

		catch (AddressException ae) {

		ae.printStackTrace();

		}

		catch (MessagingException me) {

		me.printStackTrace();

		}

		}

	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	
	
	
}
