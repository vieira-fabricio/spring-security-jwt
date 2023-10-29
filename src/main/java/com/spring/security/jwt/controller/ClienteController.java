package com.spring.security.jwt.controller;


import com.spring.security.jwt.dto.NewClient;
import com.spring.security.jwt.model.Client;
import com.spring.security.jwt.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClienteController {

    @Autowired
    private ClientService clientService;


    @GetMapping("/{id}")
    public Optional<Client> findById(@PathVariable Integer id){
        return clientService.findById(id);
    }

    @GetMapping
    public List<Client> buscarTodos(){
        return clientService.findAll();
    }

    @PostMapping("/")
    public void create(@RequestBody NewClient newClient){
        clientService.save(newClient);
    }
}
