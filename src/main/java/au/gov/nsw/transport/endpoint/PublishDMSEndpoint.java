package au.gov.nsw.transport.endpoint;

import au.gov.nsw.transport.tmc.services.entity.devices.device_v0.DevicePortTypeV0P1;
import au.gov.nsw.transport.tmc.schemas.software_component.soapheader.v1.MsgContext;
import au.gov.nsw.transport.tmc.services.entity.devices.device_v0p1.PublishDMSStatus;
import au.gov.nsw.transport.tmc.services.entity.devices.device_v0p1.PublishDMSStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.ntcip.c2c_message_administration.C2CMessageReceipt;
import javax.xml.ws.Holder;

public class PublishDMSEndpoint implements DevicePortTypeV0P1  {

    @Autowired
    JmsClient jsmClient;

    @PayloadRoot(namespace = "http://transport.nsw.gov.au/tmc/services/entity/devices/Device_v0p1", localPart = "publishDMSStatus")
    @ResponsePayload
    public PublishDMSStatusResponse test() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("########################");
        System.out.println("Setting Response Message");
        PublishDMSStatusResponse response = new PublishDMSStatusResponse();
        C2CMessageReceipt test = new C2CMessageReceipt();
        test.setInformationalText("hello");
        response.setC2CMessageReceipt(test);
        System.out.println("Response message set to hello");
        System.out.println("########################");
        //MsgContext hdrMsg = new MsgContext();
        //hdrMsg.setMsgCorrelationID("aaaa-bbbb");
        //WebServiceMessage message;
        //SoapHeader header = ((SoapMessage)message).getSoapHeader();
        return response;
    }



    @Override
    public PublishDMSStatusResponse publishDMSStatus (PublishDMSStatus message,Holder<MsgContext> header)
        throws au.gov.nsw.transport.tmc.services.entity.devices.device_v0.ErrorReport {
        PublishDMSStatusResponse response = new PublishDMSStatusResponse();
        MsgContext hdrMsg = new MsgContext();
        //hdrMsg.setMsgCorrelationID("aaaa-bbbb");
        //System.out.println("....trying to print incoming payload......");
        System.out.println("########################");
        System.out.println("  Incoming SOAP Message ");
        System.out.println("  Message Content  .....");
        System.out.println("==>" + message.getDMSStatusMsg().toString());
        System.out.println("Correlation ID ==> " + header.value.getMsgCorrelationID());
        System.out.println("########################");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("########################");
        System.out.println("  Sending MQ Message    ");
        System.out.println("==>" + header.value.getMsgCorrelationID());
        System.out.println("########################");
        //String correlationid = header.value.getMsgCorrelationID();

        //Sending MQ message

        jsmClient.send(header.value.getMsgCorrelationID());

        //Receiving MQ message
       // String message_received = jsmClient.receive();
        //System.out.println(".....Received Message =" + message_received);

        //return null;

        PublishDMSStatusResponse finalResponse = new PublishDMSStatusResponse();
        //System.out.println("calling test() method========");
        finalResponse = test();


        return finalResponse;
    }
}