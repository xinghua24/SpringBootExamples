package com.example.SpringSessionJdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class HomeController {
    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);
    @GetMapping("/")
    public String home() {
        return "Home";
    }

    @GetMapping("/setValue/{val}")
    public String setValue(HttpServletRequest request, @PathVariable String val) {
        HttpSession session = request.getSession();
        session.setAttribute("foo", val);
        return "OK";
    }

    @GetMapping("/getValue")
    public String getValue(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if( session.getAttribute("foo") != null){
            return session.getAttribute("foo").toString();
        }
        return "Can't find Value";
    }
}
