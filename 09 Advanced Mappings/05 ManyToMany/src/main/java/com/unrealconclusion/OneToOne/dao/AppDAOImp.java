package com.unrealconclusion.OneToOne.dao;

import java.lang.reflect.Type;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.unrealconclusion.OneToOne.entity.Course;
import com.unrealconclusion.OneToOne.entity.Instructor;
import com.unrealconclusion.OneToOne.entity.InstructorDetail;
import com.unrealconclusion.OneToOne.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class AppDAOImp implements AppDAO {

    private EntityManager entityManager;

    public AppDAOImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        this.entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return this.entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor = this.entityManager.find(Instructor.class, id); // retrieve the instructor 
        
        // get courses 
        List<Course> courses = instructor.getCourses();

        // break association 
        for (Course course: courses) {
            course.setInstructor(null);
        }

        this.entityManager.remove(instructor); // delete the instructor 
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return this.entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail = this.entityManager.find(InstructorDetail.class, id);

        // breaks the bi-directional link by removing the reference instructor has on instructor details by setting it to null
        instructorDetail.getInstructor().setInstructorDetail(null);

        this.entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {

        // create the query
        TypedQuery<Course> query = this.entityManager.createQuery("FROM Course WHERE instructor.id = :data", Course.class);
        query.setParameter("data", id);

        // execute query 
        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {

        // create the query 
        TypedQuery<Instructor> query = this.entityManager.createQuery(
            "SELECT i FROM Instructor i " + 
            "JOIN FETCH i.courses " +
            "JOIN FETCH i.instructorDetail " +
            "WHERE i.id = :data", Instructor.class
        );
        query.setParameter("data", id);

        // execute query
        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        this.entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        this.entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
        return this.entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course course = this.entityManager.find(Course.class, id); // find the course
        this.entityManager.remove(course); // delete course
    }

    @Override
    @Transactional
    public void save(Course course) {
        this.entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewById(int id) {

        // create query
        TypedQuery<Course> query = this.entityManager.createQuery(
            "SELECT c FROM Course c " + 
            "JOIN FETCH c.reviews " + 
            "WHERE c.id = :data", Course.class);
        query.setParameter("data", id);

        // execute query
        Course course = query.getSingleResult();
        return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int id) {

        // create query
        TypedQuery<Course> query = this.entityManager.createQuery(
            "SELECT c FROM Course c " + 
            "JOIN FETCH c.students " + 
            "WHERE c.id = :data", Course.class);
        query.setParameter("data", id);

        // execute query
        Course course = query.getSingleResult();
        return course;
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int id) {
        
        // create query
        TypedQuery<Student> query = this.entityManager.createQuery(
            "SELECT s FROM Student s " + 
            "JOIN FETCH s.courses " + 
            "WHERE s.id = :data", Student.class);
        query.setParameter("data", id);

        // execute query
        Student student = query.getSingleResult();
        return student;
    }
    
    @Override
    @Transactional
    public void update(Student student) {
        this.entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {
        // retrieve the student 
        Student student = this.entityManager.find(Student.class, id);
        
        if (student != null) {
            // break all association 
            List<Course> courses = student.getCourses();
            for (Course course : courses) {
                course.getStudents().remove(student);
            }
            this.entityManager.remove(student); // delete student
        }
    }
}
