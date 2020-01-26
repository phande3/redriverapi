package com.redriver.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.redriver.models.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>{
	
	@Query("SELECT s FROM rr_student s WHERE s.firstName LIKE %?1% OR s.lastName LIKE %?2%")
	Page<Student> findStudentByFirstNameOrLastName(String firstName, String lastName , Pageable pagable);
}
