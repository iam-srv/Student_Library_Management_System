package com.example.Student_Library_Management_System.Repository;

import com.example.Student_Library_Management_System.Models.Student;
import com.example.Student_Library_Management_System.StudentLibraryManagementSystemApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

//    @Query
//    Student findByEmail(String email);
//
//    @Query
//    List<Student> findByCountry(String country);

    Student findByEmail(String email);
}
