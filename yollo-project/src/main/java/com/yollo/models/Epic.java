package com.yollo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "epics")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Epic extends BaseEntity {
    private String title;

    @OneToMany(mappedBy = "epic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<UserStory> userStories;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_backlog_id")
    @ToString.Exclude
    private ProductBacklog productBacklog;

}
