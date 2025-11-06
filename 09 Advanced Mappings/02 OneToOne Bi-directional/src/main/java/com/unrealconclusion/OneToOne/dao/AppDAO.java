package com.unrealconclusion.OneToOne.dao;

import com.unrealconclusion.OneToOne.entity.Instructor;
import com.unrealconclusion.OneToOne.entity.InstructorDetail;

public interface AppDAO {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);
}
