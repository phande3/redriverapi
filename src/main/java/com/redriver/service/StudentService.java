package com.redriver.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redriver.models.Student;
import com.redriver.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepo;
	
	public Collection<Student> getAllStudents(){
		List<Student> sList = new ArrayList<Student>();
		sList = (List<Student>) studentRepo.findAll();
		return sList;
	}
	public Student saveStudent(Student student){
		return studentRepo.save(student);
	}
	public void deleteStudent(Long id){
		studentRepo.deleteById(id);
	}
	public Optional<Student> getStudent(Long id){
		return studentRepo.findById(id);
	}
}
