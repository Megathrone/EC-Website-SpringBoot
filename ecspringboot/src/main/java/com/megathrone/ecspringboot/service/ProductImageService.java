package com.megathrone.ecspringboot.service;

import com.megathrone.ecspringboot.bean.Product;
import com.megathrone.ecspringboot.bean.ProductImage;
import com.megathrone.ecspringboot.dao.ProductImageDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductImageService {

  public static final String type_single = "single";
  public static final String type_detail = "detail";

  @Autowired ProductImageDAO productImageDAO;
  @Autowired ProductService productService;

  public void add(ProductImage bean) {
    productImageDAO.save(bean);
  }

  public void delete(int id) {
    productImageDAO.deleteById(id);
  }

  public ProductImage get(int id) {
    return productImageDAO.findById(id).get();
  }

  public List<ProductImage> listSingleProductImages(Product product) {
    return productImageDAO.findByProductAndTypeOrderByIdDesc(product, type_single);
  }

  public List<ProductImage> listDetailProductImages(Product product) {
    return productImageDAO.findByProductAndTypeOrderByIdDesc(product, type_detail);
  }

  public void setFirstProdutImage(Product product) {
    List<ProductImage> singleImages = listSingleProductImages(product);
    if (!singleImages.isEmpty()) product.setFirstProductImage(singleImages.get(0));
    else
      product.setFirstProductImage(
          new ProductImage()); // 这样做是考虑到产品还没有来得及设置图片，但是在订单后台管理里查看订单项的对应产品图片。
  }

  public void setFirstProdutImages(List<Product> products) {
    for (Product product : products) setFirstProdutImage(product);
  }
}