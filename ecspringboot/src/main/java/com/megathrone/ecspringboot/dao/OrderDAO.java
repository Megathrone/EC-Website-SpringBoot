package com.megathrone.ecspringboot.dao;

import com.megathrone.ecspringboot.bean.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<Order, Integer> {}
