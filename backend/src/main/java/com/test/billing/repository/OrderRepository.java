package com.test.billing.repository;

import com.test.billing.entity.Customer;
import com.test.billing.entity.CustomerOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<CustomerOrder, Long> {
}