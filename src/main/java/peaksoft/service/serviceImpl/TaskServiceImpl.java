package peaksoft.service.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Task;
import peaksoft.repository.TaskRepository;
import peaksoft.service.TaskService;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void saveTaskByLessonId(Long id, Task task) {
        taskRepository.saveTaskByLessonId(id,task);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getAll(Long lessonId) {
        return taskRepository.getAll(lessonId);
    }

    @Override
    public void update(Long id,Task task) {
        taskRepository.update(id,task);
    }

    @Override
    public Task getById(Long id) {
        return taskRepository.getById(id);
    }
}
