package com.vector.statistics.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * ServiceResponse is DTO class designed to provide generic API response to
 * consumers. this class contains generic payload object for respective service
 * response and errorMsgs to provide service errors if any.
 * 
 * @author Shailesh Hurdale
 *
 * @param <T>
 */

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "API Service Response")
public class ServiceResponse<T> {
	@ApiModelProperty(value = "Response Payload")
	private T payload;
	@ApiModelProperty(value = "List of error messages", example = "[service errors]")
	private List<String> errorMsgs;
}
