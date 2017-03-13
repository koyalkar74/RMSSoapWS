package au.gov.nsw.transport;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;


@SpringBootApplication
@EnableJms
@ComponentScan("au.gov.nsw.transport")
public class OdxPocUc2Application {

	public static void main(String[] args) {

		SpringApplication.run(OdxPocUc2Application.class, args);
	}

}
