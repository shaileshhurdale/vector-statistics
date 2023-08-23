package com.vector.statistics.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * VectorStatistics is a DTO class which provides statistical data of a
 * perticular vector.
 * 
 * it provides, mean and standard deviation value.
 * 
 * @author Shailesh Hurdale
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Vector Statistics class")
public class VectorStatistics {

	@ApiModelProperty(value = "Arithmetic mean value of vector", example = "22.76")
	private double meanValue;
	@ApiModelProperty(value = "Standard deviation value of vector", example = "16.53")
	private double standardDeviation;

}
