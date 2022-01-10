package com.neosoft.spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neosoft.spring.security.model.Student;

@Repository
public interface ApplicationUserRepository extends JpaRepository<Student, Integer> {

}
