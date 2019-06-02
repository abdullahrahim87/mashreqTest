package com.test.billing;

import com.test.billing.service.discount.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class BillingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingApplication.class, args);
	}

	@Bean
	public DiscountCalculatorChain getDiscountCalculatorChain() {
		return new DiscountCalculatorChainImpl(
				new ArrayList<DiscountCalculator>(){{
					add(new PercentageBasedDiscountCalculator());
					add(new BucketBasedDiscountCalculator());
				}}
		);
	}
}
