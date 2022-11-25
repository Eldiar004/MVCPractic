package peaksoft.repository.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.repository.CourseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CourseRepositoryImpl implements CourseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveCourseByCompanyId(Long id, Course course) {
        Company company = entityManager.find(Company.class , id);
        course.setCompany(company);
        company.addCourse(course);
        entityManager.persist(course);
    }

    @Override
    public List<Course> getAllCourses(Long companyId) {
        return entityManager.createQuery("select c from Course c  where c.company.id = :companyId" , Course.class)
                .setParameter("companyId" , companyId).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Course course = entityManager.find(Course.class , id);
        entityManager.remove(course);
    }

    @Override
    public void update(Long id,Course course){
        Course course1 = entityManager.find(Course.class,id);
        course1.setCourseName(course.getCourseName());
        course1.setDuration(course.getDuration());
        course1.setDescription(course.getDescription());
        entityManager.merge(course1);
    }

    @Override
    public Course getById(Long id) {
        return entityManager.find(Course.class,id);
    }
}
