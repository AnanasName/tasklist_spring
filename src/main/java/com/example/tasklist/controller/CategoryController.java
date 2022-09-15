package com.example.tasklist.controller;

import com.example.tasklist.entity.Category;
import com.example.tasklist.entity.Priority;
import com.example.tasklist.repo.CategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/test")
    public List<Category> test(){
        List<Category> list = categoryRepository.findAll();

        return list;
    }

    @PostMapping("/add")
    public Category add(@RequestBody Category category){
        return categoryRepository.save(category);
    }
}
