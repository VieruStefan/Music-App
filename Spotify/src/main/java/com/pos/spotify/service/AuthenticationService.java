package com.pos.spotify.service;

import com.pos.spotify.component.AuthenticationRequest;
import com.pos.spotify.component.AuthenticationResponse;
import com.pos.spotify.userdetails.Role;
import com.pos.spotify.userdetails.User;
import com.pos.spotify.repository.UserRepository;
import com.pos.spotify.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final  RoleService roleService;
    public AuthenticationResponse register(AuthenticationRequest request) {
        var client_role = new ArrayList<Role>();
        client_role.add(roleService.findByRid(4));
        var user = User.builder()
                .uname(request.getUname())
                .upass(passwordEncoder.encode(request.getUpass()))
                .roles(client_role)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUname(),
                        request.getUpass()
                )
        );
        var user = userRepository.findByUname(request.getUname())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public void logout(String jwt){
        jwtService.invalidateToken(jwt);
    }

}
