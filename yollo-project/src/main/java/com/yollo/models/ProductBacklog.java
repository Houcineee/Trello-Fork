package com.yollo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product_backlogs")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductBacklog extends BaseEntity {
    private String ProjectName;

    @OneToMany(mappedBy = "productBacklog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Epic> epic ;


    @OneToMany(mappedBy = "productBacklog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<SprintBacklog> sprintBacklogs ;


    @ManyToMany(fetch = FetchType.LAZY ,
                cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name = "project_members",
            joinColumns = @JoinColumn(name = "product_backlog_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @ToString.Exclude
    private Set<User> members;
}
