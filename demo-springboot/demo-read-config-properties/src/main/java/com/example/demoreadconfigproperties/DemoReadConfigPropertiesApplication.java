package com.example.demoreadconfigproperties;

import com.example.demoreadconfigproperties.config.LibraryProperties;
import com.example.demoreadconfigproperties.config.ProfileProperties;
import com.example.demoreadconfigproperties.config.WebSite;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ProfileProperties.class)
public class DemoReadConfigPropertiesApplication implements InitializingBean {

    private final ProfileProperties profileProperties;
    private final LibraryProperties libraryProperties;
    private final WebSite webSite;
    @Value("${wuhan2020}")
    String wuhan2020;

    public DemoReadConfigPropertiesApplication(LibraryProperties library, ProfileProperties profileProperties, WebSite webSite) {
        this.libraryProperties = library;
        this.profileProperties = profileProperties;
        this.webSite = webSite;
    }


    public static void main(String[] args) {
        SpringApplication.run(DemoReadConfigPropertiesApplication.class, args);
    }


    @Override
    public void afterPropertiesSet() {
        System.out.println(wuhan2020);
        System.out.println(webSite.getUrl());
        System.out.println(libraryProperties.getLocation());
        System.out.println(libraryProperties.getBooks());
        System.out.println(profileProperties.toString());
    }

}
