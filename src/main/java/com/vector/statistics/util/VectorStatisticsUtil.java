package com.vector.statistics.util;

import java.util.ArrayList;
import java.util.List;

import com.vector.statistics.dto.VectorDTO;
import com.vector.statistics.entity.Vector;

/**
 * VectorStatisticsUtil is a utility class used to perform some utility
 * operation that can be used across multiple classes.
 * 
 * All the operations in this class are static
 * 
 * 
 * @author Shailesh Hurdale
 *
 */

public class VectorStatisticsUtil {

	public static VectorDTO vectorDtoMapper(Vector vectorEntity) {
		VectorDTO vectorDto = new VectorDTO();
		vectorDto.setVectorId(vectorEntity.getVectorId());
		List<Integer> vectorDtoElements = new ArrayList<>();
		vectorEntity.getVectorElements().stream()
				.forEach(vectorElement -> vectorDtoElements.add(vectorElement.getElementValue()));
		vectorDto.setVectorElements(vectorDtoElements);
		return vectorDto;
	}

	public static double roundToTwoDecimalPlaces(double value) {
		return Math.round(value * 100.0) / 100.0;
	}

}
