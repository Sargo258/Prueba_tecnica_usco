package com.api.mall.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRolesController {

    @GetMapping("/accessAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String accessAdmin() {
        return "Hola, has accedido con rol de ADMIN";
    }

    @PreAuthorize("hasRole('VIGILANTE')")
    @GetMapping("/accessVigilante")
    public String accessUser() {
        return "Hola, has accedido con rol de VIGILANTE";
    }

}
