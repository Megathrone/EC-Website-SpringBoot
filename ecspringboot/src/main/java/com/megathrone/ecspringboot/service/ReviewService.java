package com.megathrone.ecspringboot.service;

import com.megathrone.ecspringboot.bean.Product;
import com.megathrone.ecspringboot.bean.Review;
import com.megathrone.ecspringboot.dao.ReviewDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
  @Autowired ReviewDAO reviewDAO;
  @Autowired ProductService productService;

  public void add(Review review) {
    reviewDAO.save(review);
  }

  public List<Review> list(Product product) {
    List<Review> result = reviewDAO.findByProductOrderByIdDesc(product);
    return result;
  }

  public int getCount(Product product) {
    return reviewDAO.countByProduct(product);
  }
}
