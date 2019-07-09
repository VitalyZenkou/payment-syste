package com.zenkou.paymentsystem.database.repository;

import com.zenkou.paymentsystem.database.entity.db.CreditCard;
import org.springframework.data.repository.CrudRepository;

public interface CreditCardRepository extends CrudRepository<CreditCard, Long> {
}
