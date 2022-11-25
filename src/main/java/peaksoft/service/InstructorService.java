package peaksoft.service;

import peaksoft.model.Instructor;

import java.util.List;

public interface InstructorService {
    void saveInstructor(Instructor instructor);

    void deleteInstructorById(Long id);

    List<Instructor> getAll(Long courseId);

    void update(Long id,Instructor instructor);

    void assignInstructorToCourseById(Long courseId , Long instructorId);

    Instructor getById(Long id);
}
