package peaksoft.service.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Lesson;
import peaksoft.repository.LessonRepository;
import peaksoft.service.LessonService;
import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public void saveLessonByCourseId(Long id, Lesson lesson) {
        lessonRepository.saveLessonByCourseId(id,lesson);
    }

    @Override
    public void deleteById(Long id) {
        lessonRepository.deleteById(id);
    }

    @Override
    public List<Lesson> getAll(Long courseId) {
        return lessonRepository.getAll(courseId);
    }

    @Override
    public void update(Long id,Lesson lesson) {
        lessonRepository.update(id,lesson);
    }

    @Override
    public Lesson getById(Long id) {
        return lessonRepository.getById(id);
    }
}
