package com.tiandu.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.service.TdUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:conf/spring/spring-core.xml")
public class SpringTest {

	@Autowired
	TdUserService tdUserService;
	
	@Test
	public void test(){
		TdUser u = new TdUser();
		u.setUname("test5");
		u.setUpassword("test5");
		u.setUstatus(Byte.valueOf("1"));
		u.setUtype(Byte.valueOf("1"));
		int id = tdUserService.insert(u);
		System.out.println(id);
		System.out.println(u.getUid());
	}
}
