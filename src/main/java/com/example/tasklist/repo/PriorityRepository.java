package com.example.tasklist.repo;

import com.example.tasklist.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {

}
