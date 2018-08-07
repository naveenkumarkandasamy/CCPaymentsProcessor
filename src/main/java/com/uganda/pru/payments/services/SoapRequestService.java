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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

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

import com.sun.xml.messaging.saaj.soap.impl.ElementImpl;
import com.uganda.pru.payments.model.MSPContext;
import com.uganda.pru.payments.model.RCTCRTIREC;
import com.uganda.pru.payments.model.RequestParameter;
import com.uganda.pru.payments.model.RequestParameters;
import com.uganda.pru.payments.model.ILSoapModel;
import com.uganda.pru.payments.model.RCTCRTIREC.ADDITIONALFIELDS;
import com.uganda.pru.payments.model.RCTCRTIREC.S2610SFL;
import com.uganda.pru.payments.model.RCTCRTIREC.SFL;
import com.uganda.pru.payments.model.RCTCRTIREC.TRANDATEX;
import com.uganda.pru.payments.model.RCTCRTIREC.SFL.TCHQDATE;

public class SoapRequestService {

	

	public void sendSoapRequest(ILSoapModel requestInput){

		Properties prop = new Properties();
		InputStream input = null;
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
			
			
			input =getClass().getResourceAsStream("/application.properties");
			prop.load(input);

			String policyNumber = requestInput.getProductDescription().split("/").length>1?requestInput.getProductDescription().split("/")[2]:requestInput.getProductDescription().substring(6, requestInput.getProductDescription().length()-4);
			String date[]=new String[3];
			if(requestInput.getTransactionDate().split("/").length>1)
			{
				date= requestInput.getTransactionDate().split("/");
			}
			else
			{
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
				Date dateFormatted = null;
				try {
					dateFormatted = simpleDateFormat.parse(requestInput.getTransactionDate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				date[0]=String.valueOf(dateFormatted.getDate());
				date[1]=String.valueOf(dateFormatted.getMonth());
				date[2]=String.valueOf(dateFormatted.getYear()+1900);
			}
			
			//String date[] = requestInput.getTransactionDate().split("/").length>1?;

			mspcontext.setUserId(prop.getProperty("UserId"));
			mspcontext.setUserPassword(prop.getProperty("UserPassword"));

			requestParameter.setName(prop.getProperty("Name"));
			requestParameter.setValue(prop.getProperty("Value"));
			requestParameters.getRequestParameter().add(requestParameter);

			mspcontext.setRequestParameters(requestParameters);

			rec.setMSPContext(mspcontext);

			rec.setACTION(prop.getProperty("ACTION"));
			rec.setBANKCODE(prop.getProperty("BANKCODE"));
			rec.setRFCODE(prop.getProperty("RFCODE"));
			rec.setRFNUM(prop.getProperty("RFNUM"));

			trandatex.setCCYY(new BigInteger((String) date[2].subSequence(0, 4)));
			trandatex.setMM(new BigInteger(date[1]));
			trandatex.setDD(new BigInteger(date[0]));

			rec.setTRANDATEX(trandatex);

			sfl.setBANKDESC01("");
			sfl.setBANKDESC02("");
			sfl.setBANKDESC03("");
			sfl.setBANKKEY("");
			sfl.setCHEQNO("");
			if(!requestInput.getCreditAmount().contains("."))
			{
				sfl.setDOCORIGAMT(new BigDecimal(requestInput.getCreditAmount().replaceAll(",","")));
				s2610sfl.setACCTAMT(new BigDecimal(requestInput.getCreditAmount().replaceAll(",","")));
				s2610sfl.setORIGAMT(new BigDecimal(requestInput.getCreditAmount().replaceAll(",","")));

			}
			else
			{
				sfl.setDOCORIGAMT(new BigDecimal(requestInput.getCreditAmount().replaceAll(",","").substring(0, requestInput.getCreditAmount().indexOf(".")-1)));
				s2610sfl.setACCTAMT(new BigDecimal(requestInput.getCreditAmount().replaceAll(",","").substring(0, requestInput.getCreditAmount().indexOf(".")-1)));
				s2610sfl.setORIGAMT(new BigDecimal(requestInput.getCreditAmount().replaceAll(",","").substring(0, requestInput.getCreditAmount().indexOf(".")-1)));

			}
			sfl.setORIGCURR(prop.getProperty("ORIGCURR"));
			sfl.setPAYTYPE(prop.getProperty("PAYTYPE"));
			sfl.setSCRATE(new BigDecimal(prop.getProperty("SCRATE")));
			sfl.setZCHQTYP("");

			tchqdate.setCCYY(new BigInteger("9999"));
			tchqdate.setMM(new BigInteger("99"));
			tchqdate.setDD(new BigInteger("99"));

			sfl.setTCHQDATE(tchqdate);

			rec.setSFL(sfl);
			
			s2610sfl.setENTITY(policyNumber);
			s2610sfl.setSACSCODE(prop.getProperty("SACSCODE"));
			s2610sfl.setSACSTYPW(prop.getProperty("SACSTYPW"));

			rec.getS2610SFL().add(s2610sfl);

			additionalFields.setTRANCDE("");
			additionalFields.setTRANCDE("");
			additionalFields.setPRTIND("");
			additionalFields.setDISSEC(new BigInteger(prop.getProperty("DISSEC")));
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
				System.out.println();
				URL endpoint = new URL("http://10.163.177.100:9081/LIFEWebSrv/RCTService");
				SOAPMessage response = connection.call(request, endpoint);
				System.out.println(response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

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
