package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
		"spring.datasource.url=jdbc:h2:mem:demo-test;DB_CLOSE_DELAY=-1",
		"spring.jpa.hibernate.ddl-auto=create-drop",
		"spring.h2.console.enabled=false"
})
class DemoApplicationTests {

	@Autowired
	private ProductRepository productRepository;

	private final HttpClient httpClient = HttpClient.newHttpClient();

	@LocalServerPort
	private int port;

	@Test
	void contextLoads() {
	}

	@Test
	void serverStartsOnAnAvailablePortForTests() {
		assertTrue(port > 0);
	}

	@Test
	void productCrudAndNotFoundUseTheRestApi() throws Exception {
		String productJson = "{\"id\":null,\"name\":\"Mon kiem thu\",\"description\":\"San pham kiem tra API\","
				+ "\"price\":42000,\"quantity\":3,\"categoryId\":1}";
		HttpResponse<String> created = send("POST", "/api/products", productJson);

		assertEquals(201, created.statusCode());
		Matcher idMatcher = Pattern.compile("\\\"id\\\"\\s*:\\s*(\\d+)").matcher(created.body());
		assertTrue(idMatcher.find());
		Integer id = Integer.valueOf(idMatcher.group(1));
		assertNotNull(id);

		HttpResponse<String> found = send("GET", "/api/products/" + id, null);
		assertEquals(200, found.statusCode());
		assertTrue(found.body().contains("Mon kiem thu"));

		String updatedProductJson = "{\"name\":\"Mon da cap nhat\",\"description\":\"Da sua\","
				+ "\"price\":45000,\"quantity\":4,\"categoryId\":1}";
		HttpResponse<String> updated = send("PUT", "/api/products/" + id, updatedProductJson);
		assertEquals(200, updated.statusCode());
		assertTrue(updated.body().contains("Mon da cap nhat"));

		HttpResponse<String> missing = send("GET", "/api/products/999999", null);
		assertEquals(404, missing.statusCode());
		assertTrue(missing.body().contains("tìm thấy sản phẩm"));

		HttpResponse<String> invalidThreshold = send("GET", "/api/products/low-stock?threshold=-1", null);
		assertEquals(400, invalidThreshold.statusCode());
		assertTrue(invalidThreshold.body().contains("Ngưỡng tồn kho"));

		HttpResponse<String> deleted = send("DELETE", "/api/products/" + id, null);
		assertEquals(200, deleted.statusCode());
		assertTrue(deleted.body().contains("\"message\":\"Xóa thành công\""));
		assertTrue(productRepository.findById(id).isEmpty());
	}

	@Test
	void lowStockReportUsesJdbcQuery() throws Exception {
		Product product = productRepository.save(new Product(null, "Mon ton kho thap", "Kiem tra JDBC", 18000, 2, 1));
		try {
			HttpResponse<String> response = send("GET", "/api/products/low-stock?threshold=5", null);
			assertEquals(200, response.statusCode());
			assertTrue(response.body().contains("\"id\":" + product.getId()));
		} finally {
			productRepository.deleteById(product.getId());
		}
	}

	private HttpResponse<String> send(String method, String path, String body) throws Exception {
		HttpRequest.Builder request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:" + port + path));
		if (body == null) {
			request.method(method, HttpRequest.BodyPublishers.noBody());
		} else {
			request.header("Content-Type", "application/json")
					.method(method, HttpRequest.BodyPublishers.ofString(body));
		}
		return httpClient.send(request.build(), HttpResponse.BodyHandlers.ofString());
	}

}
