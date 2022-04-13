package com.cl.controller;

import com.cl.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("order")
    public Boolean order(Long id) {
        return orderService.order(id);
    }

    @GetMapping("orderRedisson")
    public Boolean orderRedisson(Long id) {
        return orderService.orderRedisson(id);
    }
}
