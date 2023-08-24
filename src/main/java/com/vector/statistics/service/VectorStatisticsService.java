package com.vector.statistics.service;

import com.vector.statistics.dto.VectorDTO;

/**
 * 
 * VectorStatisticsService used to perform different mathematical calculations
 * on vector like arithmetic mean and standard deviation
 *
 * @author Shailesh Hurdale
 */
public interface VectorStatisticsService {

	/**
	 * calculateVectorMean provides the arithmetic mean of the vector passed as a
	 * parameter. this method return mean as 0.0 if vector element list is null or
	 * empty
	 * 
	 * @param vectorDto - it is a vector parameter of Type VectorDTO whoes
	 *                  arithmetic mean will be calculated
	 * @return returns arithmetic mean of vector parameter
	 */
	public double calculateVectorMean(VectorDTO vectorDto);

	/**
	 * calculateVectorStandardDeviation is method used to calculate standard
	 * deviation of mathematical vector
	 * 
	 * @param vectorDto - it is a parameter of type VectorDto whoes standard
	 *                  daviation to becalculated
	 * @return it returns the standard deviation of vector passed as method
	 *         parameter
	 */
	public double calculateVectorStandardDeviation(VectorDTO vectorDto);

}
