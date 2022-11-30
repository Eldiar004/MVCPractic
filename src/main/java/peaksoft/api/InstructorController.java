package peaksoft.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import peaksoft.model.Course;
import peaksoft.model.Instructor;
import peaksoft.service.CourseService;
import peaksoft.service.InstructorService;
@Controller
public class InstructorController {

    private final InstructorService instructorService;
    private final CourseService courseService;

    @Autowired
    public InstructorController(InstructorService instructorService, CourseService courseService) {
        this.instructorService = instructorService;
        this.courseService = courseService;
    }

    @GetMapping("/getAllIns")
    public String getAll(Model model){
        instructorService.getAllInstructor();
        model.addAttribute("instructors",instructorService.getAllInstructor());
        return "instructor/instructors";
    }
    @GetMapping("/getInstructors/{courseId}")
    public String getAllInstructor(@PathVariable("courseId")Long courseId, Model model,
                         @ModelAttribute("course")Course course) {
        model.addAttribute("courses", courseService.getAllCourse());
        model.addAttribute("instructors", instructorService.getAll(courseId));
        return "instructor/instructors";
    }

    @GetMapping("/instructors/{courseId}/saveInstructor")
    public String newInstructor(@PathVariable("courseId")Long id, Model model){
        model.addAttribute("newInstructor",new Instructor());
        model.addAttribute("courseId",id);
        return "instructor/saveInstructor";
    }

    @PostMapping("/{courseId}/saveInstructor")
    public String saveInstructor(Model model,@PathVariable("courseId")Long id,
                                 @ModelAttribute("newInstructor")Instructor instructor){
        instructorService.saveInstructorByCourseId(id,instructor);
        return "redirect:/getInstructors/"+id;
    }

    @GetMapping("/{id}/deleteInstructor/{courseId}")
    public String delete(@PathVariable("id")Long id,@PathVariable("courseId")Long courseId){
        instructorService.deleteInstructorById(id);
        return "redirect:/getInstructors/"+courseId;
    }

    @GetMapping("/updateInstructor/{id}")
    public String updateInstructor(@PathVariable("id") Long id, Model model) {
        Instructor instructor = instructorService.getById(id);
        model.addAttribute("instructor", instructor);
        model.addAttribute("courseId", instructor.getCourse().getId());
        return "instructor/updateInstructor";
    }

    @PostMapping("/{courseId}/{id}/updateInstructor")
    public String saveUpdateInstructor(@PathVariable("courseId") Long courseId,
                                       @PathVariable("id") Long id,
                                       @ModelAttribute("instructor") Instructor instructor){
        instructorService.update(id,instructor);
        return "redirect:/getInstructors/" + courseId;
    }
}
