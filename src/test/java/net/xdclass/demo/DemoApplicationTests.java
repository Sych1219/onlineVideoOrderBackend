package net.xdclass.demo;

import io.jsonwebtoken.Claims;
import net.xdclass.demo.model.entity.User;
import net.xdclass.demo.utils.JWTUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void testJWT() {
		User user = new User();
		user.setId(12);
		user.setName("sun yi");

		String token = JWTUtils.getWebToken(user);
		try {
			Thread.sleep(4000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Claims claims = JWTUtils.checkJWT(token);
		System.out.println(claims.get("name"));
		assertEquals("sun yi", claims.get("name"));

	}

}
