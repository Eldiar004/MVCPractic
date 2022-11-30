package peaksoft.repository;
import peaksoft.model.Course;
import java.util.List;
public interface CourseRepository {
    List<Course>getAllCourse();
    void saveCourseByCompanyId(Long id, Course course);

    List<Course> getAllCoursesByCompanyId(Long companyId);

    void deleteById(Long id);

    void update(Long id,Course course);

    Course getById(Long id);
}
