package com.neosoft.spring.security.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {
	/*-first name
	-last name
	-mobile number
	-email address
	-project	*/

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private int student_id;

	@Size(min = 3, max = 50, message = "firstName size in between 3 and 50")
	public String firstName;

	@Size(min = 3, max = 50, message = "lastName size in between 3 and 50")
	private String lastName;

	@NotNull(message = "mobileNumber can not be null")
	private int mobileNumber;

	@Email
	@Size(min = 3, max = 50, message = "email size in between 3 and 50")
	private String email;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "student_project_record", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "projectId"))
	private List<Project> projects = new ArrayList<>();

}
