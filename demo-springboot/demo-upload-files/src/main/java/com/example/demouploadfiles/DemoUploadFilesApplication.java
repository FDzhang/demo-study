package com.example.demouploadfiles;

import com.example.demouploadfiles.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({StorageProperties.class})
public class DemoUploadFilesApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoUploadFilesApplication.class, args);
    }

}
