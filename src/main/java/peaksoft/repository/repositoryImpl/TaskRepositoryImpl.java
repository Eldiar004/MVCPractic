package peaksoft.repository.repositoryImpl;
import org.springframework.stereotype.Repository;
import peaksoft.model.Lesson;
import peaksoft.model.Task;
import peaksoft.repository.TaskRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TaskRepositoryImpl implements TaskRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveTaskByLessonId(Long id, Task task) {
        Lesson lesson = entityManager.find(Lesson.class , id);
        lesson.addTask(task);
        task.setLesson(lesson);
        entityManager.persist(task);
    }

    @Override
    public void deleteById(Long id) {
        Task task = entityManager.find(Task.class,id);
        entityManager.remove(task);
    }

    @Override
    public List<Task> getAll(Long lessonId) {
        return entityManager.createQuery("select t from Task t where t.lesson.id = :lessonId")
                .setParameter("lessonId",lessonId).getResultList();
    }

    @Override
    public void update(Long id,Task task) {
        Task task1 = entityManager.find(Task.class,id);
        task1.setTaskName(task.getTaskName());
        task1.setDeadline(task.getDeadline());
        task1.setTaskTest(task.getTaskTest());
        entityManager.merge(task1);
    }

    @Override
    public Task getById(Long id) {
        return entityManager.find(Task.class,id);
    }
}
