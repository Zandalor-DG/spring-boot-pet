package org.springboot.pet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "org.springboot.pet")
public class SpringBootPetApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPetApplication.class, args);
    }

}
