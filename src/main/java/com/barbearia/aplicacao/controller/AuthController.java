package com.barbearia.aplicacao.controller;

import com.barbearia.aplicacao.dto.request.CreateBarbeiroDTO;
import com.barbearia.aplicacao.dto.request.CreateClienteDTO;
import com.barbearia.aplicacao.dto.request.LoginDTO;
import com.barbearia.aplicacao.dto.response.TokenResponse;
import com.barbearia.aplicacao.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginDTO dto) {
        return ResponseEntity.ok(authService.loginCliente(dto));
    }

    @PostMapping("/register/cliente")
    public ResponseEntity<TokenResponse> registerCliente(@RequestBody CreateClienteDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerCliente(dto));
    }

    @PostMapping("/register/barbeiro")
    public ResponseEntity<TokenResponse> registerBarbeiro(@RequestBody CreateBarbeiroDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerBarbeiro(dto));
    }
}
