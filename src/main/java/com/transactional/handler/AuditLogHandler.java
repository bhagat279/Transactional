package com.transactional.handler;

import com.transactional.entity.AuditLog;
import com.transactional.entity.Orders;
import com.transactional.repository.AuditRepo;
import com.transactional.repository.OrderRepo;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class AuditLogHandler {

    @Autowired
    private AuditRepo auditRepo;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void auditLogDetails(Orders order, String action)
    {
        AuditLog auditLog = new AuditLog();

        auditLog.setOrderId(order.getOrderId());
        auditLog.setAction(action);
        auditLog.setTimestamp(LocalDateTime.now());

        auditRepo.save(auditLog);
    }
}
