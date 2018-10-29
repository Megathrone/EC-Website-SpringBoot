package com.megathrone.ecspringboot.controller;

import com.megathrone.ecspringboot.bean.Category;
import com.megathrone.ecspringboot.service.CategoryService;
import com.megathrone.ecspringboot.util.PageForNavigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
  @Autowired CategoryService categoryService;

  @GetMapping("/categories")
  public PageForNavigator<Category> list(
      @RequestParam(value = "start", defaultValue = "0") int start,
      @RequestParam(value = "size", defaultValue = "5") int size)
      throws Exception {
    start = start < 0 ? 0 : start;
    PageForNavigator<Category> page = categoryService.list(start, size, 5);
    return page;
  }
}
