package com.transactional.service;

import com.transactional.entity.Orders;
import com.transactional.entity.Product;
import com.transactional.handler.AuditLogHandler;
import com.transactional.handler.OrderHandler;
import com.transactional.handler.PaymentValidatorHandler;
import com.transactional.handler.ProductHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderProcessingService {

    @Autowired
    private OrderHandler orderHandler;

    @Autowired
    private ProductHandler productHandler;

    @Autowired
    private AuditLogHandler auditLogHandler;

    @Autowired
    private PaymentValidatorHandler paymentValidatorHandler;

    @Transactional(propagation = Propagation.REQUIRED)
    public Orders placOrder(Orders order)
    {
         //get product inventory
        Product product = productHandler.getProductById(order.getProductId());
        //validate stock availability
        validateStockAvailibility(order,product);

        // update total price in order entity
        order.setTotalPrice(order.getQty()*product.getPrice());

        Orders saveOrder = null;
        try {
            //save order
             saveOrder = orderHandler.saveOrder(order);

            //update stock in inventory
            updateProdcutInventoryStock(order, product);

            //Audit-log (req_new)
            auditLogHandler.auditLogDetails(order,"order placed succeeded");
        }catch (Exception ex)
        {
            auditLogHandler.auditLogDetails(order,"order placed failed");
        }

        //validatePayment();
        paymentValidatorHandler.validatePayment(order);

        return saveOrder;
    }

    private void validateStockAvailibility(Orders order, Product product) {
        if(order.getQty()>product.getProductQty())
        {
            throw new RuntimeException("insuficent stock");
        }
    }

    private void updateProdcutInventoryStock(Orders order, Product product) {
        int remaingStock = product.getProductQty() - order.getQty();
        product.setProductQty(remaingStock);
        productHandler.updateProdcutInventory(product);
    }
}
