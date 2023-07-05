package com.pos.spotify.controller;

import com.pos.spotify.security.JwtService;
import com.pos.spotify.service.RoleService;
import com.pos.spotify.service.UserService;
import com.pos.spotify.userdetails.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private final UserService userService;
    private final JwtService jwtService;

    @GetMapping("/{jwt}")
    @Secured("ROLE_ADMINISTRATOR_APP")
    public ResponseEntity<String> getRoles(@PathVariable String jwt){
        List<String> roles = roleService.getAll().stream()
                .map(Role::getRname)
                .toList();
        return new ResponseEntity<>("We have the following roles: " + roles, HttpStatus.ACCEPTED);
    }
    @GetMapping("/{rid}/{jwt}")
    @Secured("ROLE_ADMINISTRATOR_APP")
    public ResponseEntity<String> getRole(@PathVariable int rid, @PathVariable String jwt){
        Role roleDB = roleService.findByRid(rid);
        return new ResponseEntity<>(roleDB.getRname(), HttpStatus.ACCEPTED);
    }
    @PostMapping("/{jwt}")
    @Secured("ROLE_ADMINISTRATOR_APP")
    public ResponseEntity<String> newRole(@RequestBody Role role, @PathVariable String jwt){
        roleService.saveRole(role);
        return new ResponseEntity<>("Role " + role.getRname() + " added!", HttpStatus.ACCEPTED);
    }
}
