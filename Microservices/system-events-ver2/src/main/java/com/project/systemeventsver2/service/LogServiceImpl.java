package com.project.systemeventsver2.service;

import com.project.systemeventsver2.*;
import com.project.systemeventsver2.model.Log;
import com.project.systemeventsver2.repository.LogRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
@GrpcService
public class LogServiceImpl extends LogServiceGrpc.LogServiceImplBase{

    @Autowired
    private LogRepository logRepository;

    @Override
    public void log(LogRequest request, StreamObserver<LogResponse> responseObserver){
        Log log = new Log();
        log.setTime(request.getTime());
        log.setName(request.getName());
        log.setType(request.getType());
        log.setResource(request.getResource());
        log.setResponse(request.getResponse());
        System.out.println("Saved log: " + request.getName() +", " + request.getTime()+", " + request.getResource()+", " + request.getType()+", " + request.getResponse());
        logRepository.save(log);

        LogResponse response = LogResponse.newBuilder()
                .setResponse("Loggirano")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
