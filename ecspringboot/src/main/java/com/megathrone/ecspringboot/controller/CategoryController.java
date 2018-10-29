package com.megathrone.ecspringboot.controller;

import com.megathrone.ecspringboot.bean.Category;
import com.megathrone.ecspringboot.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
  @Autowired CategoryService categoryService;

  @GetMapping("/categories")
  public List<Category> list() {
    return categoryService.list();
  }
}
