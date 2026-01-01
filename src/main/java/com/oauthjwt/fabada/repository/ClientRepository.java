package com.oauthjwt.fabada.repository;

import com.oauthjwt.fabada.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {

    Client findByClientId(String clientId);
}
