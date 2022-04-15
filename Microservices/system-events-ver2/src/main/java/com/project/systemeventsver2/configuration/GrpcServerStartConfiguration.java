package com.project.systemeventsver2.configuration;


import com.project.systemeventsver2.service.LogServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class GrpcServerStartConfiguration {
    @Autowired
    private LogServiceImpl logService;

    @Bean
    public void startGrpcServer() throws IOException,InterruptedException
    {
        Server server = ServerBuilder.forPort(9091).addService(logService).build();
        server.start();
        server.awaitTermination();
    }
}
