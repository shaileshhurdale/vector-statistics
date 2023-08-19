package com.vector.statistics.sevice;

import com.vector.statistics.dto.VectorDTO;

public interface VectorStatisticsService {
	
	public double calculateVectorMean(VectorDTO vectorDto);
	
	public double calculateVectorStandardDeviation(VectorDTO vectorDto);

}
