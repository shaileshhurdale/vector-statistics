-- File: vector_management_schema.sql
-- Author: Shailesh Hurdale
-- Created: 2023-08-23
-- Description: SQL script to create vector management schema



-- Table: vector
-- Description: Stores information about the vector with it's id and number of elements
-- Columns: vector_id, element_count
-- Relationships: Links to vector_element by vector_id

CREATE TABLE vector
  (
     vector_id     INTEGER NOT NULL auto_increment,
     element_count INTEGER,
     PRIMARY KEY (vector_id)
  );
  
  
-- Table: vector_element
-- Description: Stores information about the vector elements along with it's position in respective vector
-- Columns: element_id, element_index, element_value, vector_id 
-- Relationships: Links to vector table by foreign key vector_id

CREATE TABLE vector_element
  (
     element_id    INTEGER NOT NULL auto_increment,
     element_index INTEGER,
     element_value INTEGER,
     vector_id     INTEGER,
     PRIMARY KEY (element_id)
  ); 
  
 
-- Constraint: foreign key constraints
-- Description:   
 
ALTER TABLE vector_element
  ADD CONSTRAINT fk_contraint_vector_elements FOREIGN KEY (vector_id) REFERENCES
  vector (vector_id); 

