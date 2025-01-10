package com.usermanagment.backend.model;

import com.usermanagment.backend.permission.UserPermission;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Accessors(chain = true)
@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, name = "permissions_bit_mask")
    private int permissionsBitMask;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return UserPermission.toList(permissionsBitMask).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public User(String name, String lastname, String email, String password, int permissionsBitMask) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.permissionsBitMask = permissionsBitMask;
    }
}
