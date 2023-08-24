package com.vector.statistics.service.impl;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vector.statistics.dto.ServiceResponse;
import com.vector.statistics.dto.VectorDTO;
import com.vector.statistics.dto.VectorStatistics;
import com.vector.statistics.entity.Vector;
import com.vector.statistics.entity.VectorElement;
import com.vector.statistics.exception.VectorNotFoundException;
import com.vector.statistics.repository.VectorRepository;
import com.vector.statistics.service.VectorStatisticsService;
import com.vector.statistics.sevice.impl.VectorDataServiceImpl;

@ExtendWith(MockitoExtension.class)
public class VectorDataServiceImplTest {

	@InjectMocks
	private VectorDataServiceImpl VectorDataService;

	@Mock
	private VectorRepository vectorRepository;

	@Mock
	private VectorStatisticsService vectorStatisticsService;

	private static Vector vectorEntity;

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@BeforeAll
	static void setUpClass() {
		vectorEntity = buildVectorEntitySyubs(1, 2);
		vectorEntity.setVectorElements(Arrays.asList(buildVectorElementStub(1, 1, 5, vectorEntity),
				buildVectorElementStub(1, 2, 8, vectorEntity)));
	}

	@AfterAll
	static void tearDownClass() {
	}

	/**
	 * createNewVectorHappyPath test case tests happy path scenario where vector of
	 * length 2 is created in the database.
	 */

	@Test
	public void createNewVectorHappyPath() {
		Mockito.when(vectorRepository.save(Mockito.any())).thenReturn(vectorEntity);
		ServiceResponse<VectorDTO> vectorDtoResponse = VectorDataService.createNewVector(2);
		VectorDTO vectorDto = vectorDtoResponse.getPayload();
		Assertions.assertNotNull(vectorDtoResponse);
		Assertions.assertEquals(2, vectorDto.getVectorElements().size());
		Assertions.assertEquals(1, vectorDto.getVectorId());
		Assertions.assertNull(vectorDtoResponse.getErrorMsgs());
	}

	/**
	 * retrieveVectorStatsHappyPath tests the happy path scenario for vehicle
	 * statistics
	 */

	@Test
	public void retrieveVectorStatsHappyPath() {
		Mockito.when(vectorRepository.findById(1)).thenReturn(Optional.of(vectorEntity));
		Mockito.when(vectorStatisticsService.calculateVectorMean(Mockito.any())).thenReturn(6.5);
		Mockito.when(vectorStatisticsService.calculateVectorStandardDeviation(Mockito.any())).thenReturn(1.5);
		ServiceResponse<VectorStatistics> vectorStatisticResponse = VectorDataService.retrieveVectorStats(1);
		Assertions.assertNotNull(vectorStatisticResponse);
		final VectorStatistics vectorStatistics = vectorStatisticResponse.getPayload();
		Assertions.assertNotNull(vectorStatistics);
		Assertions.assertEquals(6.5, vectorStatistics.getMeanValue());
		Assertions.assertEquals(1.5, vectorStatistics.getStandardDeviation());
		Assertions.assertNull(vectorStatisticResponse.getErrorMsgs());
	}
	
	/**
	 * vectorNotFound_scenario test case covers the scenario when user provide vector id for which
	 * vector is not associted. In that case it will throw VectorNotFoundException
	 */
	
	@Test
	public void vectorNotFound_scenario() {
		Mockito.when(vectorRepository.findById(10)).thenReturn(Optional.empty());
		Assertions.assertThrows(VectorNotFoundException.class, () -> {
			VectorDataService.retrieveVectorStats(10);
        });
	}
	

	private static Vector buildVectorEntitySyubs(int vectorId, int elementCount) {
		Vector vectorEntity = new Vector();
		vectorEntity.setElementCount(elementCount);
		vectorEntity.setVectorId(vectorId);
		return vectorEntity;
	}

	private static VectorElement buildVectorElementStub(int elementId, int elementIndex, int elementValue,
			Vector vectorEntity) {
		VectorElement vectorElement = new VectorElement();
		vectorElement.setElementId(elementId);
		vectorElement.setElementIndex(elementIndex);
		vectorElement.setElementValue(elementValue);
		vectorElement.setVector(vectorEntity);
		return vectorElement;
	}

}
