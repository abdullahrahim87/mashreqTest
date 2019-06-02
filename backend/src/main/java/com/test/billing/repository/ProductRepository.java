package com.test.billing.repository;

import com.test.billing.entity.CustomerOrder;
import com.test.billing.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends CrudRepository<Product, Long> {
    @Query( "select o from Product o where ID in :ids" )
    List<Product> findByProductIds(@Param("ids") List<Long> inventoryIdList);
}
