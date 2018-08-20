package com.uganda.pru.payments.services;

import java.net.URL;
import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.sun.xml.messaging.saaj.soap.impl.ElementImpl;

@Service
@PropertySource("classpath:soap-connection.properties")
public class SoapEnquiryRequest {

	@Value("${UserId}")
	private String userId;
	@Value("${UserPassword}")
	private String userPassword;
	@Value("${Name}")
	private String name;
	@Value("${Value}")
	private String value;
	@Value("${CustomerEnquiryURL}")
	private String customerEnquiryURL;

	private static final Logger logger = LogManager.getLogger(SoapEnquiryRequest.class);

	@SuppressWarnings("rawtypes")
	public String sendSoapEnquiryRequest(String policyNum) {
		try {
			SOAPConnectionFactory sfc = SOAPConnectionFactory.newInstance();
			SOAPConnection connection = sfc.createConnection();

			MessageFactory mf = MessageFactory.newInstance();
			SOAPMessage sm = mf.createMessage();
			SOAPEnvelope env = sm.getSOAPPart().getEnvelope();
			env.addNamespaceDeclaration("nbs", "http://www.csc.smart/bo/schemas/NBSENQI");
			env.addNamespaceDeclaration("msp", "http://www.csc.smart/msp/schemas/MSPContext");
			SOAPHeader sh = sm.getSOAPHeader();
			SOAPBody sb = sm.getSOAPBody();
			sh.detachNode();

			QName bodyName = env.createQName("NBSENQI_REC", "nbs");
			SOAPBodyElement bodyElement = sb.addBodyElement(bodyName);
			QName bodyName1 = env.createQName("MSPContext", "msp");
			SOAPElement soapElement = bodyElement.addChildElement(bodyName1);
			QName bodyName2 = env.createQName("UserId", "msp");
			SOAPElement soapElement1 = soapElement.addChildElement(bodyName2);
			soapElement1.setValue(userId);
			QName bodyName3 = env.createQName("UserPassword", "msp");
			SOAPElement soapElement3 = soapElement.addChildElement(bodyName3);
			soapElement3.setValue(userPassword);
			QName bodyName4 = env.createQName("RequestParameters", "msp");
			SOAPElement soapElement4 = soapElement.addChildElement(bodyName4);
			QName bodyName5 = env.createQName("RequestParameter", "msp");
			SOAPElement soapElement5 = soapElement4.addChildElement(bodyName5);
			soapElement5.setAttribute("name", name);
			soapElement5.setAttribute("value", value);
			QName qn = new QName("CHDRNUM");
			SOAPElement soapElement6 = bodyElement.addChildElement(qn);
			soapElement6.addTextNode(policyNum);

			logger.error("\n Soap Request:\n");
			sm.writeTo(System.out);
			System.out.println();

			URL endpoint = new URL(customerEnquiryURL);
			SOAPMessage response = connection.call(sm, endpoint);
			logger.info("Response Is" + response);
			response.writeTo(System.out);
			Iterator updates = response.getSOAPBody().getChildElements();
			while (updates.hasNext()) {
				System.out.println();
				// The listing and its ID
				SOAPElement update = (SOAPElement) updates.next();
				QName name = new QName("CONTRACT_HEADER_DETAILS");
				Iterator i = update.getChildElements(name);
				while (i.hasNext()) {
					SOAPElement e = (SOAPElement) i.next();
					if (e.hasChildNodes()) {
						QName dateTimeStamp = new QName("PAYRNUM");
						Iterator it = e.getChildElements(dateTimeStamp);
						while (it.hasNext()) {
							ElementImpl payerNum = (ElementImpl) it.next();
							System.out.println(payerNum.getValue());
							return payerNum.getValue();
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
