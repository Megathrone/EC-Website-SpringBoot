package com.megathrone.ecspringboot.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "category")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  int id;

  String name;

  @Transient List<Product> products;

  @Transient List<List<Product>> productByRow;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  public List<List<Product>> getProductByRow() {
    return productByRow;
  }

  public void setProductByRow(List<List<Product>> productByRow) {
    this.productByRow = productByRow;
  }

  @Override
  public String toString() {
    return "Category [id=" + id + ", name=" + name + "]";
  }
}
