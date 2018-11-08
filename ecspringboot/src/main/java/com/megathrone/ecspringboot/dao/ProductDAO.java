package com.megathrone.ecspringboot.dao;
 
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.megathrone.ecspringboot.bean.Category;
import com.megathrone.ecspringboot.bean.Product;

public interface ProductDAO extends JpaRepository<Product,Integer>{
	Page<Product> findByCategory(Category category, Pageable pageable);
}
