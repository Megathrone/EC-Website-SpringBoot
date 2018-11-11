package com.megathrone.ecspringboot.dao;

import com.megathrone.ecspringboot.bean.Product;
import com.megathrone.ecspringboot.bean.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewDAO extends JpaRepository<Review, Integer> {
  List<Review> findByProductOrderByIdDesc(Product product);

  int countByProduct(Product product);
}
