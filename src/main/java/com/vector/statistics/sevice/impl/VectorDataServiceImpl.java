package com.vector.statistics.sevice.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vector.statistics.dto.ServiceResponse;
import com.vector.statistics.dto.VectorDTO;
import com.vector.statistics.dto.VectorStatistics;
import com.vector.statistics.entity.Vector;
import com.vector.statistics.entity.VectorElement;
import com.vector.statistics.exception.VectorNotFoundException;
import com.vector.statistics.repository.VectorRepository;
import com.vector.statistics.service.VectorDataService;
import com.vector.statistics.service.VectorStatisticsService;
import com.vector.statistics.util.VectorStatisticsUtil;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class VectorDataServiceImpl implements VectorDataService {

	@Autowired
	private VectorRepository vectorRepository;

	@Autowired
	private VectorStatisticsService vectorStatisticsService;

	private static final Random RANDOM = new Random();

	@Override
	public ServiceResponse<VectorDTO> createNewVector(Integer length) {
		Vector vector = new Vector();
		vector.setElementCount(length);
		List<VectorElement> vectorElements = new ArrayList<>();
		IntStream.iterate(1, i -> i + 1).limit(length).forEach(i -> {
			VectorElement vectorElement = new VectorElement();
			vectorElement.setElementIndex(i);
			vectorElement.setElementValue(RANDOM.nextInt(100) + 1);
			vectorElement.setVector(vector);
			vectorElements.add(vectorElement);
		});
		vector.setVectorElements(vectorElements);
		Vector vectorEntity = vectorRepository.save(vector);
		log.info("vector-statistics: New Vector has been saved successfully with Id:{} of elements:{}",
				vectorEntity.getVectorId(), length);
		VectorDTO vectorDto = VectorStatisticsUtil.vectorDtoMapper(vectorEntity);
		return new ServiceResponse<>(vectorDto, null);

	}

	@Override
	public ServiceResponse<VectorStatistics> retrieveVectorStats(Integer vectorId) {
		Vector vectorEntity = this.vectorRepository.findById(vectorId).orElseThrow(() -> {
			return new VectorNotFoundException("Vector not found with id: " + vectorId);
		});
		VectorDTO vectorDto = VectorStatisticsUtil.vectorDtoMapper(vectorEntity);
		double meanValue = vectorStatisticsService.calculateVectorMean(vectorDto);
		double standardDeviation = vectorStatisticsService.calculateVectorStandardDeviation(vectorDto);
		VectorStatistics vectorStatistics = new VectorStatistics(meanValue, standardDeviation);
		return new ServiceResponse<VectorStatistics>(vectorStatistics, null);
	}

}
