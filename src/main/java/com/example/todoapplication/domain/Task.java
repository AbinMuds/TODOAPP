package com.example.todoapplication.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@ToString
@Entity
@Table(name="task")

public class Task {
    @Id
    @SequenceGenerator(sequenceName = "task_sequence", name="task_sequence", allocationSize = 1,initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_sequence")
    private Long id;
    private String title;
    private String description;
    private Date deuDate;
    private boolean done;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
