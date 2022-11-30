package peaksoft.service;

import peaksoft.model.Student;

import java.util.List;

public interface StudentService {
    public List<Student> getAllStudent();
    void saveStudent(Long id,Student student);

    void deleteById(Long id);

    List<Student> getAll(Long groupId);

    void update(Long id,Student student);

    void assignStudentToGroupById(Long studentId , Long groupId);

    Student getById(Long id);
}
