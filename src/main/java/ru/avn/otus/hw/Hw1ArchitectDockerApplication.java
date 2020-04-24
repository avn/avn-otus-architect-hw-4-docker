package ru.avn.otus.hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@SpringBootApplication
public class Hw1ArchitectDockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Hw1ArchitectDockerApplication.class, args);
    }

    @RequestMapping("/health")
    Object health() {
        return Collections.singletonMap("status", "OK");
    }

}
