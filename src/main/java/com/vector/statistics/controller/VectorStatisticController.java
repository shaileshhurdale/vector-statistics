package com.vector.statistics.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vector.statistics.dto.ServiceResponse;
import com.vector.statistics.dto.VectorDTO;
import com.vector.statistics.dto.VectorStatistics;
import com.vector.statistics.sevice.VectorDataService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class VectorStatisticController {

	@Autowired
	private VectorDataService vectorDataService;

	@GetMapping("/")
	public String greetingMessage() {
		return "Vector Statistics App";
	}

	@PostMapping("/{length}")
	public ResponseEntity<ServiceResponse<VectorDTO>> createNewVector(@PathVariable("length") Integer length) {
		try {
			return ResponseEntity.ok(vectorDataService.createNewVector(length));
		} catch (Exception e) {
			log.error("vector-statistics-error: Error occurred while creating new vector ErrorMsg: {}", e.getMessage());
			return new ResponseEntity<ServiceResponse<VectorDTO>>(
					new ServiceResponse<>(new VectorDTO(), Arrays.asList(e.getMessage())),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{vectorId}")
	public ResponseEntity<ServiceResponse<VectorStatistics>> getVectorStatistics(
			@PathVariable("vectorId") Integer vectorId) {
		try {
			return ResponseEntity.ok(vectorDataService.retrieveVectorStats(vectorId));
		} catch (Exception e) {
			log.error("vector-statistics-error: Error occurred while retrieving vector with Id: {} ErrorMsg: {}", vectorId, e.getMessage());
			return new ResponseEntity<ServiceResponse<VectorStatistics>>(
					new ServiceResponse<>(new VectorStatistics(), Arrays.asList(e.getMessage())),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
