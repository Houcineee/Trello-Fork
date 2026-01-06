package com.yollo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "sprint_backlogs")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SprintBacklog extends BaseEntity {
    private String SprintName;

    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private Boolean isActive ;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_backlog_id")
    @ToString.Exclude
    private ProductBacklog productBacklog;


    @ManyToMany(mappedBy = "sprintBacklogs", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<UserStory> userStoriesSprint;

}
