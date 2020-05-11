package ru.avn.otus.hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.projection.CollectionAwareProjectionFactory;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.avn.otus.hw.users.UserModuleConfiguration;

import java.util.Collections;

@RestController
@SpringBootApplication
@Import(UserModuleConfiguration.class)
public class Hw1ArchitectDockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Hw1ArchitectDockerApplication.class, args);
    }

    @Bean
    ProjectionFactory projectionFactory() {
        return new CollectionAwareProjectionFactory();
    }

    @RequestMapping("/health")
    Object health() {
        return Collections.singletonMap("STATUS", "OK");
    }
}
