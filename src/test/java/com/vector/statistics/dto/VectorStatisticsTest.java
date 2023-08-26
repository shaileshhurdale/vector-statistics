package com.vector.statistics.dto;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VectorStatisticsTest {

	private static VectorStatistics v1;
	private static VectorStatistics v2;
	private static VectorStatistics v3;
	private static VectorStatistics v4;

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@BeforeAll
	static void setUpClass() {
		v1 = buildVectorStatisticsStubs(5.4, 1.8);
		v2 = buildVectorStatisticsStubs(5.4, 1.8);
		v3 = buildVectorStatisticsStubs(4.0, 1.8);
		v4 = buildVectorStatisticsStubs(5.4, 2.4);
	}

	@AfterAll
	static void tearDownClass() {
		v1=v2=v3=v4=null;
	}

	@Test
	public void VectorStatisticsEqualityTest() {
		Assertions.assertEquals(v1, v2);
		Assertions.assertEquals(v1.hashCode(), v1.hashCode());
	}

	@Test
	public void VectorStatisticsToStringTest() {
		Assertions.assertNotNull(v1.toString());
		Assertions.assertEquals("VectorStatistics(meanValue=5.4, standardDeviation=1.8)", v1.toString());
	}

	@Test
	public void VectorStatisticsNotEqualTest() {
		Assertions.assertNotEquals(v1, v3);
		Assertions.assertNotEquals(v1, v4);
	}

	private static VectorStatistics buildVectorStatisticsStubs(double meanValue, double standardDeviation) {
		VectorStatistics v = new VectorStatistics();
		v.setMeanValue(meanValue);
		v.setStandardDeviation(standardDeviation);
		return v;
	}
}
