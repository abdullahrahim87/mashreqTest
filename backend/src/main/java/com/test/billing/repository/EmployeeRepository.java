package com.test.billing.repository;

import com.test.billing.entity.Customer;
import com.test.billing.entity.Employee;
import com.test.billing.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
