package peaksoft.repository.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Course;
import peaksoft.model.Lesson;
import peaksoft.repository.LessonRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class LessonRepositoryImpl implements LessonRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveLessonByCourseId(Long id, Lesson lesson) {
        Course course = entityManager.find(Course.class,id);
        lesson.setCourse(course);
        entityManager.persist(lesson);
    }

    @Override
    public void deleteById(Long id) {
        Lesson lesson = entityManager.find(Lesson.class , id);
        entityManager.remove(lesson);
    }

    @Override
    public List<Lesson> getAll(Long courseId) {
        return entityManager.createQuery("select l from Lesson l where l.course.id = :courseId")
                .setParameter("courseId",courseId).getResultList();
    }

    @Override
    public void update(Long id,Lesson lesson) {
        Lesson lesson1 = entityManager.find(Lesson.class,id);
        lesson1.setLessonName(lesson.getLessonName());
        entityManager.merge(lesson1);
    }

    @Override
    public Lesson getById(Long id) {
        return entityManager.find(Lesson.class,id);
    }
}
