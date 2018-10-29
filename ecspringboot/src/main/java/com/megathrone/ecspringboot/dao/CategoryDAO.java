package com.megathrone.ecspringboot.dao;

import com.megathrone.ecspringboot.bean.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category,Integer> {

}
