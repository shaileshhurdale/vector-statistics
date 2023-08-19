package com.vector.statistics.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VectorDTO {
	
	private Integer vectorId;
	private List<Integer> vectorElements;

}
