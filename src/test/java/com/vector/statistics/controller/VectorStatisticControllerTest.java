package com.vector.statistics.controller;

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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.vector.statistics.dto.ServiceResponse;
import com.vector.statistics.dto.VectorDTO;
import com.vector.statistics.dto.VectorStatistics;
import com.vector.statistics.exception.VectorNotFoundException;
import com.vector.statistics.service.VectorDataService;

@ExtendWith(MockitoExtension.class)
public class VectorStatisticControllerTest {

	@InjectMocks
	private VectorStatisticController vectorStatisticController;
	
	@Mock
	private VectorDataService vectorDataService;
	
	private static ServiceResponse<VectorDTO> vectorServiceResponse;
	
	private static ServiceResponse<VectorStatistics> statServiceResponse;
	
	
	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@BeforeAll
	static void setUpClass() {
		vectorServiceResponse = buildServiceResponseStub();
		statServiceResponse = buildVectorStatisticsStub();
	}

	@AfterAll
	static void tearDownClass() {
	}
	
	@Test
	public void createNewVectorHappyPath() {
		Mockito.when(vectorDataService.createNewVector(5)).thenReturn(vectorServiceResponse);
		ResponseEntity<ServiceResponse<VectorDTO>> vectorResponse = vectorStatisticController.createNewVector(5);
		ServiceResponse<VectorDTO> vectorDtoServiceResponse = vectorResponse.getBody();
		VectorDTO vectorDto = vectorDtoServiceResponse.getPayload();
		Assertions.assertEquals(1, vectorDto.getVectorId());
		Assertions.assertEquals(5, vectorDto.getVectorElements().size());
		Assertions.assertNull(vectorDtoServiceResponse.getErrorMsgs());
		Assertions.assertEquals(HttpStatus.CREATED,vectorResponse.getStatusCode());
	}
	
	@Test
	public void getVectorStatisticsHappyPath() {
		Mockito.when(vectorDataService.retrieveVectorStats(5)).thenReturn(statServiceResponse);
		ResponseEntity<ServiceResponse<VectorStatistics>> vectorAPIResponse = vectorStatisticController.getVectorStatistics(5);
		ServiceResponse<VectorStatistics> serviceResponse = vectorAPIResponse.getBody();
		VectorStatistics statsPayload = serviceResponse.getPayload();
		List<String> errorMsgs = serviceResponse.getErrorMsgs();
		Assertions.assertEquals(22.5, statsPayload.getMeanValue());
		Assertions.assertEquals(54.6, statsPayload.getStandardDeviation());
		Assertions.assertNull(errorMsgs);
	}

	@Test
	public void createNewVectorErrorScenario(){
		Mockito.when(vectorDataService.createNewVector(5)).thenThrow(new RuntimeException("Error while creating the vector of length 5"));
		ResponseEntity<ServiceResponse<VectorDTO>> vectorAPIResponse = vectorStatisticController.createNewVector(5);
		ServiceResponse<VectorDTO> vectorAPIResponseBody = vectorAPIResponse.getBody();
		VectorDTO vectorPayload = vectorAPIResponseBody.getPayload();
		List<String> errorMsgs = vectorAPIResponseBody.getErrorMsgs();
		Assertions.assertNull(vectorPayload.getVectorId());
		Assertions.assertNull(vectorPayload.getVectorElements());
		Assertions.assertEquals(1, errorMsgs.size());
		Assertions.assertEquals("Error while creating the vector of length 5", errorMsgs.get(0));
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, vectorAPIResponse.getStatusCode());
	}

	@Test
	public void retrieveVectorErrorScenario(){
		Mockito.when(vectorStatisticController.getVectorStatistics(10)).thenThrow(new VectorNotFoundException("Vector Not found with ID: 10"));
		ResponseEntity<ServiceResponse<VectorStatistics>> vectorStatsAPIResponse = vectorStatisticController.getVectorStatistics(10);
		ServiceResponse<VectorStatistics> vectorAPIResponseBody = vectorStatsAPIResponse.getBody();
		VectorStatistics vectorPayload = vectorAPIResponseBody.getPayload();
		List<String> errorMsgs = vectorAPIResponseBody.getErrorMsgs();
		Assertions.assertEquals(0.0,vectorPayload.getMeanValue());
		Assertions.assertEquals(0.0,vectorPayload.getStandardDeviation());
		Assertions.assertEquals(1, errorMsgs.size());
		Assertions.assertEquals("Vector Not found with ID: 10", errorMsgs.get(0));
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, vectorStatsAPIResponse.getStatusCode());
	}
	
	
	private static ServiceResponse<VectorDTO> buildServiceResponseStub(){
		VectorDTO vectorDTO = new VectorDTO();
		vectorDTO.setVectorId(1);
		vectorDTO.setVectorElements(Arrays.asList(3,5,6,7,8));
		return new ServiceResponse<VectorDTO>(vectorDTO, null);
	} 
	
	private static ServiceResponse<VectorStatistics> buildVectorStatisticsStub(){
		VectorStatistics stats = new VectorStatistics();
		stats.setMeanValue(22.5);
		stats.setStandardDeviation(54.6);
		return new ServiceResponse<>(stats,null);
		
	}

}
