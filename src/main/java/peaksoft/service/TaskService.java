package peaksoft.service;

import peaksoft.model.Student;
import peaksoft.model.Task;

import java.util.List;

public interface TaskService {
    void saveTaskByLessonId(Long id, Task task);

    void deleteById(Long id);

    List<Task> getAll(Long lessonId);

    void update(Long id,Task task);

    Task getById(Long id);
}
