package com.springbootdemo.boot.rest;

import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "Access-Denied";
    }
}
