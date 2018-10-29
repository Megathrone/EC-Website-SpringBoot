package com.megathrone.ecspringboot.service;

import com.megathrone.ecspringboot.bean.Category;
import com.megathrone.ecspringboot.dao.CategoryDAO;
import com.megathrone.ecspringboot.util.PageForNavigator;
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

  public PageForNavigator<Category> list(int start, int size, int navigatePages) {
    Sort sort = new Sort(Sort.Direction.DESC, "id");
    Pageable pageable = new PageRequest(start, size, sort);
    Page pageFromJPA = categoryDAO.findAll(pageable);

    return new PageForNavigator<>(pageFromJPA, navigatePages);
  }

  public List<Category> list() {
    Sort sort = new Sort(Sort.Direction.DESC, "id");
    return categoryDAO.findAll(sort);
  }
}
