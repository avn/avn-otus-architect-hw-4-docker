package ru.avn.otus.hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.projection.CollectionAwareProjectionFactory;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.web.bind.annotation.RestController;
import ru.avn.otus.hw.users.profiles.UserProfileModuleConfiguration;

@RestController
@SpringBootApplication
@Import(UserProfileModuleConfiguration.class)
public class UserProfileApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserProfileApplication.class, args);
    }

    @Bean
    ProjectionFactory projectionFactory() {
        return new CollectionAwareProjectionFactory();
    }

}
