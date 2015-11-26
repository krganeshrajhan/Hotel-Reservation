package com.hotel.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Project: Hotel-Reservation
 * Package Name: com.hotel.reservation.config
 * Created by: krganeshrajhan
 * Description:
 */
@Configuration
@ComponentScan({"com.hotel.reservation"})
@Import(ControllerConfig.class)
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public InternalResourceViewResolver simpleViewResolver() {
        InternalResourceViewResolver vr = new InternalResourceViewResolver();
        ((InternalResourceViewResolver) vr).setPrefix("/WEB-INF/pages/");
        ((InternalResourceViewResolver) vr).setSuffix(".jsp");
        return vr;

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

}
