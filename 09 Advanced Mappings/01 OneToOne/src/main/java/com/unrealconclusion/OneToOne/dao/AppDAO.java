package com.unrealconclusion.OneToOne.dao;

import com.unrealconclusion.OneToOne.entity.Instructor;

public interface AppDAO {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
}
