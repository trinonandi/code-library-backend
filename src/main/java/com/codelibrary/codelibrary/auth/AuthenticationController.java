package com.codelibrary.codelibrary.auth;


import com.codelibrary.codelibrary.auth.appuser.AppUser;
import com.codelibrary.codelibrary.auth.appuser.AppUserService;
import com.codelibrary.codelibrary.auth.jwtauthentication.JwtUtil;
import com.codelibrary.codelibrary.auth.jwtauthentication.model.AuthenticationRequest;
import com.codelibrary.codelibrary.auth.jwtauthentication.model.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/authenticate")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final AppUserService userDetailsService;
    private final JwtUtil jwtUtil;
    @Autowired
    public AuthenticationController(AuthenticationManager manager, AppUserService userDetailsService, JwtUtil util) {
        this.authenticationManager = manager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = util;
    }

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Incorrect Username or Password");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        final AppUser user = (AppUser)userDetails;
        AuthenticationResponse response = new AuthenticationResponse(
                jwt,
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                String.valueOf(user.getAppUserRole())
        );
        return ResponseEntity.ok().body(response);
    }
}
