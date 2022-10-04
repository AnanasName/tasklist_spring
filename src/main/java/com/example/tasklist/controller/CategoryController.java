package com.example.tasklist.controller;

import com.example.tasklist.entity.Category;
import com.example.tasklist.entity.Priority;
import com.example.tasklist.repo.CategoryRepository;
import com.example.tasklist.search.CategorySearchValues;
import com.example.tasklist.service.CategoryService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public List<Category> getAllCategories(){
        return categoryService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        if (category.getTitle() == null || category.getTitle().trim().length() == 0){
            return new ResponseEntity("Missed Param: Title", HttpStatus.NOT_ACCEPTABLE);
        }

        if (category.getId() != 0 || category.getId() != null){
            return new ResponseEntity("Redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryService.add(category));
    }

    @PutMapping("/update")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){
        if (category.getTitle() == null || category.getTitle().trim().length() == 0){
            return new ResponseEntity("Missed Param: Title", HttpStatus.NOT_ACCEPTABLE);
        }

        if (category.getId() == 0 || category.getId() == null){
            return new ResponseEntity("Expected param: id MUST NOT be null", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryService.update(category));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable Long id){

        Category category = null;

        try{
            category = categoryService.findById(id);
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity("id="+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategoryById(@PathVariable Long id){
        try{
            categoryService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return new ResponseEntity("id="+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok("Delete successful");
    }

    @PostMapping("/search")
    public ResponseEntity<List<Category>> search(@RequestBody CategorySearchValues categorySearchValues){
        return ResponseEntity.ok(categoryService.findByTitle(categorySearchValues.getTitle()));
    }
}
