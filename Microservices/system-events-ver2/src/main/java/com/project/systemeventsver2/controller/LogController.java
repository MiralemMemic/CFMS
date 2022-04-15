package com.project.systemeventsver2.controller;

import com.project.systemeventsver2.service.LogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    @Autowired
    private LogServiceImpl logService;


}
