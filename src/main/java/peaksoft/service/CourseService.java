package peaksoft.service;

import peaksoft.model.Course;

import java.util.List;

public interface CourseService {
    void saveCourseByCompanyId(Long id, Course course);

    List<Course> getAllCourses(Long companyId);

    void deleteById(Long id);

    void update(Long id,Course course);

    Course getById(Long id);
}
