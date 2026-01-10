package com.yollo.models;
import com.yollo.models.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User extends BaseEntity implements UserDetails {

    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)

    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role; // e.g., "DEVELOPER", "TESTER", "PM", "SM"


    @OneToMany(mappedBy = "developer", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Task> devTask = new ArrayList<>();

    @OneToMany(mappedBy = "tester", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Task> testTask = new ArrayList<>();

    @ManyToMany(mappedBy = "members", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<ProductBacklog> productBacklogs = new HashSet<>();

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_" + role.name())
        ) ;
    }

}
