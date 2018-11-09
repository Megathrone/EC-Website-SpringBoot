package com.megathrone.ecspringboot.controller;

import com.megathrone.ecspringboot.bean.Category;
import com.megathrone.ecspringboot.service.CategoryService;
import com.megathrone.ecspringboot.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForeRESTController {
  @Autowired CategoryService categoryService;
  @Autowired ProductService productService;

  @GetMapping("/forehome")
  public Object home() {
    List<Category> cs = categoryService.list();
    productService.fill(cs);
    productService.fillByRow(cs);
    categoryService.removeCategoryFromProduct(cs);
    return cs;
  }
}
