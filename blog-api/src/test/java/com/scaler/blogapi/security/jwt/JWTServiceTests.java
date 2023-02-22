package com.scaler.blogapi.security.jwt;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JWTServiceTests {

    private JWTService jwtService = new JWTService();

    @Test
    void canCreateJWTFromUserId () {
        var userId = 1122;
        var jwt = jwtService.createJWT(userId, new Date(1677082), new Date(1677687));
        assertEquals("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTIyIiwiZXhwIjoxNjc3LCJpYXQiOjE2Nzd9.rIGL_NK9fp5r5CtNPq9AKSIy5E5xFntTlYq-a6ZqTPo", jwt);
    }

    @Test
    void canVerifyJWT() {
        var jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTIyIiwiZXhwIjoxNjc3Njg3NTkyLCJpYXQiOjE2NzcwODI3OTJ9.aDoiFHfb13x6kCQPzE7jZp6llgoChzktpbeRir9szps";
        var userId = jwtService.getUserIdFromJWT(jwt);
        assertEquals(1122, userId);
    }
}
