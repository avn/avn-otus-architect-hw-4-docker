package ru.avn.otus.hw.users;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@ComponentScan(basePackageClasses = UserModuleConfiguration.class)
public class UserModuleConfiguration {
}
