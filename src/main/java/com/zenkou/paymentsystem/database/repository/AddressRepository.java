package com.zenkou.paymentsystem.database.repository;


import com.zenkou.paymentsystem.database.entity.db.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
