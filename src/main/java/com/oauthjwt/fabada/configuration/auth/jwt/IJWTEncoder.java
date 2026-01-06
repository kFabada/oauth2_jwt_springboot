package com.oauthjwt.fabada.configuration.auth.jwt;

import com.nimbusds.jose.JOSEException;
import com.oauthjwt.fabada.model.User;

public interface IJWTEncoder {
    String generateTokenJWT(User user) throws JOSEException;
}
