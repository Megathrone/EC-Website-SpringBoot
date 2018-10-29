package com.megathrone.ecspringboot.service;

import com.megathrone.ecspringboot.bean.Category;
import com.megathrone.ecspringboot.dao.CategoryDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
  @Autowired CategoryDAO categoryDAO;

  public List<Category> list() {
    Sort sort = new Sort(Direction.DESC, "id");
    return categoryDAO.findAll(sort);
  }
}
