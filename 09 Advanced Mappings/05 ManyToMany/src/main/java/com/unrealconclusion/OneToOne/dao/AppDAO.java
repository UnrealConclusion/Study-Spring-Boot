package com.unrealconclusion.OneToOne.dao;

import java.util.List;

import com.unrealconclusion.OneToOne.entity.Course;
import com.unrealconclusion.OneToOne.entity.Instructor;
import com.unrealconclusion.OneToOne.entity.InstructorDetail;
import com.unrealconclusion.OneToOne.entity.Student;

public interface AppDAO {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);
    List<Course> findCoursesByInstructorId(int id);
    Instructor findInstructorByIdJoinFetch(int id);
    void update(Instructor instructor);
    void update(Course course);
    Course findCourseById(int id);
    void deleteCourseById(int id);
    void save(Course course);
    Course findCourseAndReviewById(int id);
    Course findCourseAndStudentsByCourseId(int id);
    Student findStudentAndCoursesByStudentId(int id);
    void update(Student student);
    void deleteStudentById(int id);
}
