package com.example.message.interceptors;

import com.example.message.controller.MessageController;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

@Service
public class MessageInterceptor implements HandlerInterceptor {

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;


    private static Logger log = LoggerFactory.getLogger(MessageController.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuffer requestURL = request.getRequestURL();
        log.info("preHandle => Request URL: {}", requestURL);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        int status = response.getStatus();

        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("system-event-service",false);
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",9090).usePlaintext().build();
        com.project.systemeventsver2.LogServiceGrpc.LogServiceBlockingStub stub = com.project.systemeventsver2.LogServiceGrpc.newBlockingStub(channel);
        Calendar c = Calendar.getInstance();
        String time = c.getTime().toString();
        int res = response.getStatus();
        String resp = Integer.toString(res);
        com.project.systemeventsver2.LogRequest request1 = com.project.systemeventsver2.LogRequest.newBuilder()
                .setTime(time).setName("Prisoner").setType(request.getMethod()).setResource("prisoner").setResponse(resp)
                .build();
        com.project.systemeventsver2.LogResponse response1 = stub.log(request1);

    }
}
