package com.example.tasklist.service;

import com.example.tasklist.entity.Priority;
import com.example.tasklist.entity.Stat;
import com.example.tasklist.repo.PriorityRepository;
import com.example.tasklist.repo.StatRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StatService {
    private final StatRepository repository;

    public StatService(StatRepository repository){
        this.repository = repository;
    }

    public List<Stat> findAll(){
        return repository.findAll();
    }

    public Stat add(Stat stat){
        return repository.save(stat);
    }

    public Stat update(Stat stat){
        return repository.save(stat);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Stat findById(Long id){
        return repository.findById(id).get();
    }
}
