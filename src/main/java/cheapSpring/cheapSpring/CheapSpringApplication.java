package cheapSpring.cheapSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.io.IOException;
import java.net.URISyntaxException;

import static cheapSpring.cheapSpring.AmazonRequests.makeAmazonCall;
import static cheapSpring.cheapSpring.EmagRequests.makeEmagCall;

@SpringBootApplication(scanBasePackages = "cheapSpring.cheapSpring")
public class CheapSpringApplication  extends SpringBootServletInitializer {
	public static void main(String[] args) throws IOException, URISyntaxException {
		makeEmagCall();
		makeAmazonCall();
		SpringApplication.run(CheapSpringApplication.class, args);
	}

}
