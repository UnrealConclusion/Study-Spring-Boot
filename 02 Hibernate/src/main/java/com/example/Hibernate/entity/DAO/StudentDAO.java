package com.example.Hibernate.entity.DAO;

import com.example.Hibernate.entity.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student theStudent);
    Student findById(Integer id);
    void updateFirstName(Student theStudent, String newFirstName); // update an entity
    void delete(Integer id); // delete an entity by its primary key
    List<Student> findAll();
    List<Student> findByLastName(String theLastName);
    int deleteAll();
}
