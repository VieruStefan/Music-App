package com.pos.spotify.userdetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String uname;
    private String upass;

    @ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = { @JoinColumn(name = "user_uid") },
            inverseJoinColumns = { @JoinColumn(name = "role_rid") }
    )
    private List<Role> roles;

    public User(String uname, String upass) {
        this.uname = uname;
        this.upass = upass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return uid == user.getUid() && Objects.equals(uname, user.getUname()) && Objects.equals(upass, user.getUpass());
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, uname, upass);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return !roles.isEmpty()?
                roles.stream()
                        .map(f -> new SimpleGrantedAuthority(String.format("ROLE_%s", f.getRname())))
                        .collect(Collectors.toList())
                :new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return this.upass;
    }

    @Override
    public String getUsername() {
        return this.uname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean addRole(Role role){
        if(!roles.contains(role)){
            roles.add(role);
            return true;
        }
        return false;
    }

    public String toString(){
        return this.uname;
    }
}
