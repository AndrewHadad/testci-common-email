package org.apache.commons.mail;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Session;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EmailTest {
	//Initializations of all variables that are used in all the test cases
	public static final String[] TEST_EMAILS = {"andrew@hadad.com", "maxine@caulfield.com", "sean@diaz.com"};
	Map<String, String> testHeader = new HashMap<String, String>();
	String testEmail = "andrew@hadad.com";
	String name = "Andrew Hadad";
	String value = "09292001";
	String hostname;
	Date testDate = new Date();
	Properties prop = new Properties();
	Session session;
	int sct = 0000;
	
	//Concrete Email class for testing
	private EmailConcrete email;
	
	@Before
	public void setUpEmailTest() {
		email = new EmailConcrete();
	}
	
	@After
	public void tearDownEmailTest() {
		
	}
	
	//Test addBcc(String... emails) function
	@Test
	public void testAddBcc() throws Exception {
		email.addBcc(TEST_EMAILS);
		assertEquals(3, email.getBccAddresses().size());
	}
	
	//Test addCc(String email) function
	@Test
	public void testAddCc() throws Exception {
		email.addCc(testEmail);
		assertEquals(1, email.getCcAddresses().size());
	}
	
	//Test addHeader(String name, String value) function (P1: name and value are both not null)
	@Test
	public void testAddHeaderNotNull() throws Exception {
		testHeader.put(name, value);
		email.addHeader(name, value);
		assertEquals(email.headers, testHeader);
	}
	
	//Test addHeader(String name, String value) function (P2: only name is null)
//	@Test
//	public void testAddHeaderNameNull() throws Exception {
//		name = null;
//		value = "09292001";
//		testHeader.put(name, value);
//		
//		email.addHeader(name, value);
//		assertEquals(email.headers, testHeader);
//	}
	
//	//Test addHeader(String name, String value) function (P3: only value is null)
//	@Test
//	public void testAddHeaderValueNull() throws Exception {
//		name = "Andrew Hadad";
//		value = null;
//		testHeader.put(name, value);
//		
//		email.addHeader(name, value);
//		assertEquals(email.headers, testHeader);
//	}
	
	//Test addReplyTo(String email, String name) function
	@Test
	public void testAddReplyTo() throws Exception {
		email.addReplyTo(testEmail, name);
		assertEquals(1, email.getReplyToAddresses().size());
	}
	
//	//Test buildMimeMessage() function
//	@Test
//	public void testBuildMimeMessage() throws Exception {
//		email.setSubject("Test Subject");
//		email.setCharset("UTF8");
//		email.updateContentType(EmailConstants.TEXT_PLAIN);
//		session = Session.getInstance(prop);
//		email.setMailSession(session);
//		email.setFrom(testEmail);
//		email.addTo(testEmail);
//		email.addBcc(testEmail);
//		email.addCc(testEmail);
//		email.addReplyTo(testEmail);
//		email.addHeader(name, value);
//		email.setPopBeforeSmtp(true, hostname, name, name);
//		email.createMimeMessage(session);
//		email.setContent(new String(), new String());
//		email.buildMimeMessage();
//		assertEquals("Test Subject", email.getSubject());
//	}
	
	//Test getHostName() function (P1: with session)
	@Test
	public void getHostNameTest() {
		email.setHostName("localhost");
		prop.setProperty(email.MAIL_HOST, "localhost");
		session = Session.getInstance(prop);
		email.setMailSession(session);
		
		hostname = email.getHostName();
		assertEquals("localhost", hostname);
	}
	
	//Test getHostName() function (P2: without session)
	@Test
	public void getHostNameTestWithoutSession() {
		email.setHostName("localhost");
		hostname = email.getHostName();
		assertEquals("localhost", hostname);
	}
	
	//Test getMailSession() function (P1: session is not null)
	@Test
	public void testGetMailSession() throws Exception {
		prop.setProperty(email.MAIL_PORT, "8080");
		session = Session.getInstance(prop);
		email.setMailSession(session);
		assertEquals(session, email.getMailSession());
	}
	
//	//Test getMailSession() function (P2: session is null)
//	@Test
//	public void testGetMailSessionNull() throws Exception {
//		email.setHostName("localhost");
//		assertEquals(null, email.getMailSession());
//	}
	
//	//Test getMailSession() function (P3: hostname is null)
//	@Test
//	public void testGetMailSessionHostNameNull() throws Exception {
//		email.setHostName(null);
//		assertEquals(hostname, email.getMailSession());
//	}
	
	//Test getSentDate() function (P1: date is not null)
	@Test
	public void testGetSentDateNotNull() throws Exception {
		email.setSentDate(testDate);
		email.getSentDate();
		assertEquals(testDate, email.getSentDate());
	}
	
	//Test getSentDate() function (P2: date is null)
	@Test
	public void testGetSentDateNull() throws Exception {
		testDate = null;
		email.setSentDate(testDate);
		email.getSentDate();
		assertEquals(testDate, null);
	}
	
	//Test getSocketConnectionTimeout() function
	@Test
	public void testGetSocketConnectionTimeout() throws Exception {
		email.setSocketConnectionTimeout(sct);
		email.getSocketConnectionTimeout();
		assertEquals(sct, email.getSocketConnectionTimeout());
	}
	
	//Test setFrom(String email) function
	@Test
	public void testSetFrom() throws Exception {
		email.setFrom(testEmail);
		assertEquals(testEmail, email.getFromAddress().getAddress());
	}
}