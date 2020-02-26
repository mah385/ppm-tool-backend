package com.ppmtool.controller;

import com.ppmtool.domain.Project;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/misc")
public class HelloWorldController {

    @GetMapping(value = "/hello-world", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> helloWorld() {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("timestamp", LocalDateTime.now());
        responseMap.put("appName", "ProjectPortfolioManagement");
        responseMap.put("message", "Hello World");
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
    }

    @GetMapping(value = "/project", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Project project() {
        return new Project();
    }

}
