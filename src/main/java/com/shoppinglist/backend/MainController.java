package com.shoppinglist.backend;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/")
    public String index(Model model){
        return "index";
    }

    @GetMapping("/henlo.js")
    public String henlo(Model model){
        return "henlo.js";
    }

    @PostMapping("/json")
    public String json(Model model){
        return "example.json";
    }

    @Autowired
    private HttpServletRequest request;

    @PostMapping(value = "/echo", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, Object>> echoBack(@RequestBody(required = false) byte[] rawBody) throws IOException {

        Map<String, String> headers = new HashMap<String, String>();
        for (String headerName : Collections.list(request.getHeaderNames())) {
            headers.put(headerName, request.getHeader(headerName));
        }

        Map<String, Object> responseMap = new HashMap<String,Object>();
        responseMap.put("protocol", request.getProtocol());
        responseMap.put("method", request.getMethod());
        responseMap.put("headers", headers);
        responseMap.put("cookies", request.getCookies());
        responseMap.put("parameters", request.getParameterMap());
        responseMap.put("path", request.getServletPath());
        responseMap.put("body", rawBody != null ? new String(rawBody) : null);
        log.info("Echo Service received request");
        return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }
}
