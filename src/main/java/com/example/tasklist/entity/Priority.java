package com.example.tasklist.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
public class Priority {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    @NotNull
    private Long id;
    @Basic
    @Column(name = "title", length = 45)
    @NotNull
    private String title;
    @Basic
    @Column(name = "color", length = 45)
    @NotNull
    private String color;
}
