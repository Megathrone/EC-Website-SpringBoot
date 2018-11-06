package com.megathrone.ecspringboot.controller;

import com.megathrone.ecspringboot.bean.Product;
import com.megathrone.ecspringboot.service.CategoryService;
import com.megathrone.ecspringboot.service.ProductImageService;
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
  @Autowired ProductImageService productImageService;

  @GetMapping("/categories/{cid}/products")
  public PageForNavigator<Product> list(
      @PathVariable("cid") int cid,
      @RequestParam(value = "start", defaultValue = "0") int start,
      @RequestParam(value = "size", defaultValue = "5") int size)
      throws Exception {
    start = start < 0 ? 0 : start;
    PageForNavigator<Product> page = productService.list(cid, start, size, 5);

    productImageService.setFirstProdutImages(page.getContent());

    return page;
  }

  @GetMapping("/products/{id}")
  public Product get(@PathVariable("id") int id) throws Exception {
    Product bean = productService.get(id);
    return bean;
  }

  @PostMapping("/products")
  public Object add(@RequestBody Product bean) throws Exception {
    bean.setCreateDate(new Date());
    productService.add(bean);
    return bean;
  }

  @DeleteMapping("/products/{id}")
  public String delete(@PathVariable("id") int id, HttpServletRequest request) throws Exception {
    productService.delete(id);
    return null;
  }

  @PutMapping("/products")
  public Object update(@RequestBody Product bean) throws Exception {
    productService.update(bean);
    return bean;
  }
}