package com.project.systemeventsver2;

import com.project.systemeventsver2.configuration.GrpcServerStartConfiguration;
import com.project.systemeventsver2.repository.LogRepository;
import com.project.systemeventsver2.service.LogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SystemEventsVer2Application implements CommandLineRunner{
    @Bean
    LogServiceImpl logService(){
        return new LogServiceImpl();
    }

    @Autowired
    private LogRepository logRepository;


    public static void main(String[] args) {
        SpringApplication.run(SystemEventsVer2Application.class, args);
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(GrpcServerStartConfiguration.class);
        ctx.getBean("startGrpcServer");

    }

    @Override
    public void run(String... args) throws Exception {
    }

}
