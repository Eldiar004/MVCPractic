package peaksoft.service.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Student;
import peaksoft.repository.StudentRepository;
import peaksoft.service.StudentService;
import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void saveStudent(Long id,Student student) {
        studentRepository.saveStudent(id,student);
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAll(Long groupId) {
        return studentRepository.getAll(groupId);
    }

    @Override
    public void update(Long id,Student student) {
        studentRepository.update(id,student);
    }

    @Override
    public void assignStudentToGroupById(Long studentId, Long groupId) {
        studentRepository.assignStudentToGroupById(studentId,groupId);
    }

    @Override
    public Student getById(Long id) {
        return studentRepository.getById(id);
    }
}
