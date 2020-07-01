package com.loginandregistration.configuration;

import com.loginandregistration.dao.UserDAO;
import com.loginandregistration.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan({"com.loginandregistration.controller","com.loginandregistration.service"})
public class MvcConfiguration implements WebMvcConfigurer {

    @Bean
    public UserDAO getUserDAO() {
        return new UserDAO();
    }

    @Bean
    public UserService getUserService() {
        return new UserService();
    }

    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
    }

}
