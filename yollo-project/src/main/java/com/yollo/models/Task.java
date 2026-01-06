package com.yollo.models;


import com.yollo.models.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@Getter @Setter
@ToString
public class Task extends BaseEntity {

    private String title;
    private String description;
    private String feedback ; // for the tester
    private TaskStatus status;


    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "user_story_id")
    private UserStory userStory;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="developer_id")
    @ToString.Exclude // to avoid circular references
    private User developer;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="tester_id")
    @ToString.Exclude
    private User tester;
}
