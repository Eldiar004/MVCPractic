package peaksoft.service.serviceImpl;
import org.springframework.stereotype.Service;
import peaksoft.model.Course;
import peaksoft.repository.CourseRepository;
import peaksoft.service.CourseService;
import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void saveCourseByCompanyId(Long id, Course course) {
        courseRepository.saveCourseByCompanyId(id,course);
    }

    @Override
    public List<Course> getAllCourses(Long companyId) {
        return courseRepository.getAllCourses(companyId);
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public void update(Long id,Course course) {
        courseRepository.update(id,course);
    }

    @Override
    public Course getById(Long id) {
        return courseRepository.getById(id);
    }
}
