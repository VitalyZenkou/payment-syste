package com.zenkou.paymentsystem.database.repository;

import com.zenkou.paymentsystem.database.entity.db.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface BankAccountRepository extends CrudRepository<BankAccount, Long> {
}
