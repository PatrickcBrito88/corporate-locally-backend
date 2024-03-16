package brito.org.com.corporatelocallybackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Properties;

@SpringBootApplication
@EnableFeignClients
public class CorporateLocallyBackendApplication {

	public static void main(String[] args) {
		Properties properties = new Properties();
		SpringApplication.run(CorporateLocallyBackendApplication.class, args);
	}

}
