package com.megathrone.ecspringboot.controller;

import com.megathrone.ecspringboot.bean.Property;
import com.megathrone.ecspringboot.service.PropertyService;
import com.megathrone.ecspringboot.util.PageForNavigator;
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
public class PropertyController {
  @Autowired PropertyService propertyService;

  @GetMapping("/categories/{cid}/properties")
  public PageForNavigator<Property> list(
      @PathVariable("cid") int cid,
      @RequestParam(value = "start", defaultValue = "0") int start,
      @RequestParam(value = "size", defaultValue = "5") int size) {
    start = start < 0 ? 0 : start;
    PageForNavigator<Property> page = propertyService.list(cid, start, size, 5);
    return page;
  }

  @GetMapping("/properties/{id}")
  public Property get(@PathVariable("id") int id) {
    Property bean = propertyService.get(id);
    return bean;
  }

  @PostMapping("/properties")
  public Object add(@RequestBody Property property) {
    propertyService.add(property);
    return property;
  }

  @DeleteMapping("/properties/{id}")
  public String delete(@PathVariable("id") int id, HttpServletRequest request) {
    propertyService.delete(id);
    return null;
  }

  @PutMapping("/properties")
  public Object update(@RequestBody Property property) {
    propertyService.update(property);
    return property;
  }
}
