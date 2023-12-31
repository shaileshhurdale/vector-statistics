package com.vector.statistics.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * Vector is an entity class associated with vector database table in vector
 * management database vector id is a auto-increamented primary key present in
 * the vector class with list of vector elements
 * 
 * @author Shailesh Hurdale
 *
 */
@Entity
@Table(name = "Vector")
@Data
public class Vector {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vector_id")
	private Integer vectorId;

	@Column(name = "element_count")
	private int elementCount;

	@OneToMany(mappedBy = "vector", cascade = CascadeType.ALL)
	private List<VectorElement> vectorElements;

}
