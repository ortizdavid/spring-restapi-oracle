package com.ortizdavid.restapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "API Front", description = "First api Path")
@RestController
@RequestMapping("/api")
class FrontController {

    @Operation(summary = "Public EndPoint", description = "Can be accessed without JWT")
    @GetMapping("/public")
    public String publicResource() {
        return "<h1>Public Resource for REST API</h1>";
    }

    @Operation(summary = "Private EndPoint", description = "Can be accessed only with JWT")
    @GetMapping("/private")
    public String privateResource(@AuthenticationPrincipal OidcUser principal) {
        return String.format("""
            <h1>OAuth2</h1>
            <p>Email: %s</p> 
            <p>Authorities %s</p>
            <p>JWT: %s</p>
            """, 
            principal.getAttribute("email"),
            principal.getAuthorities(),
            principal.getIdToken().getTokenValue());
    }

}