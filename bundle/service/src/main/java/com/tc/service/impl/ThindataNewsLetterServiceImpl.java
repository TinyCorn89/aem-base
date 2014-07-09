package com.tc.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.List;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tc.service.api.NewsLetterService;

@Component(label = "Thindata News Letter Service", description = "Thindata news letter service for subscribe, unsubscribe and broadcast emails", immediate = true, metatype = true)
@Properties({
		@Property(name = "service.pid", value = "com.tc.service.api.NewsLetterService", propertyPrivate = false),
		@Property(name = "service.description", value = "Provides the Application config variables", propertyPrivate = false) })
@Service({ NewsLetterService.class })
public class ThindataNewsLetterServiceImpl implements NewsLetterService {

	private static final String WEB_SERVICE_NAMESPACE = "wsi";

	@Property(name = "listQName", description = "Maillist webservice namespace", value = "https://login.thindata.com/ws/transcontinental/listrecipientadmin")
	private String listQName = "https://login.thindata.com/ws/transcontinental/listrecipientadmin";

	@Property(name = "mailListServerURL", description = "Maillist webservice URL", value = "https://ws.e.transcontinentalmedia.com/ListRecipientAdmin.asmx")
	private String mailListServerURL = "https://ws.e.transcontinentalmedia.com/ListRecipientAdmin.asmx";

	@Property(name = "broadcastQName", description = "Broadcast webservice namespace", value = "https://login.thindata.com/ws/transcontinental/broadcastadmin")
	private String broadcastQName = "https://login.thindata.com/ws/transcontinental/broadcastadmin";

	@Property(name = "broadcastServerURL", description = "Broadcast webservice URL", value = "https://ws.e.transcontinentalmedia.com/BroadcastAdmin.asmx")
	private String broadcastServerURL = "https://ws.e.transcontinentalmedia.com/BroadcastAdmin.asmx";

	@Property(name = "companyName", description = "Company name to be used during authentication", value = "tcm - hebdos")
	private String companyName = "tcm - hebdos";

	@Property(name = "userName", description = "User name to be used during authentication", value = "APIUser")
	private String userName = "APIUser";

	@Property(name = "password", description = "Password to be used during authentication", value = "shepard2163")
	private String password = "shepard2163";

	/*public static void main(String a[]) throws Exception  {
		ThindataNewsLetterServiceImpl service = new ThindataNewsLetterServiceImpl();
		Date d1 = new Date();
		String dStr = new SimpleDateFormat("2014-7-3 4:0:0").format(d1);
		
		
		
		String dateFormatStr = "yyyy-MM-dd";
		String timeFormatStr = "HH:mm:ss";
		
		Date dateObj = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dStr);
		String formattedDate = new SimpleDateFormat(dateFormatStr).format(dateObj);
		String formattedTime = new SimpleDateFormat(timeFormatStr).format(dateObj);
		String formattedDateTime = formattedDate + "T" + formattedTime;
		System.out.println(formattedDateTime);
		//service.schedule(new SimpleDateFormat("2014-07-03 13:15:00").format(d1), "Test schedule1", "Email scheduled at 2014-07-03 13:15:00", "UNIS List 1");
		
	}*/
	
	/**
	 * logger object for handling log messages.
	 */
	private static final Logger LOG = LoggerFactory
			.getLogger(ThindataNewsLetterServiceImpl.class);

	@SuppressWarnings("unchecked")
	protected final void activate(final ComponentContext componentContext) {
		LOG.info("inside activate of {}", this.getClass());
		final Dictionary<String, String> properties = componentContext
				.getProperties();
		listQName = properties.get("listQName");
		mailListServerURL = properties.get("mailListServerURL");
		broadcastQName = properties.get("broadcastQName");
		broadcastServerURL = properties.get("broadcastServerURL");
		companyName = properties.get("companyName");
		userName = properties.get("userName");
		password = properties.get("password");

		LOG.info("mailListNamespace={}", listQName);
		LOG.info("mailListServerURL={}", mailListServerURL);
		LOG.info("broadcastQName={}", broadcastQName);
		LOG.info("broadcastServerURL={}", broadcastServerURL);
		LOG.info("companyName={}", companyName);
		LOG.info("userName={}", userName);
		LOG.info("password={}", password);

	}

