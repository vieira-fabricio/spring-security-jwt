package com.spring.security.jwt.service;

import com.spring.security.jwt.dto.NewClient;
import com.spring.security.jwt.model.Client;
import com.spring.security.jwt.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public void save(NewClient newClient) {
        Client client = new Client();
        client.setName(newClient.getName());

        clientRepository.save(client);
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Optional<Client> findById(Integer id){
            return clientRepository.findById(id);
    }
}


