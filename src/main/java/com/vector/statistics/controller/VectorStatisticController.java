package com.vector.statistics.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vector.statistics.dto.ServiceResponse;
import com.vector.statistics.dto.VectorDTO;
import com.vector.statistics.dto.VectorStatistics;
import com.vector.statistics.service.VectorDataService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * VectorStatisticController is a rest controller which exposes two endpoints 1.
 * createNewVector - it creates new vector of provided length 2.
 * getVectorStatistics - it provides statistical information about vector
 * 
 * @author Shailesh Hurdale
 *
 */
@RestController
@Slf4j
@RequestMapping("/vector")
@Api(tags = "Vector Statistics Controller")
public class VectorStatisticController {

	@Autowired
	private VectorDataService vectorDataService;

	/**
	 * createNewVector is a handler method responsible for creating new vector of
	 * perticular length
	 * 
	 * @param length - it is a integer parameter which specifies the length of
	 *               vactor to be created.
	 * @return ResponseEntity<ServiceResponse<VectorDTO>> - it returns the
	 *         responseentity of ServiceResponse<VectorDTO> for successful call
	 *         method returns 200 status code. for errors it returns 500 status
	 *         code.
	 */

	@PostMapping("create/{length}")
	@ApiOperation("Create new vector of perticular length")
	@ApiResponses({ @ApiResponse(code = 201, message = "new vector created successfully!"),
		@ApiResponse(code = 500, message = "Error While Creating New Vector") })
	public ResponseEntity<ServiceResponse<VectorDTO>> createNewVector(
			@ApiParam(value = "Length of Vector To Be Created", example = "4", required = true) @PathVariable("length") Integer length) {
		try {
			return new ResponseEntity<>(vectorDataService.createNewVector(length),HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("vector-statistics-error: Error occurred while creating new vector ErrorMsg: {}", e.getMessage());
			return new ResponseEntity<ServiceResponse<VectorDTO>>(
					new ServiceResponse<>(new VectorDTO(), Arrays.asList(e.getMessage())),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * getVectorStatistics is a handler method for retrieving the mean and standard
	 * deviation for a given vector associated with vectorId parameter.
	 * 
	 * if vector id is not found, it returns 500 status code with appropriate error
	 * message
	 * 
	 * @param vectorId - it is vector id of vector whoes mean and standard deviation
	 *                 will be provided
	 * @return ResponseEntity<ServiceResponse<VectorStatistics>> - it returns the
	 *         vector statistics
	 * 
	 */
	@GetMapping("stats/{vectorId}")
	@ApiOperation("Retrive statistical data of vector associated with a vectorId")
	@ApiResponses({ @ApiResponse(code = 200, message = "Vector Statistics Retrieval Successful"),
			@ApiResponse(code = 500, message = "Error While Retrieving Vector Statistics") })
	public ResponseEntity<ServiceResponse<VectorStatistics>> getVectorStatistics(
			@ApiParam(value = "Vector ID", example = "1", required = true) @PathVariable("vectorId") Integer vectorId) {
		try {
			return ResponseEntity.ok(vectorDataService.retrieveVectorStats(vectorId));
		} catch (Exception e) {
			log.error("vector-statistics-error: Error occurred while retrieving vector with Id: {} ErrorMsg: {}",
					vectorId, e.getMessage());
			return new ResponseEntity<ServiceResponse<VectorStatistics>>(
					new ServiceResponse<>(new VectorStatistics(), Arrays.asList(e.getMessage())),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
