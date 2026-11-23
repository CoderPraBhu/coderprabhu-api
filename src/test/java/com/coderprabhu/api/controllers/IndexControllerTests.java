
package com.coderprabhu.api.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
@DirtiesContext
public class IndexControllerTests {

	@Autowired
	private RestTestClient restTestClient;

	@Test
	public void testGreeting() throws Exception {
		restTestClient.get().uri("/hello")
				.exchange()
				.expectStatus().isOk();
	}

}
