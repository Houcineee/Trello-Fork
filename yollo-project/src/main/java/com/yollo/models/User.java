package com.yollo.models;
import com.yollo.models.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User extends BaseEntity {
    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role; // e.g., "DEVELOPER", "TESTER", "PM", "SM"


    @OneToMany(mappedBy = "developer", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Task> devTask;

    @OneToMany(mappedBy = "tester", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Task> testTask;

    @ManyToMany(mappedBy = "members", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<ProductBacklog> productBacklogs;
}
