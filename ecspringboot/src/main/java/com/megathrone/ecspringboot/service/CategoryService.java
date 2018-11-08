package com.megathrone.ecspringboot.service;

import com.megathrone.ecspringboot.bean.Category;
import com.megathrone.ecspringboot.dao.CategoryDAO;
import com.megathrone.ecspringboot.util.Page4Navigator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
  @Autowired CategoryDAO categoryDAO;

  public Page4Navigator<Category> list(int start, int size, int navigatePages) {
    Sort sort = new Sort(Sort.Direction.DESC, "id");
    Pageable pageable = new PageRequest(start, size, sort);
    Page pageFromJPA = categoryDAO.findAll(pageable);

    return new Page4Navigator<>(pageFromJPA, navigatePages);
  }

  public List<Category> list() {
    Sort sort = new Sort(Sort.Direction.DESC, "id");
    return categoryDAO.findAll(sort);
  }

  public void add(Category bean) {
    categoryDAO.save(bean);
  }

  public void delete(int id) {
    categoryDAO.deleteById(id);
  }

  public Category get(int id) {
    Category c = categoryDAO.findById(id).get();
    return c;
  }

  public void update(Category bean) {
    categoryDAO.save(bean);
  }
}
