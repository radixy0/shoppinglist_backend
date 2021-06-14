package com.shoppinglist.backend;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/error")
    public String errorPage(Model model){
        return "error.html";
    }
    @GetMapping("/")
    public String index(Model model){
        return "index";
    }

    @Autowired
    ResourceLoader resourceLoader;
    @GetMapping("/{filename}.{fileextension}")
    public String htmlandJShandler(@PathVariable String filename, @PathVariable String fileextension, Model model){
        if(fileextension.equals("html") || fileextension.equals("js")){
            log.info("got html or js request: "+filename+"."+fileextension);
            Resource resource = resourceLoader.getResource("classpath:/templates/"+filename+"."+fileextension);
            if(resource.exists()) {
                return filename + "." + fileextension;
            }
            else return "error";
        }
        return "error";
    }

    @GetMapping("/json")
    public String jsonGET(){
        log.info("got json GET request");
        return "example.json";
    }

    @PostMapping(value="/json",consumes = "application/json", produces = "application/json")
    public String jsonPOST(@RequestBody(required=false) byte[] rawbody){
        String content = rawbody != null ? new String(rawbody) : null;
        log.info("got json POST request with content: " + content);
        return "example.json";
    }

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/echo")
    public ResponseEntity<Map<String, Object>> echoBackGET(@RequestBody(required = false) byte[] rawBody) throws IOException {

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
        log.info("Echo Service received GET request");
        return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }

    @PostMapping(value = "/echo", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, Object>> echoBackPOST(@RequestBody(required = false) byte[] rawBody) throws IOException {

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
        log.info("Echo Service received POST request");
        return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }

    @PostMapping(value="/v2/list",consumes = "application/json", produces = "application/json")
    public String v2list(@RequestBody(required=false) byte[] rawbody){
        String content = rawbody != null ? new String(rawbody) : null;
        log.info("got json POST request with content: " + content);
        return "example.json";
    }

    @PostMapping(value="/v1/list",consumes = "application/json", produces = "application/json")
    public String v1list(@RequestBody(required=false) byte[] rawbody){
        String content = rawbody != null ? new String(rawbody) : null;
        log.info("got json POST request with content: " + content);
        return "example2.json";
    }

    @PostMapping(value="/v1/**",consumes = "application/json", produces = "application/json")
    public String v1echo(@RequestBody(required=false) byte[] rawbody){
        String content = rawbody != null ? new String(rawbody) : null;
        log.info("got json POST request with content: " + content);
        return "got request to: "+request.getRequestURL() + "with content: "+content;
    }

    @PostMapping(value="/v2/*",consumes = "application/json", produces = "application/json")
    public String v2echo(@RequestBody(required=false) byte[] rawbody){
        String content = rawbody != null ? new String(rawbody) : null;
        log.info("got json POST request with content: " + content);
        return "got request to: "+request.getRequestURL() + "with content: "+content;
    }


}
