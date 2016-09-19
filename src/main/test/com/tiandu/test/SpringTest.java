package com.tiandu.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.service.TdUserService;
import com.tiandu.order.service.TdOrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:conf/spring/spring-core.xml")
public class SpringTest {

	@Autowired
	private TdOrderService tdOrderService;
	
	@Test
	public void test(){
		tdOrderService.completeOrderBySystemJob();
	}
}
