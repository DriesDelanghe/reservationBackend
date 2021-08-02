package com.example.reservationrestapi;

import com.example.reservationrestapi.mailingservice.SpringMailConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


@SpringBootApplication
public class ReservationRestapiApplication extends AbstractAnnotationConfigDispatcherServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ReservationRestapiApplication.class, args);
    }

    public static final String CHARACTER_ENCODING = "UTF-8";

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { };
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { SpringMailConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        final CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding(CHARACTER_ENCODING);
        encodingFilter.setForceEncoding(true);
        return new Filter[] { encodingFilter };
    }

}
