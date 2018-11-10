package com.megathrone.ecspringboot.service;

import com.megathrone.ecspringboot.bean.Order;
import com.megathrone.ecspringboot.bean.OrderItem;
import com.megathrone.ecspringboot.bean.Product;
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

  public void update(OrderItem orderItem) {
    orderItemDAO.save(orderItem);
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

  public void add(OrderItem orderItem) {
    orderItemDAO.save(orderItem);
  }

  public OrderItem get(int id) {
    return orderItemDAO.findOne(id);
  }

  public void delete(int id) {
    orderItemDAO.delete(id);
  }

  public int getSaleCount(Product product) {
    List<OrderItem> ois = listByProduct(product);
    int result = 0;
    for (OrderItem oi : ois) {
      if (null != oi.getOrder())
        if (null != oi.getOrder() && null != oi.getOrder().getPayDate()) result += oi.getNumber();
    }
    return result;
  }

  public List<OrderItem> listByProduct(Product product) {
    return orderItemDAO.findByProduct(product);
  }

  public List<OrderItem> listByOrder(Order order) {
    return orderItemDAO.findByOrderOrderByIdDesc(order);
  }
}
