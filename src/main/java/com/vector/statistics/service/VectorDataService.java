package com.vector.statistics.service;

import com.vector.statistics.dto.ServiceResponse;
import com.vector.statistics.dto.VectorDTO;
import com.vector.statistics.dto.VectorStatistics;

public interface VectorDataService {
	
	public ServiceResponse<VectorDTO> createNewVector(Integer length);
	
	public ServiceResponse<VectorStatistics> retrieveVectorStats(Integer vectorId);

}
