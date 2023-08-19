package com.vector.statistics.util;

import java.util.ArrayList;
import java.util.List;

import com.vector.statistics.dto.VectorDTO;
import com.vector.statistics.entity.Vector;

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

}
