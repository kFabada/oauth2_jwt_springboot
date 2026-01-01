package com.oauthjwt.fabada.service;

import com.oauthjwt.fabada.model.Client;
import com.oauthjwt.fabada.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Client registerClient(Client client){
      client.setClientId(UUID.randomUUID().toString());
      return clientRepository.save(client);
    }
}
