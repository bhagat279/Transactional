package com.transactional.handler;

import com.transactional.entity.Orders;
import com.transactional.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderHandler {

    @Autowired
    private OrderRepo orderRepo;

    @Transactional(propagation = Propagation.REQUIRED)
    public Orders saveOrder(Orders order)
    {
      return orderRepo.save(order);
    }


}
