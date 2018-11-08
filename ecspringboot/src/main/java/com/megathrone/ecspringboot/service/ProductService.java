package com.megathrone.ecspringboot.service;

import com.megathrone.ecspringboot.bean.Category;
import com.megathrone.ecspringboot.bean.Product;
import com.megathrone.ecspringboot.dao.ProductDAO;
import com.megathrone.ecspringboot.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  @Autowired ProductDAO productDAO;
  @Autowired CategoryService categoryService;

  public void add(Product bean) {
    productDAO.save(bean);
  }

  public void delete(int id) {
    productDAO.deleteById(id);
  }

  public Product get(int id) {
    return productDAO.findById(id).get();
  }

  public void update(Product bean) {
    productDAO.save(bean);
  }

  public Page4Navigator<Product> list(int cid, int start, int size, int navigatePages) {
    Category category = categoryService.get(cid);
    Sort sort = new Sort(Sort.Direction.DESC, "id");
    Pageable pageable = new PageRequest(start, size, sort);
    Page<Product> pageFromJPA = productDAO.findByCategory(category, pageable);
    return new Page4Navigator<>(pageFromJPA, navigatePages);
  }
}
