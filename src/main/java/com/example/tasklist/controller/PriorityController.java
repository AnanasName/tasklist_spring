package com.example.tasklist.controller;

import com.example.tasklist.entity.Priority;
import com.example.tasklist.repo.PriorityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    private PriorityRepository priorityRepository;

    public PriorityController(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @GetMapping("/test")
    public List<Priority> test() {

        List<Priority> list = priorityRepository.findAll();

        return list;
    }

    @PostMapping("/add")
    public ResponseEntity<Priority> addPriority(@RequestBody Priority priority) {
        if (priority.getId() != null && priority.getId() != 0) {
            return new ResponseEntity("Redunant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("Missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityRepository.save(priority));
    }
}
