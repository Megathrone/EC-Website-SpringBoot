package com.megathrone.ecspringboot.controller;

import com.megathrone.ecspringboot.bean.Product;
import com.megathrone.ecspringboot.service.CategoryService;
import com.megathrone.ecspringboot.service.ProductService;
import com.megathrone.ecspringboot.util.PageForNavigator;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
  @Autowired ProductService productService;

  @Autowired CategoryService categoryService;

  @GetMapping("/categories/{cid}/products")
  public PageForNavigator<Product> list(
      @PathVariable("cid") int cid,
      @RequestParam(value = "start", defaultValue = "0") int start,
      @RequestParam(value = "size", defaultValue = "5") int size)
      throws Exception {
    start = start < 0 ? 0 : start;
    PageForNavigator<Product> page = productService.list(cid, start, size, 5);
    return page;
  }

  @GetMapping("/product/{id}")
  public Product get(@PathVariable("id") int id) throws Exception {
    Product product = productService.get(id);
    return product;
  }

  @PostMapping("/products")
  public Object add(@RequestBody Product product) throws Exception {
    product.setCreateDate(new Date());
    productService.add(product);
    return product;
  }

  @DeleteMapping("/products/{id}")
  public String delete(@PathVariable("id") int id, HttpServletRequest request) throws Exception {
    productService.delete(id);
    return null;
  }

  @PutMapping("/products")
  public Object update(@RequestBody Product product) throws Exception {
    productService.update(product);
    return product;
  }
}
