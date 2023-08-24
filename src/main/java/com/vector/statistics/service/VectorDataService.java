package com.vector.statistics.service;

import com.vector.statistics.dto.ServiceResponse;
import com.vector.statistics.dto.VectorDTO;
import com.vector.statistics.dto.VectorStatistics;

/**
 * 
 * VectorDataService interface provides two methods to create new vector and to
 * get statistics of a perticular method
 * 
 * @author Shailesh Hurdale
 */
public interface VectorDataService {

	/**
	 * createNewVector is a service method responsible for creating new vector of
	 * perticular length it store the newly created vector in database and responds
	 * with vector ID and elements in VectorDTO object
	 * 
	 * @param length it is integer type of paramter used to specify length of new
	 *               vector to be crreated
	 * @return - it return ServiceResponse<VectorDTO> which contains vector id and
	 *         it's element
	 */
	public ServiceResponse<VectorDTO> createNewVector(Integer length);

	/**
	 * retrieveVectorStats is amethod takes vector id as a input parameter and
	 * returns the statistics result associated with vector.if vector is not found
	 * in database with provided id it throws VectorNotFoundException.
	 * 
	 * @param vectorId - vectorId is a integer type of parameter to be passed as a
	 *                 id of vector to be retrieved
	 * @return this method returns ServiceResponse<VectorStatistics> which contains
	 *         arithmetic mean and standard deviation
	 */
	public ServiceResponse<VectorStatistics> retrieveVectorStats(Integer vectorId);

}
