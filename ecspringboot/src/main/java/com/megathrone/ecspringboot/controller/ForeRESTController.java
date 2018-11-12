package com.megathrone.ecspringboot.web;

import com.megathrone.ecspringboot.bean.Category;
import com.megathrone.ecspringboot.bean.OrderItem;
import com.megathrone.ecspringboot.bean.Product;
import com.megathrone.ecspringboot.bean.ProductImage;
import com.megathrone.ecspringboot.bean.PropertyValue;
import com.megathrone.ecspringboot.bean.Review;
import com.megathrone.ecspringboot.bean.User;
import com.megathrone.ecspringboot.service.CategoryService;
import com.megathrone.ecspringboot.service.OrderItemService;
import com.megathrone.ecspringboot.service.ProductImageService;
import com.megathrone.ecspringboot.service.ProductService;
import com.megathrone.ecspringboot.service.PropertyValueService;
import com.megathrone.ecspringboot.service.ReviewService;
import com.megathrone.ecspringboot.service.UserService;
import com.megathrone.ecspringboot.util.ProductAllComparator;
import com.megathrone.ecspringboot.util.ProductDateComparator;
import com.megathrone.ecspringboot.util.ProductPriceComparator;
import com.megathrone.ecspringboot.util.ProductReviewComparator;
import com.megathrone.ecspringboot.util.ProductSaleCountComparator;
import com.megathrone.ecspringboot.util.Result;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
public class ForeRESTController {
  @Autowired CategoryService categoryService;
  @Autowired ProductService productService;
  @Autowired UserService userService;
  @Autowired ProductImageService productImageService;
  @Autowired PropertyValueService propertyValueService;
  @Autowired ReviewService reviewService;
  @Autowired OrderItemService orderItemService;

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
      String message = "用户名已经被使用,不能使用";
      return Result.fail(message);
    }

    user.setPassword(password);

    userService.add(user);

    return Result.success();
  }

  @PostMapping("/forelogin")
  public Object login(@RequestBody User userParam, HttpSession session) {
    String name = userParam.getName();
    name = HtmlUtils.htmlEscape(name);

    User user = userService.get(name, userParam.getPassword());
    if (null == user) {
      String message = "账号密码错误";
      return Result.fail(message);
    } else {
      session.setAttribute("user", user);
      return Result.success();
    }
  }

  @GetMapping("/foreproduct/{pid}")
  public Object product(@PathVariable("pid") int pid) {
    Product product = productService.get(pid);
    List<ProductImage> productSingleImages = productImageService.listSingleProductImages(product);
    List<ProductImage> productDetailImages = productImageService.listDetailProductImages(product);
    product.setProductSingleImages(productSingleImages);
    product.setProductDetailImages(productDetailImages);

    List<PropertyValue> pvs = propertyValueService.list(product);
    List<Review> reviews = reviewService.list(product);
    productService.setSaleAndReviewNumber(product);
    productImageService.setFirstProdutImage(product);

    Map<String, Object> map = new HashMap<>();
    map.put("product", product);
    map.put("pvs", pvs);
    map.put("reviews", reviews);

    return Result.success(map);
  }

  @GetMapping("/forecheckLogin")
  public Object checkLogin(HttpSession httpSession) {
    User user = (User) httpSession.getAttribute("user");
    if (user != null) {
      return Result.success();
    }
    return Result.fail("未登录");
  }

  @GetMapping("forecategory/{cid}")
  public Object category(@PathVariable("cid") int cid, String sort) {
    Category category = categoryService.get(cid);
    productService.fill(category);
    productService.setSaleAndReviewNumber(category.getProducts());
    categoryService.removeCategoryFromProduct(category);

    if (sort != null) {
      switch (sort) {
        case "review":
          Collections.sort(category.getProducts(), new ProductReviewComparator());
          break;
        case "date":
          Collections.sort(category.getProducts(), new ProductDateComparator());
          break;
        case "saleCount":
          Collections.sort(category.getProducts(), new ProductSaleCountComparator());
          break;
        case "price":
          Collections.sort(category.getProducts(), new ProductPriceComparator());
          break;
        case "all":
          Collections.sort(category.getProducts(), new ProductAllComparator());
          break;
      }
    }
    return category;
  }

  @PostMapping("foresearch")
  public Object search(String keyword) {
    if (null == keyword) keyword = "";
    List<Product> ps = productService.search(keyword, 0, 20);
    productImageService.setFirstProdutImages(ps);
    productService.setSaleAndReviewNumber(ps);
    return ps;
  }

  @GetMapping("forebuyone")
  public Object buyone(int pid, int num, HttpSession session) {
    return buyoneAndAddCart(pid, num, session);
  }

  private int buyoneAndAddCart(int pid, int num, HttpSession session) {
    Product product = productService.get(pid);
    int oiid = 0;

    User user = (User) session.getAttribute("user");
    boolean found = false;
    List<OrderItem> ois = orderItemService.listByUser(user);
    for (OrderItem oi : ois) {
      if (oi.getProduct().getId() == product.getId()) {
        oi.setNumber(oi.getNumber() + num);
        orderItemService.update(oi);
        found = true;
        oiid = oi.getId();
        break;
      }
    }
    if (!found) {
      OrderItem orderItem = new OrderItem();
      orderItem.setUser(user);
      orderItem.setProduct(product);
      orderItem.setNumber(num);
      orderItemService.add(orderItem);
      oiid = orderItem.getId();
    }

    return oiid;
  }
}
