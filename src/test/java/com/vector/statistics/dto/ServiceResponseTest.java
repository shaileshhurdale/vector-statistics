package com.vector.statistics.dto;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ServiceResponseTest {

	private static ServiceResponse<String> s1;
	private static ServiceResponse<String> s2;
	private static ServiceResponse<String> s3;

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@BeforeAll
	static void setUpClass() {
		String nullResponse = null;
		s1 = buildServiceResponseStubs("sample payload v1", null);
		s2 = buildServiceResponseStubs("sample payload v1", null);
		s3 = buildServiceResponseStubs(nullResponse, Arrays.asList("Vector Not found with ID 1"));

	}

	@AfterAll
	static void tearDownClass() {
		s1 = s2 = s3 = null;
	}

	private static ServiceResponse<String> buildServiceResponseStubs(String payload, List<String> errorMsg) {
		ServiceResponse<String> s = new ServiceResponse<>(payload, errorMsg);
		return s;
	}

	@Test
	public void serviceResponseEqualityTest() {
		Assertions.assertEquals(s1, s2);
		Assertions.assertEquals(s1.hashCode(), s2.hashCode());
		Assertions.assertNotEquals(s1, s3);
	}

	@Test
	public void serviceResponseToStringTest() {
		Assertions.assertEquals("ServiceResponse(payload=sample payload v1, errorMsgs=null)", s1.toString());
	}

}
