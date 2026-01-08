package com.yollo.models;

import com.yollo.models.enums.UserStoryPriority;
import com.yollo.models.enums.UserStoryStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_stories")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserStory extends BaseEntity {
    private String title;
    private String description;
    private Integer storyPoints;

    @Enumerated(EnumType.STRING)
    private UserStoryPriority priority;

    @Enumerated(EnumType.STRING)
    private UserStoryStatus status;


    @OneToMany(mappedBy = "userStory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Task> tasks = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "epic_id")
    @ToString.Exclude
    private Epic epic;


    @ManyToMany(fetch = FetchType.LAZY,
                cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(name = "sprint_user_stories",
            joinColumns = @JoinColumn(name = "user_story_id"),
            inverseJoinColumns = @JoinColumn(name = "sprint_backlog_id")
    )
    @ToString.Exclude
    private Set<SprintBacklog> sprintBacklogs = new HashSet<>();


}
