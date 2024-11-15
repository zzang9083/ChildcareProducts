package euljiro.project.childcareproducts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ChildcareproductsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChildcareproductsApplication.class, args);
    }



}
