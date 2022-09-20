package com.example.tasklist.controller;

import com.example.tasklist.entity.Stat;
import com.example.tasklist.repo.StatRepository;
import com.example.tasklist.service.StatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/stat")
@CrossOrigin(origins = "http://localhost:4200")
public class StatController {

    private StatService statService;

    public StatController(StatService statService) {
        this.statService = statService;
    }

    private final Long defaultId = 1l;

    @GetMapping("/get")
    private ResponseEntity<Stat> getStat(){
        Stat stat = null;
        try{
            stat = statService.findById(defaultId);
        } catch (NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity("stat not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(stat);
    }
}
