package peaksoft.repository;

import peaksoft.model.Company;
import peaksoft.model.Lesson;

import java.util.List;

public interface LessonRepository {
    void saveLessonByCourseId(Long id, Lesson lesson);

    void deleteById(Long id);

    List<Lesson> getAll(Long courseId);

    void update(Long id,Lesson lesson);

    Lesson getById(Long id);
}
