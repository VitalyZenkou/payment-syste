package com.zenkou.paymentsystem.database.repository;

import com.zenkou.paymentsystem.database.entity.db.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
