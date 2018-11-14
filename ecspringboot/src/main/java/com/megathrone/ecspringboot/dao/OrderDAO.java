package com.megathrone.ecspringboot.dao;

import com.megathrone.ecspringboot.bean.Order;
import com.megathrone.ecspringboot.bean.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<Order, Integer> {
  public List<Order> findByUserAndStatusNotOrderByIdDesc(User user, String status);
}
