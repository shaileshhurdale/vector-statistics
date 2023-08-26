package com.vector.statistics.dto;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VectorDTOTest {

	private static VectorDTO v1;
	private static VectorDTO v2;

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@BeforeAll
	static void setUpClass() {
		 v1 = buildVectorDtoStub(1, Arrays.asList(4,5));
		 v2 = buildVectorDtoStub(1, Arrays.asList(4,5));
	}

	@AfterAll
	static void tearDownClass() {
		v1=v2=null;
	}

	@Test
	public void vectorDtoEqualityTest(){
		Assertions.assertEquals(v1, v2);
		Assertions.assertEquals(v1.hashCode(), v2.hashCode());
	}
	
	@Test
	public void vectorDtoNotEqualTest(){
		VectorDTO v3 = new VectorDTO(2,Arrays.asList(4,5));
		VectorDTO v4 = new VectorDTO(1,Arrays.asList(5,6));
		Assertions.assertNotEquals(v1, v3);
		Assertions.assertNotEquals(v1, v4);
		Assertions.assertNotEquals(v1.hashCode(), v3.hashCode());
		Assertions.assertNotEquals(v1.hashCode(), v4.hashCode());
	}
	
	

	@Test
	public void vectorDtoTestTostring(){
		String vectorDtoStr = "VectorDTO(vectorId=1, vectorElements=[4, 5])";
		Assertions.assertEquals(vectorDtoStr, v1.toString());
	}
	
	@Test
	public void vectorDtoAllArgConstructorStuv() {
		VectorDTO v = new VectorDTO(2,Arrays.asList(7,8));
		Assertions.assertNotNull(v);
	}


	private static VectorDTO buildVectorDtoStub(Integer vectorId, List<Integer> elements){
		VectorDTO v = new VectorDTO();
		v.setVectorId(vectorId);
		v.setVectorElements(elements);
		return v;
	}

}
