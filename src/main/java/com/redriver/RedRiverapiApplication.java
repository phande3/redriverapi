package com.redriver;

import java.time.LocalDate;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.redriver.models.Student;
import com.redriver.repository.StudentRepository;

@SpringBootApplication
public class RedRiverapiApplication implements CommandLineRunner {

	@Resource
	private StudentRepository repository ;
	
	public static void main(String[] args) {
		SpringApplication.run(RedRiverapiApplication.class, args);
	}
	
	@Override
    public void run(String... args) throws Exception {
		 for(int i=0 ; i< 10; i++){
		    	Student std = new Student();
		        std.setFirstName("firstName- " + i);
		        std.setLastName("lastName - "+i);
		        std.setDob(LocalDate.now());
		        std.setGrade("First");
		        std.setGender(Math.random()%2==1?"Male":"Female");
		        repository.save(std);
		    }
    }
	
//	public class PopulateStudentDataRunner implements CommandLineRunner {
//		@Override
//		public void run(String... args) throws Exception {
//			 for(int i=0 ; i< 10000; i++){
//			    	Student std = new Student();
//			        std.setFirstName("firstName- " + i);
//			        std.setLastName("lastName - "+i);
//			        std.setDob(LocalDate.now());
//			        std.setGrade("First");
//			        std.setFirstName("Male");
//			        repository.save(std);
//			    }
//		}
//	}
	//	@InitBinder
//    public void initBinder(WebDataBinder binder) {
//        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
//        binder.registerCustomEditor(Date.class, editor);
//    }
}
