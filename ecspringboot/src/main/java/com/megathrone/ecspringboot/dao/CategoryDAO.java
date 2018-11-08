package com.megathrone.ecspringboot.dao;
 
import org.springframework.data.jpa.repository.JpaRepository;

import com.megathrone.ecspringboot.bean.Category;

public interface CategoryDAO extends JpaRepository<Category,Integer>{

}
