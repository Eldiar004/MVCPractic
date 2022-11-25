package peaksoft.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import peaksoft.model.Instructor;
import peaksoft.service.InstructorService;

@Controller
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/getInstructors/{courseId}")
    public String getAll(@PathVariable("courseId")Long courseId, Model model){
        model.addAttribute("instructors",instructorService.getAll(courseId));
        return "instructor/instructors";
    }

    @GetMapping("/instructors/{courseId}/saveInstructor")
    public String newInstructor(@PathVariable("courseId")Long id, Model model){
        model.addAttribute("newInstructor",new Instructor());
        model.addAttribute("courseId",id);
        return "instructor/saveInstructor";
    }

    @PostMapping("/{courseId}/saveInstructor")
    public String saveInstructor(@PathVariable("courseId")Long id,@ModelAttribute("newInstructor")Instructor instructor){
        instructorService.saveInstructor(instructor);
        return "redirect:/getInstructors/"+id;
    }

    @GetMapping("/{id}/deleteInstructor/{courseId}")
    public String delete(@PathVariable("id")Long id,@PathVariable("courseId")Long courseId){
        instructorService.deleteInstructorById(id);
        return "redirect:/getInstructors/"+courseId;
    }

    @GetMapping
    public String update(Model model,@PathVariable("id")Long id){
        Instructor instructor = instructorService.getById(id);
        model.addAttribute("instructor" , instructor);
        model.addAttribute("courseId" , instructor.getCourse().getId());
        return "instructor/updateInstructor";
    }
}
