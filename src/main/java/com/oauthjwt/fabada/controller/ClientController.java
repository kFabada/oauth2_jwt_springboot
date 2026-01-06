package com.oauthjwt.fabada.controller;


import com.oauthjwt.fabada.model.Client;
import com.oauthjwt.fabada.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;


@RestController
@EnableMethodSecurity
@RequestMapping("/cliente")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/register")
    public ResponseEntity<?> registerClient(@RequestBody Client client){
        Client newClient = clientService.registerClient(client);
        return ResponseEntity.ok(newClient);
    }
}
