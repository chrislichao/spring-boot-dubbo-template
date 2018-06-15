package org.ys.soft.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainTest {
	@Test
	public void testHello(){
		System.out.println("This is spring boot test.");
	}
}
