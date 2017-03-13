package au.gov.nsw.transport.endpoint;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.xml.transform.StringSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import java.io.IOException;
import java.io.StringWriter;
import java.security.Security;

/**
 * Created by PansariK on 10/03/2017.
 */
public class SoapRequestHeaderModifier implements WebServiceMessageCallback {

   @Override
    public void doWithMessage(WebServiceMessage message) throws IOException,TransformerException {
   }

    /*@Override
    public void doWithMessage(WebServiceMessage message) throws IOException,
            TransformerException {

        SoapHeader header = ((SoapMessage)message).getHeader()
        StringSource headerSource = new StringSource(prepareSecurityHeader());

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(headerSource, header.getResult());
    }

    private String prepareSecurityHeader() {
        String result = "";
        StringWriter sw = new StringWriter();
        try {
            JAXBContext carContext = JAXBContext.newInstance(Security.class);
            Marshaller carMarshaller = carContext.createMarshaller();
            carMarshaller.marshal(SECURITY_HEADER, sw);
            result = sw.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return result;
    }*/

}
