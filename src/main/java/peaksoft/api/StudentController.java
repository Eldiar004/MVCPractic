package peaksoft.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import peaksoft.model.Student;
import peaksoft.model.enums.StudyFormat;
import peaksoft.service.StudentService;

@Controller
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/getAllStudent/{groupId}")
    public String getAllStudent(@PathVariable("groupId")Long groupId,Model model){
        model.addAttribute("allStudent",studentService.getAll(groupId));
        model.addAttribute("groupId",groupId);
        return "student/students";
    }

    @GetMapping("/{groupId}/newStudent")
    public String newStudent(@PathVariable("groupId") Long groupId, Model model){
        model.addAttribute("student", new Student());
        model.addAttribute("studyFormatOnline", StudyFormat.ONLINE);
        model.addAttribute("studyFormatOffline",StudyFormat.OFFLINE);
        model.addAttribute("groupId", groupId);
        return "student/saveStudent";
    }

    @PostMapping("/{groupId}/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student,
                              @PathVariable("groupId") Long groupId){
        studentService.saveStudent(groupId, student);
        return "redirect:/getAllStudent/" + groupId ;
    }

    @GetMapping("/{groupId}/{id}/deleteStudent")
    public String deleteStudent(@PathVariable("id") Long id, @PathVariable("groupId") Long groupId) {
        studentService.deleteById(id);
        return "redirect:/getAllStudent/"+groupId;
    }

    @GetMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable("id") Long id, Model model) {
        Student student = studentService.getById(id);
        model.addAttribute("student", student);
        model.addAttribute("groupId", student.getGroup().getId());
        model.addAttribute("studyFormatOnline", StudyFormat.ONLINE);
        model.addAttribute("studyFormatOffline", StudyFormat.OFFLINE);
        return "/student/updateStudent";
    }

    @PostMapping("/{groupId}/{id}/saveUpdateStudent")
    public String saveUpdateStudent(@PathVariable("groupId") Long groupId,
                                    @PathVariable("id") Long id,
                                    @ModelAttribute("student") Student student){
        studentService.update(id,student);
        return "redirect:/getAllStudent/"+groupId;
    }
}
