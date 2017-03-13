package au.gov.nsw.transport.configuration;


import javax.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import au.gov.nsw.transport.tmc.services.entity.devices.device_v0.DevicePortTypeV0P1;
import au.gov.nsw.transport.endpoint.PublishDMSEndpoint;

@Configuration
public class WebServiceConfiguration {

    public static final String BASE_URL = "/soap-api";
    public static final String SERVICE_URL = "/publishDMSStatus";


    @Bean
    public ServletRegistrationBean cxfServlet() {
        return new ServletRegistrationBean(new CXFServlet(), BASE_URL + "/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }



    @Bean
    public DevicePortTypeV0P1 DevicePortType_v0p1() {
        return new PublishDMSEndpoint();
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), DevicePortType_v0p1());
        endpoint.publish(SERVICE_URL);
        endpoint.setWsdlLocation("Device_v0.wsdl");
        return endpoint;
    }
}
