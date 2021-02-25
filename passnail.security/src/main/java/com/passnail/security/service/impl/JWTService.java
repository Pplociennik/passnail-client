package com.passnail.security.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.passnail.data.model.entity.UserEntity;
import com.passnail.data.service.UserServiceIf;
import com.passnail.data.transfer.model.dto.LoginDto;
import com.passnail.security.SecurityConstants;
import com.passnail.security.service.AuthenticationServiceIf;
import com.passnail.security.service.JWTServiceIf;
import com.passnail.security.throwable.AuthenticationException;
import com.passnail.security.throwable.AuthorizationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.regex.Matcher;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.passnail.security.SecurityConstants.SESSION_EXPIRATION_TIME_MILIS;

/**
 * Created by: Pszemko at poniedziałek, 22.02.2021 23:28
 * Project: passnail-client
 */
@Service
public class JWTService implements JWTServiceIf {


    @Autowired
    private UserServiceIf userService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationServiceIf authenticationService;


    @Override
    public void isValid(@NonNull final String aToken, @NonNull final String aKey) {
        Claims claims = Jwts.parser()
                .setSigningKey(aKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(aToken)
                .getBody();

        Algorithm algorithm = HMAC512(aKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(aToken);

        String userName = claims.getSubject();
        Date expDate = claims.getExpiration();

        if (userService.findByLogin(userName) == null) {
            throw new AuthenticationException("You are not allowed!");
        }

        if (new Date().after(expDate)) {
            authenticationService.logout();
            throw new AuthorizationException("Session expired!");
        }
    }

    @Override
    public String createToken(@NonNull final LoginDto aDto) {
        Matcher matcher = SecurityConstants.VALID_EMAIL_ADDRESS_REGEX.matcher(aDto.getLoginOrEmail());
        UserEntity user = matcher.find() ?
                userService.findByEmail(aDto.getLoginOrEmail()) :
                userService.findByLogin(aDto.getLoginOrEmail());

        String token = JWT.create()
                .withSubject(user.getLogin())
                .withExpiresAt(new Date(System.currentTimeMillis() + SESSION_EXPIRATION_TIME_MILIS))
                .sign(HMAC512(aDto.getPassword().getBytes()));

        jwtService.isValid(token, aDto.getPassword());

        return token;
    }

    @Override
    public String createOnlineToken(@NonNull final LoginDto aDto) {
        Matcher matcher = SecurityConstants.VALID_EMAIL_ADDRESS_REGEX.matcher(aDto.getLoginOrEmail());
        UserEntity user = matcher.find() ?
                userService.findByEmail(aDto.getLoginOrEmail()) :
                userService.findByLogin(aDto.getLoginOrEmail());
        String onlineId = aDto.getOnlineID() != null ?
                aDto.getOnlineID() :
                user.getLogin();

        String token = JWT.create()
                .withSubject(onlineId)
                .withClaim("email", user.getEmailAddress())
                .withExpiresAt(new Date(System.currentTimeMillis() + SESSION_EXPIRATION_TIME_MILIS))
                .sign(HMAC512(aDto.getPassword().getBytes()));

        jwtService.isValid(token, aDto.getPassword());

        return token;
    }
}
