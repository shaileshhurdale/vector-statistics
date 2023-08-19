CREATE TABLE vector
  (
     vector_id     INTEGER NOT NULL auto_increment,
     element_count INTEGER,
     PRIMARY KEY (vector_id)
  );

CREATE TABLE vector_element
  (
     element_id    INTEGER NOT NULL auto_increment,
     element_index INTEGER,
     element_value INTEGER,
     vector_id     INTEGER,
     PRIMARY KEY (element_id)
  ); 
  
ALTER TABLE vector_element
  ADD CONSTRAINT fk_contraint_vector_elements FOREIGN KEY (vector_id) REFERENCES
  vector (vector_id); 

