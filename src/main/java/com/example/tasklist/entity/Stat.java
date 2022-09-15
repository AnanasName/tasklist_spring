package com.example.tasklist.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Stat {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    @NotNull
    private Long id;
    @Basic
    @Column(name = "completed_total")
    private Long completedTotal;
    @Basic
    @Column(name = "uncompleted_total")
    private Long uncompletedTotal;
}
