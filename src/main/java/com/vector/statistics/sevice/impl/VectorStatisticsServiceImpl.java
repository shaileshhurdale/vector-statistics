package com.vector.statistics.sevice.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vector.statistics.dto.VectorDTO;
import com.vector.statistics.service.VectorStatisticsService;
import com.vector.statistics.util.VectorStatisticsUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * VectorStatisticsServiceImpl class implements VectorStatisticsService
 * interface. this class is used to perform different mathematical calculations
 * on vector like arithmetic mean and standard deviation
 *
 * @author Shailesh Hurdale
 */

@Service
@Slf4j
public class VectorStatisticsServiceImpl implements VectorStatisticsService {

	/**
	 * calculateVectorMean provides the arithmetic mean of the vector passed as a
	 * parameter. this method return mean as 0.0 if vector element list is null or
	 * empty
	 * 
	 * @param vectorDto - it is a vector parameter of Type VectorDTO whoes
	 *                  arithmetic mean will be calculated
	 * @return returns arithmetic mean of vector parameter
	 */
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

	/**
	 * calculateVectorStandardDeviation is method used to calculate standard
	 * deviation of mathematical vector
	 * 
	 * @param vectorDto - it is a parameter of type VectorDto whoes standard
	 *                  daviation to becalculated
	 * @return it returns the standard deviation of vector passed as method
	 *         parameter
	 */

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
