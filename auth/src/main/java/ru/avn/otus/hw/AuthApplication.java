package ru.avn.otus.hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.projection.CollectionAwareProjectionFactory;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@SpringBootApplication
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

    @Bean
    ProjectionFactory projectionFactory() {
        return new CollectionAwareProjectionFactory();
    }

    @GetMapping("/health")
    public Object health() {
        return Collections.singletonMap("STATUS", "OK");
    }
}
