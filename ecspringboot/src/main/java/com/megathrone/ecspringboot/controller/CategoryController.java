package com.megathrone.ecspringboot.controller;

import com.megathrone.ecspringboot.bean.Category;
import com.megathrone.ecspringboot.service.CategoryService;
import com.megathrone.ecspringboot.util.ImageUtil;
import com.megathrone.ecspringboot.util.PageForNavigator;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

  @PostMapping("/categories")
  public Object add(Category bean, MultipartFile image, HttpServletRequest request)
      throws Exception {
    categoryService.add(bean);
    saveOrUpdateImageFile(bean, image, request);
    return bean;
  }

  public void saveOrUpdateImageFile(Category bean, MultipartFile image, HttpServletRequest request)
      throws IOException {
    File imageFolder = new File(request.getServletContext().getRealPath("img/category"));
    File file = new File(imageFolder, bean.getId() + ".jpg");
    if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
    image.transferTo(file);
    BufferedImage img = ImageUtil.change2jpg(file);
    ImageIO.write(img, "jpg", file);
  }
}
