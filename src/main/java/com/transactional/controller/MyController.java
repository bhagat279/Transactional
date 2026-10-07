package com.transactional.controller;

import com.transactional.entity.Orders;
import com.transactional.entity.Product;
//import com.transactional.service.KeepProduct;
import com.transactional.service.OrderProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private OrderProcessingService orderProcessingService;

  /*  @Autowired
    private KeepProduct keepProduct;*/
/*
    @PostMapping("/keepproduct")
    public ResponseEntity<Product> keepProduct(@RequestBody Product product)
    {
        return ResponseEntity.ok(keepProduct.keepProduct(product));
    }*/


    @PostMapping("/placeorder")
    public ResponseEntity<Orders> placeOrder(@RequestBody Orders order)
    {
       return ResponseEntity.ok(orderProcessingService.placOrder(order));

    }


}
