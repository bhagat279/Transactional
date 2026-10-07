package com.transactional.repository;

import com.transactional.entity.Orders;
import com.transactional.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Orders,Integer> {
}
