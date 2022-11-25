package peaksoft.service.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Instructor;
import peaksoft.repository.InstructorRepository;
import peaksoft.service.InstructorService;
import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public void saveInstructor(Instructor instructor) {
        instructorRepository.saveInstructor(instructor);
    }

    @Override
    public void deleteInstructorById(Long id) {
        instructorRepository.deleteInstructorById(id);
    }

    @Override
    public List<Instructor> getAll(Long courseId) {
        return instructorRepository.getAll(courseId);
    }

    @Override
    public void update(Long id,Instructor instructor) {
        instructorRepository.update(id,instructor);
    }

    @Override
    public void assignInstructorToCourseById(Long courseId, Long instructorId) {
        instructorRepository.assignInstructorToCourseById(courseId, instructorId);
    }

    @Override
    public Instructor getById(Long id) {
        return instructorRepository.getById(id);
    }
}
