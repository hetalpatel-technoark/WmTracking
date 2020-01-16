/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 * @author Mukesh
 */
@Component
public class MaJWT {

    private static final String JWT_KEY = "X3qGRkZY-BB786BB-XYZ6653-HMAC256";
    private static final String TOKEN = "apptoken";
    private static final String APPTOKEN = "a7voxyzjGBzm-Trucking-2019-UoWZAkBB1sZ";
    private static final String DEVICETOKEN = "devicetoken";
    private static final String AUTHORIZATION = "Authorization";

    Algorithm algorithm = Algorithm.HMAC256(JWT_KEY);

    public String generate(Long id) {
        return JWT.create()
                .withJWTId(String.valueOf(id))
                .withIssuer("786")
                .sign(algorithm);
    }

    public String generateWithExpires(Long id, int expTimeInSec) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.SECOND, expTimeInSec);
        return JWT.create()
                .withExpiresAt(c.getTime())
                .withJWTId(String.valueOf(id))
                .withIssuer("786")
                .withClaim("username", "")
                .withClaim("host", "")
                .sign(algorithm);
    }

    public Boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT
                    .require(algorithm)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getId(String jwt, HttpServletRequest request) {
        try {
            JWTVerifier verifier = JWT
                    .require(algorithm)
                    .build();
            return verifier.verify(jwt).getId();

        } catch (Exception e) {
            return null;
        }
    }

    public Boolean checkHeadersWithAuth(HttpServletRequest request) {
        if (!isValidHeader(request.getHeader(TOKEN))) {
            return false;
        }
        if (request.getHeader(DEVICETOKEN) == null || request.getHeader(DEVICETOKEN).equals("")) {
            return false;
        }
        if (request.getHeader(AUTHORIZATION) == null || request.getHeader(AUTHORIZATION).equals("")) {
            return false;
        }
        return true;
    }

    public boolean isValidHeader(String value) {
        if (value == null || value.equals("")) {
            return false;
        } else {
            return value.equals(APPTOKEN);
        }
    }

    public String getJwt(HttpServletRequest request) {
        try {
            return request.getHeader(AUTHORIZATION);
        } catch (Exception e) {
            return "";
        }
    }
}
