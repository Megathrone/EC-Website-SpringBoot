package com.megathrone.ecspringboot.dao;

import com.megathrone.ecspringboot.bean.Category;
import com.megathrone.ecspringboot.bean.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyDAO extends JpaRepository<Property, Integer> {
  Page<Property> findByCategory(Category category, Pageable pageable);
}
