package com.megathrone.ecspringboot.controller;

import com.megathrone.ecspringboot.bean.Category;
import com.megathrone.ecspringboot.bean.User;
import com.megathrone.ecspringboot.service.CategoryService;
import com.megathrone.ecspringboot.service.ProductService;
import com.megathrone.ecspringboot.service.UserService;
import com.megathrone.ecspringboot.util.Result;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
public class ForeRESTController {
  @Autowired CategoryService categoryService;
  @Autowired ProductService productService;
  @Autowired UserService userService;

  @GetMapping("/forehome")
  public Object home() {
    List<Category> cs = categoryService.list();
    productService.fill(cs);
    productService.fillByRow(cs);
    categoryService.removeCategoryFromProduct(cs);
    return cs;
  }

  @PostMapping("/foreregister")
  public Object register(@RequestBody User user) {
    String name = user.getName();
    String password = user.getPassword();
    name = HtmlUtils.htmlEscape(name);
    user.setName(name);
    boolean exist = userService.isExist(name);

    if (exist) {
      String message = "User exists";
      return Result.fail(message);
    }
    user.setPassword(password);
    userService.add(user);
    return Result.success();
  }
}
