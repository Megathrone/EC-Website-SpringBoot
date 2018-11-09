package com.megathrone.ecspringboot.web;

import com.megathrone.ecspringboot.bean.Order;
import com.megathrone.ecspringboot.service.OrderItemService;
import com.megathrone.ecspringboot.service.OrderService;
import com.megathrone.ecspringboot.util.Page4Navigator;
import com.megathrone.ecspringboot.util.Result;
import java.io.IOException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
  @Autowired OrderService orderService;
  @Autowired OrderItemService orderItemService;

  @GetMapping("/orders")
  public Page4Navigator<Order> list(
      @RequestParam(value = "start", defaultValue = "0") int start,
      @RequestParam(value = "size", defaultValue = "5") int size)
      throws Exception {
    start = start < 0 ? 0 : start;
    Page4Navigator<Order> page = orderService.list(start, size, 5);
    orderItemService.fill(page.getContent());
    orderService.removeOrderFromOrderItem(page.getContent());
    return page;
  }

  @PutMapping("deliveryOrder/{oid}")
  public Object deliveryOrder(@PathVariable int oid) throws IOException {
    Order o = orderService.get(oid);
    o.setDeliveryDate(new Date());
    o.setStatus(OrderService.waitConfirm);
    orderService.update(o);
    return Result.success();
  }
}
