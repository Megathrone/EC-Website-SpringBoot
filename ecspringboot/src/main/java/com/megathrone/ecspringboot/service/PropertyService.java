package com.megathrone.ecspringboot.service;

import com.megathrone.ecspringboot.bean.Category;
import com.megathrone.ecspringboot.bean.Property;
import com.megathrone.ecspringboot.dao.PropertyDAO;
import com.megathrone.ecspringboot.util.PageForNavigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PropertyService {

  @Autowired PropertyDAO propertyDAO;

  @Autowired CategoryService categoryService;

  public void add(Property bean) {
    propertyDAO.save(bean);
  }

  public void delete(int id) {
    propertyDAO.deleteById(id);
  }

  public Property get(int id) {
    return propertyDAO.findById(id).get();
  }

  public void update(Property bean) {
    propertyDAO.save(bean);
  }

  public PageForNavigator<Property> list(int cid, int start, int size, int navigatePages) {
    Category category = categoryService.get(cid);

    Sort sort = new Sort(Sort.Direction.DESC, "id");
    Pageable pageable = new PageRequest(start, size, sort);

    Page<Property> pageFromJPA = propertyDAO.findByCategory(category, pageable);

    return new PageForNavigator<>(pageFromJPA, navigatePages);
  }
}