package com.example.cookies;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class HomeController {
    // http://localhost:8080/setCookie
    @GetMapping("/setCookie")
    public String setCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("foo", "bar");
        cookie.setSecure(false);
        cookie.setHttpOnly(false);
        cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days

        response.addCookie(cookie);
        return "cookie is added!";
    }

    // http://localhost:8080/readCookie
    @GetMapping("/readCookie")
    public String readCookie(@CookieValue("foo") String fooCookieValue) {
        return "The value of foo cookie is " + fooCookieValue;
    }

    // http://localhost:8080/readAllCookie
    @GetMapping("/readAllCookie")
    public String readAllCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if( cookies != null) {
            return Stream.of(cookies).map(c-> c.getName() + "=" + c.getValue()).collect(Collectors.joining("<br>"));
        }
        return "No cookie is found!";
    }


    // http://localhost:8080/deleteCookie
    @GetMapping("/deleteCookie")
    public String deleteCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("foo", null);
        cookie.setMaxAge(0);
        cookie.setSecure(false);
        cookie.setHttpOnly(false);
        response.addCookie(cookie);

        return "cookie is deleted!";
    }

}
