package com.megathrone.ecspringboot.dao;
 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megathrone.ecspringboot.bean.Product;
import com.megathrone.ecspringboot.bean.ProductImage;

public interface ProductImageDAO extends JpaRepository<ProductImage,Integer>{
	public List<ProductImage> findByProductAndTypeOrderByIdDesc(Product product, String type);
	
}
