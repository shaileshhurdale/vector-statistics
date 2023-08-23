package com.vector.statistics.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * VectorElement is a entity class associated with vector_element table
 * elementId is acting as a primary key for this entity
 * 
 * vector element and vector shares many to one relationship with each other
 * 
 * @author Shailesh Hurdale
 *
 */

@Entity
@Table(name = "vector_element")
@Data
public class VectorElement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "element_id")
	private Integer elementId;

	@ManyToOne
	@JoinColumn(name = "vector_id")
	private Vector vector;

	@Column(name = "element_index")
	private int elementIndex;

	@Column(name = "element_value")
	private int elementValue;

}
