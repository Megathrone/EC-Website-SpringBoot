package com.megathrone.ecspringboot.service;

import com.megathrone.ecspringboot.bean.Order;
import com.megathrone.ecspringboot.bean.OrderItem;
import com.megathrone.ecspringboot.dao.OrderItemDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
  @Autowired OrderItemDAO orderItemDAO;
  @Autowired ProductImageService productImageService;

  public void fill(List<Order> orders) {
    for (Order order : orders) fill(order);
  }

  public void fill(Order order) {
    List<OrderItem> orderItems = listByOrder(order);
    float total = 0;
    int totalNumber = 0;
    for (OrderItem oi : orderItems) {
      total += oi.getNumber() * oi.getProduct().getPromotePrice();
      totalNumber += oi.getNumber();
      productImageService.setFirstProdutImage(oi.getProduct());
    }
    order.setTotal(total);
    order.setOrderItems(orderItems);
    order.setTotalNumber(totalNumber);
    order.setOrderItems(orderItems);
  }

  public List<OrderItem> listByOrder(Order order) {
    return orderItemDAO.findByOrderOrderByIdDesc(order);
  }
}
