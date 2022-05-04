package com.project.systemeventsver2;

import com.project.systemeventsver2.repository.LogRepository;
import com.project.systemeventsver2.service.LogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableEurekaClient
public class SystemEventsVer2Application{
    @Bean
    LogServiceImpl logService(){
        return new LogServiceImpl();
    }

    public static void main(String[] args) {
        SpringApplication.run(SystemEventsVer2Application.class, args);
    }


}
