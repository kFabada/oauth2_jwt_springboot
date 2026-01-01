package com.oauthjwt.fabada.configuration.auth.CustomOauth2;

import com.oauthjwt.fabada.configuration.auth.CustomListGrantedAuthority;
import com.oauthjwt.fabada.model.Client;
import com.oauthjwt.fabada.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@EnableWebSecurity
public class CustomOauth2Register implements RegisteredClientRepository {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void save(RegisteredClient registeredClient) {

    }

    @Override
    public RegisteredClient findById(String id) {



        return null;
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {

        Client client = clientRepository.findByClientId(clientId);

        if(client == null){
            return null;
        }

        return RegisteredClient
                .withId(client.getId())
                .clientSecret("{noop}"+client.getClientSecret())
                .redirectUri(client.getRedirectUri())
                .clientId(client.getClientId())
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .scopes(scope -> scope.add(client.getScopes()))

                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .build();
    }
}
