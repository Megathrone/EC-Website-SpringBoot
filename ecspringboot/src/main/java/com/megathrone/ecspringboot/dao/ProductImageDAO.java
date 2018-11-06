package com.megathrone.ecspringboot.dao;

import com.megathrone.ecspringboot.bean.Product;
import com.megathrone.ecspringboot.bean.ProductImage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageDAO extends JpaRepository<ProductImage, Integer> {
  public List<ProductImage> findByProductAndTypeOrderByIdDesc(Product product, String type);
}
