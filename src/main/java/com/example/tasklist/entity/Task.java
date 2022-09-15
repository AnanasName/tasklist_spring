package com.example.tasklist.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Task {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    @NotNull
    private Long id;
    @Basic
    @Column(name = "title", length = 100)
    @NotNull
    private String title;
    @Basic
    @Column(name = "completed")
    private Integer completed;
    @Basic
    @Column(name = "date")
    private Date date;
    @ManyToOne
    @JoinColumn(name = "priority_id", referencedColumnName = "id")
    private Priority priority;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
}
