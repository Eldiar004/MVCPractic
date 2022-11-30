package peaksoft.service;

import peaksoft.model.Instructor;

import java.util.List;

public interface InstructorService {
    List<Instructor> getAllInstructor();
    void saveInstructorByCourseId(Long courseId,Instructor instructor);

    void deleteInstructorById(Long id);

    List<Instructor> getAll(Long courseId);

    void update(Long id,Instructor instructor);

    void assignInstructorToCourseById(Long courseId , Long instructorId);

    Instructor getById(Long id);
}
