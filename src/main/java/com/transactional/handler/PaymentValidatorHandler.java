package com.transactional.handler;

import ch.qos.logback.core.util.Loader;
import com.transactional.entity.AuditLog;
import com.transactional.entity.Orders;
import com.transactional.repository.AuditRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PaymentValidatorHandler {

    @Autowired
    private AuditRepo auditRepo;

    @Transactional(propagation = Propagation.MANDATORY)
    public void validatePayment(Orders orders)
    {
        boolean paymentSuccessful = false;

        if(!paymentSuccessful)
        {
            AuditLog paymentFailureLog = new AuditLog();
            paymentFailureLog.setTimestamp(LocalDateTime.now());
            paymentFailureLog.setOrderId(orders.getOrderId());
            paymentFailureLog.setAction("payment failed");


//            if(orders.getTotalPrice()>1000)
//            {
//                throw new RuntimeException("price is high");
//            }
            auditRepo.save(paymentFailureLog);
        }
    }
}
