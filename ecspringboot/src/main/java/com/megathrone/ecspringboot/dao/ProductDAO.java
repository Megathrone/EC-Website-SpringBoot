package com.megathrone.ecspringboot.dao;

import com.megathrone.ecspringboot.bean.Category;
import com.megathrone.ecspringboot.bean.Product;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<Product, Integer> {
  Page<Product> findByCategory(Category category, Pageable pageable);

  List<Product> findByCategoryOrderById(Category category);

  List<Product> findByNameLike(String keyword, Pageable pageable);
}