		public void broadcast(String subject, String htmlContent,
			String mailListName) {

		try {
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
					.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory
					.createConnection();

			SOAPMessage soapResponse = soapConnection.call(
					createBroadcastSOAPRequest(subject, htmlContent,
							mailListName), broadcastServerURL);
			int broadcastId = getBroadcastId(soapResponse);
			LOG.info("Broadcast id = " + broadcastId);
			logSOAPResponse(soapResponse);

			sendBroadcast(broadcastId);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void sendBroadcast(int broadcastId) {
		try {
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
					.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory
					.createConnection();

			SOAPMessage soapResponse = soapConnection.call(
					createSendBroadcastSOAPRequest(broadcastId),
					broadcastServerURL);

			logSOAPResponse(soapResponse);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private SOAPMessage createSendBroadcastSOAPRequest(int broadcastId)
			throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();

		// soap header
		SOAPHeader soaph = envelope.getHeader();

		SOAPFactory soapFactory = SOAPFactory.newInstance();
		Name headerName = soapFactory.createName("WebServiceCredentials",
				WEB_SERVICE_NAMESPACE, broadcastQName);
		SOAPHeaderElement cred = soaph.addHeaderElement(headerName);

		addHeader(cred, "CompanyName", companyName);
		addHeader(cred, "Username", userName);
		addHeader(cred, "Password", password);

		Name bodyName = soapFactory.createName("SendBroadcast ", WEB_SERVICE_NAMESPACE,
				broadcastQName);

		SOAPBody soapBody = envelope.getBody();
		SOAPElement soapBodyElem = soapBody.addChildElement(bodyName);
		SOAPElement broadcastIdElement = soapBodyElem.addChildElement(
				"BroadcastID", WEB_SERVICE_NAMESPACE);
		broadcastIdElement.addTextNode(Integer.toString(broadcastId));

		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", broadcastQName + "/SendBroadcast");

		soapMessage.saveChanges();

		/* Print the request message */
		logSoapMessage(soapMessage);

		return soapMessage;
	}

	private void logSoapMessage(SOAPMessage soapMessage) throws SOAPException,
			IOException, UnsupportedEncodingException {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		soapMessage.writeTo(bout);
		String msg = bout.toString("UTF-8");
		LOG.info("SOAP Message = " + msg);
	}

	private SOAPMessage createBroadcastSOAPRequest(String subject,
			String htmlContent, String mailListName) throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();

		// soap header
		SOAPHeader soaph = envelope.getHeader();

		SOAPFactory soapFactory = SOAPFactory.newInstance();
		Name headerName = soapFactory.createName("WebServiceCredentials",
				WEB_SERVICE_NAMESPACE, broadcastQName);
		SOAPHeaderElement cred = soaph.addHeaderElement(headerName);

		addHeader(cred, "CompanyName", companyName);
		addHeader(cred, "Username", userName);
		addHeader(cred, "Password", password);

		Name bodyName = soapFactory.createName("CreateBroadcast", WEB_SERVICE_NAMESPACE,
				broadcastQName);

		SOAPBody soapBody = envelope.getBody();
		SOAPElement soapBodyElem = soapBody.addChildElement(bodyName);
		SOAPElement emailAddressSoapElement = soapBodyElem.addChildElement(
				"CampaignID", WEB_SERVICE_NAMESPACE);
		emailAddressSoapElement.addTextNode("0");

		SOAPElement listNameSoapElement = soapBodyElem.addChildElement(
				"ListName", WEB_SERVICE_NAMESPACE);
		listNameSoapElement.addTextNode(mailListName);

		SOAPElement mimeTypeSoapElement = soapBodyElem.addChildElement("Type",
				WEB_SERVICE_NAMESPACE);
		mimeTypeSoapElement.addTextNode("Normal");

		/*
		 * This parameter must be populated if the SendToSubscribers input parameter 
		 * has a value of “false”, otherwise this parameter can be omitted. 
		 * If populated, only one BroadcastRecipient element should be included in the array. 
		 * Only the DatabaseID and SendToSubscribers (0 = no, 1 = yes) 
		 * properties of the element should be populated.
		 */

		/*
		 * SOAPElement broadcastRecipientsSoapElement =
		 * soapBodyElem.addChildElement("BroadcastRecipients", "wsi");
		 * SOAPElement broadcastRecipientSoapElement =
		 * broadcastRecipientsSoapElement.addChildElement("BroadcastRecipient",
		 * "wsi"); SOAPElement databaseElement =
		 * broadcastRecipientSoapElement.addChildElement("DatabaseName", "wsi");
		 * databaseElement.addTextNode(database);
		 */
		
		 

		SOAPElement sendToSubscribersElement = soapBodyElem.addChildElement(
				"SendToSubscribers", WEB_SERVICE_NAMESPACE);
		sendToSubscribersElement.addTextNode("true");

		SOAPElement subjectElement = soapBodyElem.addChildElement("Subject",
				WEB_SERVICE_NAMESPACE);
		subjectElement.addTextNode(subject);

		SOAPElement htmlContentElement = soapBodyElem.addChildElement(
				"HTMLContent", WEB_SERVICE_NAMESPACE);
		htmlContentElement.addTextNode(htmlContent);

		SOAPElement sendDateTimeElement = soapBodyElem.addChildElement(
				"SendDateTime", WEB_SERVICE_NAMESPACE);
		sendDateTimeElement.addTextNode("2008-01-31T01:01:01");

		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", broadcastQName + "/CreateBroadcast");

		soapMessage.saveChanges();

		/* Print the request message */
		logSoapMessage(soapMessage);
		return soapMessage;
	}

	@Override
	public void subscribe(String emailId, String listName) {
		try {
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
					.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory
					.createConnection();

			SOAPMessage soapResponse = soapConnection.call(
					createSubscribeSOAPRequest(emailId, listName),
					mailListServerURL);
			logSOAPResponse(soapResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unSubscribe(String emailId, String listName) {
		try {
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
					.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory
					.createConnection();

			SOAPMessage soapResponse = soapConnection.call(
					createUnsubscribeSOAPRequest(emailId, listName),
					mailListServerURL);
			logSOAPResponse(soapResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void getSubscriptions(String emailId, List<String> mailList) {
		try {
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
					.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory
					.createConnection();

			
			SOAPMessage soapResponse = soapConnection.call(
					createGetEmailSubscriptionsSOAPRequest(emailId, mailList),
					mailListServerURL);
			logSOAPResponse(soapResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void addHeader(SOAPHeaderElement head, String name, String value)
			throws SOAPException {
		SOAPElement element = head.addChildElement(name, WEB_SERVICE_NAMESPACE);
		element.addTextNode(value);

	}

	private SOAPMessage createSubscribeSOAPRequest(String emailId,
			String listName) throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();

		// soap header
		SOAPHeader soaph = envelope.getHeader();

		SOAPFactory soapFactory = SOAPFactory.newInstance();
		Name headerName = soapFactory.createName("WebServiceCredentials",
				WEB_SERVICE_NAMESPACE, listQName);
		SOAPHeaderElement cred = soaph.addHeaderElement(headerName);

		addHeader(cred, "CompanyName", companyName);
		addHeader(cred, "Username", userName);
		addHeader(cred, "Password", password);

		Name bodyName = soapFactory.createName("SubscribeEmail", WEB_SERVICE_NAMESPACE,
				listQName);

		SOAPBody soapBody = envelope.getBody();
		SOAPElement soapBodyElem = soapBody.addChildElement(bodyName);
		SOAPElement emailAddressSoapElement = soapBodyElem.addChildElement(
				"EmailAddress", WEB_SERVICE_NAMESPACE);
		emailAddressSoapElement.addTextNode(emailId);
		SOAPElement listNameSoapElement = soapBodyElem.addChildElement(
				"ListName", WEB_SERVICE_NAMESPACE);
		listNameSoapElement.addTextNode(listName);
		// addBody(listNameSoapElement, "string", listName);

		SOAPElement mimeTypeSoapElement = soapBodyElem.addChildElement(
				"MimeType", WEB_SERVICE_NAMESPACE);
		mimeTypeSoapElement.addTextNode("Html");

		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", listQName + "/SubscribeEmail");

		soapMessage.saveChanges();

		/* Print the request message */
		logSoapMessage(soapMessage);
		return soapMessage;
	}

	private SOAPMessage createUnsubscribeSOAPRequest(String emailId,
			String listName) throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();

		// soap header
		SOAPHeader soaph = envelope.getHeader();

		SOAPFactory soapFactory = SOAPFactory.newInstance();
		Name headerName = soapFactory.createName("WebServiceCredentials",
				WEB_SERVICE_NAMESPACE, listQName);
		SOAPHeaderElement cred = soaph.addHeaderElement(headerName);

		addHeader(cred, "CompanyName", companyName);
		addHeader(cred, "Username", userName);
		addHeader(cred, "Password", password);

		Name bodyName = soapFactory.createName("UnsubscribeEmail", WEB_SERVICE_NAMESPACE,
				listQName);

		SOAPBody soapBody = envelope.getBody();
		SOAPElement soapBodyElem = soapBody.addChildElement(bodyName);
		SOAPElement emailAddressSoapElement = soapBodyElem.addChildElement(
				"EmailAddress", WEB_SERVICE_NAMESPACE);
		emailAddressSoapElement.addTextNode(emailId);
		SOAPElement listNameSoapElement = soapBodyElem.addChildElement(
				"ListName", WEB_SERVICE_NAMESPACE);
		listNameSoapElement.addTextNode(listName);

		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", listQName + "/UnsubscribeEmail");

		soapMessage.saveChanges();

		/* Print the request message */
		logSoapMessage(soapMessage);
		return soapMessage;
	}

	private SOAPMessage createGetEmailSubscriptionsSOAPRequest(String emailId, List<String> mailList)
			throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();

		// soap header
		SOAPHeader soaph = envelope.getHeader();

		SOAPFactory soapFactory = SOAPFactory.newInstance();
		Name headerName = soapFactory.createName("WebServiceCredentials",
				WEB_SERVICE_NAMESPACE, listQName);
		SOAPHeaderElement cred = soaph.addHeaderElement(headerName);

		addHeader(cred, "CompanyName", companyName);
		addHeader(cred, "Username", userName);
		addHeader(cred, "Password", password);

		Name bodyName = soapFactory.createName("GetEmailSubscriptions ", WEB_SERVICE_NAMESPACE,
				listQName);

		SOAPBody soapBody = envelope.getBody();
		SOAPElement soapBodyElem = soapBody.addChildElement(bodyName);
		SOAPElement emailElement = soapBodyElem.addChildElement("EmailAddress",
				WEB_SERVICE_NAMESPACE);
		emailElement.addTextNode(emailId);
		/*
		 * SOAPElement s2 = soapBodyElem.addChildElement("ListNames", "wsi");
		 * List<String> mailList = user.getMailList(); for (String mailElement :
		 * mailList) { addBody(s2, "string", mailElement); }
		 */

		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", listQName + "/GetEmailSubscriptions");

		soapMessage.saveChanges();

		/* Print the request message */
		logSoapMessage(soapMessage);
		return soapMessage;
	}

	private int getBroadcastId(SOAPMessage soapResponse) throws SOAPException {
		NodeList elements = soapResponse.getSOAPBody().getElementsByTagName(
				"BroadcastID");
		if (elements != null) {
			int length = elements.getLength();
			for (int i = 0; i < length; i++) {
				Node node = elements.item(i);
				return Integer.parseInt(node.getTextContent());
			}
		}

		return -1;
	}

	private static void logSOAPResponse(SOAPMessage soapResponse)
			throws Exception {
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		Source sourceContent = soapResponse.getSOAPPart().getContent();
		LOG.debug("\nResponse SOAP Message = ");
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		StreamResult result = new StreamResult(os);

		transformer.transform(sourceContent, result);
		String resultStr = os.toString("UTF-8");
		LOG.info(resultStr);
	}

	@Override
	public void schedule(String dateTime, String subject, String htmlContent,
			String mailListName) {
		
		try {
			
			
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
					.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory
					.createConnection();

			SOAPMessage soapResponse = soapConnection.call(
					createBroadcastSOAPRequest(subject, htmlContent,
							mailListName), broadcastServerURL);
			int broadcastId = getBroadcastId(soapResponse);
			LOG.info("Scheduled Broadcast id {} ", broadcastId);
			
			logSOAPResponse(soapResponse);
			scheduleBroadcast(broadcastId, dateTime);
			

		} catch (Exception e) {
			LOG.error("Error while scheduling" , e);
		}
	}
	
	private void scheduleBroadcast(int broadcastId, String dateTime) {
		try {
			LOG.info("Scheduling broadcastId {} at {}", broadcastId, dateTime);
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
					.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory
					.createConnection();

			SOAPMessage soapResponse = soapConnection.call(
					createSceduleBroadcastSOAPRequest(broadcastId, dateTime),
					broadcastServerURL);

			logSOAPResponse(soapResponse);

		} catch (Exception e) {
			LOG.error("Error while scheduling" , e);
		}
	}
	
	private SOAPMessage createSceduleBroadcastSOAPRequest(int broadcastId, String dateTime)
			throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();

		// soap header
		SOAPHeader soaph = envelope.getHeader();

		SOAPFactory soapFactory = SOAPFactory.newInstance();
		Name headerName = soapFactory.createName("WebServiceCredentials",
				WEB_SERVICE_NAMESPACE, broadcastQName);
		SOAPHeaderElement cred = soaph.addHeaderElement(headerName);

		addHeader(cred, "CompanyName", companyName);
		addHeader(cred, "Username", userName);
		addHeader(cred, "Password", password);

		Name bodyName = soapFactory.createName("ScheduleBroadcast ", WEB_SERVICE_NAMESPACE,
				broadcastQName);

		SOAPBody soapBody = envelope.getBody();
		SOAPElement soapBodyElem = soapBody.addChildElement(bodyName);
		SOAPElement broadcastIdElement = soapBodyElem.addChildElement(
				"BroadcastID", WEB_SERVICE_NAMESPACE);
		broadcastIdElement.addTextNode(Integer.toString(broadcastId));
		
		SOAPElement dateTimeElement = soapBodyElem.addChildElement(
				"SendDateTime", WEB_SERVICE_NAMESPACE);
		//yyyy-mm-ddThh:mm:ss
		String dateFormatStr = "yyyy:MM:dd";
		String timeFormatStr = "HH:mm:ss:SSS";

        Date dateObj = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime);
		String formattedDate = new SimpleDateFormat(dateFormatStr).format(dateObj);
		String formattedTime = new SimpleDateFormat(timeFormatStr).format(dateObj);
		String formattedDateTime = formattedDate + "T" + formattedTime;
		LOG.info("formattedDateTime=" + formattedDateTime);
		dateTimeElement.addTextNode(formattedDateTime);

		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", broadcastQName + "/ScheduleBroadcast");

		soapMessage.saveChanges();

		/* Print the request message */
		logSoapMessage(soapMessage);

		return soapMessage;
	}

}
