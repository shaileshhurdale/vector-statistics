package com.vector.statistics.sevice.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vector.statistics.dto.VectorDTO;
import com.vector.statistics.service.VectorStatisticsService;
import com.vector.statistics.util.VectorStatisticsUtil;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class VectorStatisticsServiceImpl implements VectorStatisticsService {

	@Override
	public double calculateVectorMean(VectorDTO vectorDto) {
		List<Integer> elements = vectorDto.getVectorElements();
		if (null != elements && !elements.isEmpty()) {
			double sum = 0;
			for (double element : elements) {
				sum += element;
			}
			return VectorStatisticsUtil.roundToTwoDecimalPlaces(sum / elements.size());
		}
		log.error("vector-statistics-error: Empty list of vectors elements for vector with id: {}",
				vectorDto.getVectorId());
		return 0;
	}

	@Override
	public double calculateVectorStandardDeviation(VectorDTO vectorDto) {
		List<Integer> elements = vectorDto.getVectorElements();
		if (!elements.isEmpty()) {
			double mean = calculateVectorMean(vectorDto);
			double sumSquareDiff = 0;
			for (double element : elements) {
				double diff = element - mean;
				sumSquareDiff += diff * diff;
			}
			double meanSquareDiff = sumSquareDiff / elements.size();
			return VectorStatisticsUtil.roundToTwoDecimalPlaces(Math.sqrt(meanSquareDiff));
		}

		log.error("vector-statistics-error: Empty list of vectors elements for vector with id: {}",
				vectorDto.getVectorId());
		return 0;
	}

}
