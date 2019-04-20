package com.zft;

import com.zft.entity.BlogProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoApplicationTests {

	@Autowired
	private BlogProperties blogProperties;


	@Test
	public void getHello() {
		Assert.assertEquals(blogProperties.getName(),"zft");
		Assert.assertEquals(blogProperties.getTitle(),"spring-boot-demo");
	}

	@Test
	public void contextLoads() {
	}

}
