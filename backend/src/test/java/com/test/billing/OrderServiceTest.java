package com.test.billing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.billing.body.OrderBody;
import com.test.billing.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    OrderService orderService;
    @Test
    @Sql(scripts = "/scripts/test.sql")
    public void testEmployeeUser() throws Exception {
        String req = "{\"customerId\": 5, \"items\":[{\"productId\":10, \"quantity\":2}, {\"productId\":11, \"quantity\":1}, {\"productId\":12, \"quantity\":2}, {\"productId\":13, \"quantity\":1}]}";
        OrderBody orderBody = new ObjectMapper().readValue(req, OrderBody.class);
        orderBody = orderService.addOrder(orderBody);
        assert orderBody.getTotal() == 870F;
        assert orderBody.getDiscount() == 200F;
        assert orderBody.getNetTotal() == 670F;
    }
}
