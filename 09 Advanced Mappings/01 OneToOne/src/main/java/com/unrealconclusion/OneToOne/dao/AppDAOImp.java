package com.unrealconclusion.OneToOne.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.unrealconclusion.OneToOne.entity.Instructor;

import jakarta.persistence.EntityManager;

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
        this.entityManager.remove(instructor); // delete the instructor 
    }
}
