package com.example.todoapplication.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="user")
public class User {
    @Id
    @SequenceGenerator(name="user_sequence", sequenceName="user_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;
    private String userName;
    private String password;

    @OneToMany(mappedBy ="title",cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();
}
