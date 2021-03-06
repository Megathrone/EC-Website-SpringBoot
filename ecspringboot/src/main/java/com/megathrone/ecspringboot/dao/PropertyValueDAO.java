package com.megathrone.ecspringboot.dao;

import com.megathrone.ecspringboot.bean.Product;
import com.megathrone.ecspringboot.bean.Property;
import com.megathrone.ecspringboot.bean.PropertyValue;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyValueDAO extends JpaRepository<PropertyValue, Integer> {

  List<PropertyValue> findByProductOrderByIdDesc(Product product);

  PropertyValue getByPropertyAndProduct(Property property, Product product);
}
