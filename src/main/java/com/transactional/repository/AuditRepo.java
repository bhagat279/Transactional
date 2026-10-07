package com.transactional.repository;

import com.transactional.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepo extends JpaRepository<AuditLog,Integer> {
}
