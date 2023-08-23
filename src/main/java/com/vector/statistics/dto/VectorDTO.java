package com.vector.statistics.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * VectorDTO is a DTO class which provides vector id and it's associated
 * elements.
 * 
 * @author Shailesh Hurdale
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Vector DTO class")
public class VectorDTO {

	@ApiModelProperty(value = "Vector Id", example = "1")
	private Integer vectorId;
	@ApiModelProperty(value = "List of vector elements", example = "[1,2,3,4,5]")
	private List<Integer> vectorElements;

}
