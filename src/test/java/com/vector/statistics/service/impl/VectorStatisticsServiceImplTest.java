package com.vector.statistics.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vector.statistics.dto.VectorDTO;
import com.vector.statistics.sevice.impl.VectorStatisticsServiceImpl;

@ExtendWith(MockitoExtension.class)
public class VectorStatisticsServiceImplTest {

	@InjectMocks
	private VectorStatisticsServiceImpl vectorStatisticsServiceImpl;

	private static VectorDTO vectorDto;
	private static VectorDTO emptyVectorDto;

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@BeforeAll
	static void setUpClass() {
		vectorDto = buildVectorDtoStub(1, Arrays.asList(5, 8));
		emptyVectorDto = buildVectorDtoStub(2,new ArrayList<>());
	}

	private static VectorDTO buildVectorDtoStub(int vectorId, List<Integer> vectorElements) {
		VectorDTO vectorDto = new VectorDTO();
		vectorDto.setVectorId(vectorId);
		vectorDto.setVectorElements(vectorElements);
		return vectorDto;
	}

	@AfterAll
	static void tearDownClass() {
		vectorDto=null;
		emptyVectorDto=null;
	}

	/**
	 * calculateVectorMeanHappyPath is a test case, which verifies the happy path
	 * scenario for mean value calculation
	 */
	@Test
	public void calculateVectorMeanHappyPath() {
		double meanValue = vectorStatisticsServiceImpl.calculateVectorMean(vectorDto);
		Assertions.assertEquals(6.5, meanValue);
	}

	/**
	 * calculateVectorStandardDeviationHappyPath is a test case, which verifies the
	 * happy path scenario for standard deviation value calculation
	 */
	@Test
	public void calculateVectorStandardDeviationHappyPath() {
		double standardDeviation = vectorStatisticsServiceImpl.calculateVectorStandardDeviation(vectorDto);
		Assertions.assertEquals(1.5, standardDeviation);
	}
	
	/**
	 * calculateVectorMeanEmptyVector is a test case, which verifies the empty vector
	 * scenario for mean value calculation
	 */
	@Test
	public void calculateVectorMeanEmptyVector() {
		double meanValue = vectorStatisticsServiceImpl.calculateVectorMean(emptyVectorDto);
		Assertions.assertEquals(0.0, meanValue);
	}
	
	/**
	 * calculateVectorStandardDeviationEmptyVector is a test case, which verifies the
	 * empty vector scenario for standard deviation value calculation
	 */
	@Test
	public void calculateVectorStandardDeviationEmptyVector() {
		double standardDeviation = vectorStatisticsServiceImpl.calculateVectorStandardDeviation(emptyVectorDto);
		Assertions.assertEquals(0.0, standardDeviation);
	}

}
