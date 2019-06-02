package com.test.billing.controller;

import com.test.billing.body.OrderBody;
import com.test.billing.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/order"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = {""}, method = RequestMethod.POST)
    public OrderBody addOrder(@RequestBody OrderBody orderBody) throws Exception {
        return orderService.addOrder(orderBody);
    }
}
