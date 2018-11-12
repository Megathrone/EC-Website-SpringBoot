package com.megathrone.ecspringboot.dao;

import com.megathrone.ecspringboot.bean.Order;
import com.megathrone.ecspringboot.bean.OrderItem;
import com.megathrone.ecspringboot.bean.Product;
import com.megathrone.ecspringboot.bean.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemDAO extends JpaRepository<OrderItem, Integer> {
  List<OrderItem> findByOrderOrderByIdDesc(Order order);

  List<OrderItem> findByProduct(Product product);

  List<OrderItem> findByUserAndOrderIsNull(User user);
}
