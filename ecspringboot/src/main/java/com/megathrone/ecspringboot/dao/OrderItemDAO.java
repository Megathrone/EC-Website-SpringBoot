package com.megathrone.ecspringboot.dao;

import com.megathrone.ecspringboot.bean.Order;
import com.megathrone.ecspringboot.bean.OrderItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemDAO extends JpaRepository<OrderItem, Integer> {
  List<OrderItem> findByOrderOrderByIdDesc(Order order);
}
