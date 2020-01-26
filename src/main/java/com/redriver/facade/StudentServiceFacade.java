package com.redriver.facade;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redriver.exception.StudentNotFoundException;
import com.redriver.models.Student;
import com.redriver.repository.StudentRepository;

@RestController
@RequestMapping(path ="/rest/v1/students")
public class StudentServiceFacade {

	@Autowired
	private StudentRepository studentRepo;
	
	@GetMapping(path ="/")
	public List<Student> getAllStudent(){
		List<Student> students = (List<Student>) studentRepo.findAll();
		return students;
	} 
	@GetMapping(path="/paginated")
	public Page<Student> getStudentByName(@RequestParam Optional<String> searchName , 
			@RequestParam Optional<Integer> pageNumber , @RequestParam Optional<Integer> number
			,@RequestParam Optional<String> sortBy){
		return studentRepo.findStudentByFirstNameOrLastName(searchName.orElse("_") , searchName.orElse("_") ,PageRequest.of(pageNumber.orElse(0),
				number.orElse(20) ,Sort.Direction.ASC , sortBy.orElse("id")));
	}
	@GetMapping(path = "/{id}")
	public Optional<Student> getStudent(@PathVariable(name = "id" ) Long id) {
		Optional<Student> student =  studentRepo.findById(id);
		if(!student.isPresent())
			throw new StudentNotFoundException("Student with ID " +  id + " Does not exist");
		return student;
	}
	@PostMapping(path = "/")
	public void saveStudent(@RequestBody Student student) {
		studentRepo.save(student);
	}
	@DeleteMapping(path = "/{id}")
	public void deleteStudent(@PathVariable(name = "id" ) Long id) {
		studentRepo.deleteById(id);
	}
} 
