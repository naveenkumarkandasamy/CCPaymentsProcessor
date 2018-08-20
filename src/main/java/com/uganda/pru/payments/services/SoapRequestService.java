package com.uganda.pru.payments.services;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.sun.xml.messaging.saaj.soap.impl.ElementImpl;
import com.uganda.pru.payments.model.ILInputModel;
import com.uganda.pru.payments.model.MSPContext;
import com.uganda.pru.payments.model.RCTCRTIREC;
import com.uganda.pru.payments.model.RCTCRTIREC.ADDITIONALFIELDS;
import com.uganda.pru.payments.model.RCTCRTIREC.S2610SFL;
import com.uganda.pru.payments.model.RCTCRTIREC.SFL;
import com.uganda.pru.payments.model.RCTCRTIREC.SFL.TCHQDATE;
import com.uganda.pru.payments.model.RCTCRTIREC.TRANDATEX;
import com.uganda.pru.payments.model.RequestParameter;
import com.uganda.pru.payments.model.RequestParameters;
@Service
@PropertySource("classpath:soap-connection.properties")
public class SoapRequestService {

	@Value("${UserId}")
	private String userId;
	@Value("${UserPassword}")
	private String userPassword;
	@Value("${Name}")
	private String name;
	@Value("${Value}")
	private String value;
	@Value("${ACTION}")
	private String action;
	@Value("${BANKCODE}")
	private String bankCode;
	@Value("${RFCODE}")
	private String rfCode;
	@Value("${ORIGCURR}")
	private String origcurr;
	@Value("${PAYTYPE}")
	private String paytype;
	@Value("${SCRATE}")
	private String scrate;
	@Value("${SACSCODE}")
	private String sacscode;
	@Value("${SACSTYPW}")
	private String sacstypw;
	@Value("${DISSEC}")
	private String dissec;
	@Value("${ReceiptURL}")
	private String receiptURL;
	
	@SuppressWarnings("rawtypes")
	public String sendSoapRequest(ILInputModel requestInput,String customerNumber){

		RCTCRTIREC rec = new RCTCRTIREC();
		TRANDATEX trandatex = new TRANDATEX();
		SFL sfl = new SFL();
		TCHQDATE tchqdate = new TCHQDATE();
		ADDITIONALFIELDS additionalFields = new ADDITIONALFIELDS();
		MSPContext mspcontext = new MSPContext();
		RequestParameters requestParameters = new RequestParameters();
		RequestParameter requestParameter = new RequestParameter();
		S2610SFL s2610sfl = new S2610SFL();
		try {			
			mspcontext.setUserId(userId);
			mspcontext.setUserPassword(userPassword);

			requestParameter.setName(name);
			requestParameter.setValue(value);
			requestParameters.getRequestParameter().add(requestParameter);

			mspcontext.setRequestParameters(requestParameters);

			rec.setMSPContext(mspcontext);

			rec.setACTION(action);
			rec.setBANKCODE(bankCode);
			rec.setRFCODE(rfCode);
			rec.setRFNUM(customerNumber);

			trandatex.setCCYY(new BigInteger(requestInput.getYear()));
			trandatex.setMM(new BigInteger(requestInput.getMonth()));
			trandatex.setDD(new BigInteger(requestInput.getDate()));

			rec.setTRANDATEX(trandatex);

			sfl.setBANKDESC01("");
			sfl.setBANKDESC02("");
			sfl.setBANKDESC03("");
			sfl.setBANKKEY("");
			sfl.setCHEQNO("");
			sfl.setDOCORIGAMT(new BigDecimal(requestInput.getCreditAmount()));
			s2610sfl.setACCTAMT(new BigDecimal(requestInput.getCreditAmount()));
			s2610sfl.setORIGAMT(new BigDecimal(requestInput.getCreditAmount()));
			sfl.setORIGCURR(origcurr);
			sfl.setPAYTYPE(paytype);
			sfl.setSCRATE(new BigDecimal(scrate));
			sfl.setZCHQTYP("");

			tchqdate.setCCYY(new BigInteger("9999"));
			tchqdate.setMM(new BigInteger("99"));
			tchqdate.setDD(new BigInteger("99"));

			sfl.setTCHQDATE(tchqdate);

			rec.setSFL(sfl);
			
			s2610sfl.setENTITY(requestInput.getPolicyNumber());
			s2610sfl.setSACSCODE(sacscode);
			s2610sfl.setSACSTYPW(sacstypw);

			rec.getS2610SFL().add(s2610sfl);

			additionalFields.setTRANCDE("");
			additionalFields.setTRANCDE("");
			additionalFields.setPRTIND("");
			additionalFields.setDISSEC(new BigInteger(dissec));
			additionalFields.setREMFLD1("");
			additionalFields.setREMFLD2("");

			rec.setADDITIONALFIELDS(additionalFields);
			String result =transform("input-to-output.xsl", ObjectToXML(rec));

			try {
				SOAPConnectionFactory sfc = SOAPConnectionFactory.newInstance();
				SOAPConnection connection = sfc.createConnection();
				InputStream is = new ByteArrayInputStream(result.getBytes());
				SOAPMessage request = MessageFactory.newInstance().createMessage(null, is);
				request.writeTo(System.out);
				//System.out.println();
				URL endpoint = new URL(receiptURL);
				SOAPMessage response = connection.call(request, endpoint);
				System.out.println(response);
				response.writeTo(System.out);
				System.out.println();
				Iterator updates = response.getSOAPBody().getChildElements();
	            while (updates.hasNext()) {
	                System.out.println();
	                // The listing and its ID
	                SOAPElement e = (SOAPElement)updates.next();
	                QName dateTimeStamp=new QName("RECEIPT");
            		Iterator it = e.getChildElements(dateTimeStamp);
                    while(it.hasNext())
                    {
                    	ElementImpl receiptNumber = (ElementImpl)it.next();
                    	System.out.println(receiptNumber.getValue());
                    	return receiptNumber.getValue();
                    }
	            }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;

	}

	public static String ObjectToXML(RCTCRTIREC rec) {

		StringWriter sw = null;
		try {
			JAXBContext context = JAXBContext.newInstance(RCTCRTIREC.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			sw = new StringWriter();
			m.marshal(rec, sw);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return sw.toString();
	}

	public String transform(String xslFileName, String source) {
		String result = null;
		String xslFile = getClass().getResource("/xsl/" + xslFileName).getFile();
		try (StringReader reader = new StringReader(source); StringWriter writer = new StringWriter()) {

			StreamSource xslCode = new StreamSource(new File(xslFile));
			StreamSource input = new StreamSource(reader);
			StreamResult output = new StreamResult(writer);

			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer(xslCode);
			t.transform(input, output);
			result = writer.toString();
		} catch (TransformerException | IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
