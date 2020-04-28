package ru.avn.otus.hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.projection.CollectionAwareProjectionFactory;
import org.springframework.data.projection.ProjectionFactory;
import ru.avn.otus.hw.users.UserModuleConfiguration;

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

}
