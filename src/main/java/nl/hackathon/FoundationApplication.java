package nl.hackathon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "nl.hackathon" })
public class FoundationApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoundationApplication.class, args);
    }
}
