package peaksoft.repository.repositoryImpl;
import org.springframework.stereotype.Repository;
import peaksoft.model.Course;
import peaksoft.model.Instructor;
import peaksoft.repository.InstructorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class InstructorRepositoryImpl implements InstructorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Instructor> getAllInstructor() {
        return entityManager.createQuery("select i from Instructor i").getResultList();
    }

    @Override
    public void saveInstructorByCourseId(Long courseId,Instructor instructor) {
        Course course = entityManager.find(Course.class,courseId);
        instructor.setCourse(course);
        entityManager.persist(instructor);
    }

    @Override
    public void deleteInstructorById(Long id) {
        Instructor instructor = entityManager.find(Instructor.class , id);
        entityManager.remove(instructor);
    }

    @Override
    public List<Instructor> getAll(Long courseId) {
        return entityManager.createQuery("select i from Instructor i where i.course.id = :courseId")
                .setParameter("courseId",courseId).getResultList();
    }

    @Override
    public void update(Long id,Instructor instructor) {
        Instructor instructor1 = entityManager.find(Instructor.class, id);
        instructor1.setFirstName(instructor.getFirstName());
        instructor1.setPhoneNumber(instructor.getPhoneNumber());
        instructor1.setEmail(instructor.getEmail());
        instructor1.setSpecialization(instructor.getSpecialization());
        instructor1.setLastName(instructor.getLastName());
        entityManager.merge(instructor1);
    }

    @Override
    public void assignInstructorToCourseById(Long courseId, Long instructorId) {
        Course course = entityManager.find(Course.class,courseId);
        Instructor instructor = entityManager.find(Instructor.class,instructorId);
        instructor.setCourse(course);
        entityManager.merge(instructor);
    }

    @Override
    public Instructor getById(Long id) {
        return entityManager.find(Instructor.class,id);
    }
}
