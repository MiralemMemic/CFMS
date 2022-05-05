package com.project.prisoner.interceptor;

import com.netflix.discovery.EurekaClient;
import com.project.prisoner.LogRequest;
import com.project.prisoner.LogServiceGrpc;
import com.project.prisoner.controller.PrisonerController;
import lombok.extern.java.Log;
import net.devh.boot.grpc.client.inject.GrpcClient;
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

import static java.lang.System.out;

@Service
public class PrisonerInterceptor implements HandlerInterceptor {

    @GrpcClient("LogService")
    private LogServiceGrpc.LogServiceStub logServiceStub;



    private static Logger log = LoggerFactory.getLogger(PrisonerController.class);

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        out.println(log);
        log.info("UserInterceptor - afterCompletion");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
