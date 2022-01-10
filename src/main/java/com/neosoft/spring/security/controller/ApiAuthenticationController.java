package com.neosoft.spring.security.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neosoft.spring.security.model.Student;
import com.neosoft.spring.security.repository.ApplicationUserRepository;

@Controller
@RequestMapping("managenment/api/v1/students")
public class ApiAuthenticationController {

	@Autowired
	private ApplicationUserRepository applicationUserRepository;

	@GetMapping("/")
	@PreAuthorize("hasAuthority('get:all')")
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> students = applicationUserRepository.findAll();
		return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
	public ResponseEntity<?> registerStudent(@RequestBody Student student) {
		Student s = applicationUserRepository.save(student);
		return new ResponseEntity<>(s, HttpStatus.CREATED);
	}

//	@GetMapping("/form")
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
//	public String getRegistrationForm(Model model) {
//		model.addAttribute("student", new Student());
//		return "student_registration";
//	}

	@GetMapping(path = "/{StudentId}")
	@PreAuthorize("hasAuthority('get:student:by:id')")
	public ResponseEntity<?> getStudentById(@PathVariable("StudentId") Integer StudentId) {
		System.out.println(StudentId);
		Optional<Student> st = applicationUserRepository.findById(StudentId);
		if (st.isPresent()) {
			Student s = st.get();
			return new ResponseEntity<>(s, HttpStatus.FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
