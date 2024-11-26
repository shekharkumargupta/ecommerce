package com.skcodify.products;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	@LocalServerPort
	private int port;
	@Autowired
	private ProductController productController;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertThat(productController).isNotNull();
	}

	@Test
	void test_ping(){
		assertThat(this.testRestTemplate.getForObject("http://localhost:"
						+ port + "/v1/products/",
				String.class)).containsIgnoringCase("ok");
	}

	@Test
	void test_ping_with_mockMvc() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
				.andDo(print())
				.andExpect(status().isOk())
				;
		)
	}
}
