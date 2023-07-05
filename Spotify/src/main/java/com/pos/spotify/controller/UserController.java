package com.pos.spotify.controller;

import com.pos.spotify.component.AuthenticationRequest;
import com.pos.spotify.userdetails.Role;
import com.pos.spotify.userdetails.User;
import com.pos.spotify.security.JwtService;
import com.pos.spotify.service.AuthenticationService;
import com.pos.spotify.service.RoleService;
import com.pos.spotify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    @GetMapping("/{jwt}")
    @Secured("ROLE_ADMINISTRATOR_APP")
    public ResponseEntity<String> getUsers(@PathVariable String jwt){
        List<String> users = userService.getAll().stream()
                .map(User::getUname)
                .toList();
        return new ResponseEntity<>("We have the following users: " + users, HttpStatus.ACCEPTED);
    }
    @GetMapping("/{uid}/{jwt}")
    @Secured("ROLE_ADMINISTRATOR_APP")
    public ResponseEntity<String> getUser(@PathVariable String jwt, @PathVariable int uid){
        User userDB = userService.findByUid(uid);
        return new ResponseEntity<>(userDB.getUname(), HttpStatus.ACCEPTED);
    }
    @PutMapping("/bind/{jwt}")
    @Secured("ROLE_ADMINISTRATOR_APP")
    public ResponseEntity<String> bindRole(@RequestBody Map<String, String> json, @PathVariable String jwt){
            User userDB = userService.findByUid(Integer.parseInt(json.get("uid")));
            Role roleDB = roleService.findByRid(Integer.parseInt(json.get("rid")));
            boolean response = userDB.addRole(roleDB);
            if(response)
                userService.updateUser(userDB);
            return response? new ResponseEntity<>("Bound " + json.get("rid") +" to " + json.get("uid"), HttpStatus.ACCEPTED):
                    new ResponseEntity<>("Can't bound " + json.get("rid") +" to " + json.get("uid")+" (already has it)", HttpStatus.CONFLICT);
    }
    @PutMapping("/{jwt}")
    @Secured("ROLE_ADMINISTRATOR_APP")
    public ResponseEntity<String> newUser(@RequestBody AuthenticationRequest request, @PathVariable String jwt){
        try {
            authenticationService.register(request);
        }
        catch (Exception e){
            return new ResponseEntity<>("User already exists!", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("User " + request.getUname() + " saved!", HttpStatus.ACCEPTED);
    }
    @GetMapping("/roles/{uid}/{jwt}")
    public ResponseEntity<String>  getRolesByUid(@PathVariable int uid, @PathVariable String jwt){
        String uname = jwtService.extractUname(jwt);
        User user = userService.findByUname(uname);
        if(user.getUid() == uid || Objects.equals(user.getUname(), uname) &&
                user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMINISTRATOR_APP")))
        {
            User userDB = userService.findByUid(uid);
            return new ResponseEntity<>("Roles of user " + userDB.getUname() + ": " + userDB.getRoles().stream().map(Role::getRname).toList(), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Operation denied!", HttpStatus.FORBIDDEN);
    }
}
