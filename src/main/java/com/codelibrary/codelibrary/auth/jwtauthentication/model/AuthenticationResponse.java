package com.codelibrary.codelibrary.auth.jwtauthentication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationResponse {
    private final String jwt;
    private final Long appUserId;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String appUserRole;
}
