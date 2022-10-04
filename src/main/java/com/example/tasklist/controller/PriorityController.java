package com.example.tasklist.controller;

import com.example.tasklist.entity.Category;
import com.example.tasklist.entity.Priority;
import com.example.tasklist.repo.PriorityRepository;
import com.example.tasklist.search.CategorySearchValues;
import com.example.tasklist.search.PrioritySearchValues;
import com.example.tasklist.service.PriorityService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/priority")
@CrossOrigin(origins = "http://localhost:4200")
public class PriorityController {

    private PriorityService priorityService;

    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @GetMapping("/all")
    public List<Priority> getAllPriorities() {
        return priorityService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Priority> addPriority(@RequestBody Priority priority) {
        if (priority.getId() != null && priority.getId() != 0) {
            return new ResponseEntity("Redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("Missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityService.add(priority));
    }

    @PutMapping("/update")
    public ResponseEntity<Priority> updatePriority(@RequestBody Priority priority) {
        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("Missed Param: Title", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getId() == 0 || priority.getId() == null) {
            return new ResponseEntity("Expected param: id MUST NOT be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getColor() == null || priority.getColor().trim().length() == 0){
            return new ResponseEntity("Missed param: color", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityService.update(priority));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Priority> findPriorityById(@PathVariable Long id){

        Priority priority = null;

        try{
            priority = priorityService.findById(id);
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity("id="+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priority);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePriorityById(@PathVariable Long id){
        try{
            priorityService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return new ResponseEntity("id="+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok("Delete successful");
    }

    @PostMapping("/search")
    public ResponseEntity<List<Priority>> search(@RequestBody PrioritySearchValues prioritySearchValues){
        return ResponseEntity.ok(priorityService.findByTitle(prioritySearchValues.getTitle()));
    }
}
