package peaksoft.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Lesson;
import peaksoft.service.LessonService;

@Controller
@RequestMapping
public class LessonController {

    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }
    @GetMapping("/getLessons/{id}")
    public String getAllLesson(@PathVariable("id")Long courseId, Model model){
        model.addAttribute("allLesson",lessonService.getAll(courseId));
        model.addAttribute("courseId",courseId);
        return "lesson/lessons";
    }
    @GetMapping("/saveLesson/{courseId}/newLesson")
    public String newLesson(@PathVariable("courseId")Long id , Model model){
        model.addAttribute("newLesson",new Lesson());
        model.addAttribute("courseId",id);
        return "lesson/saveLesson";
    }
    @PostMapping("/{id}/saveLesson")
    public String saveLesson(@PathVariable("id")Long courseId, @ModelAttribute("newLesson")Lesson lesson){
        lessonService.saveLessonByCourseId(courseId,lesson);
        return "redirect:/getLessons/{id}";
    }

    @GetMapping("/{courseId}/deleteLesson/{id}")
    public String deleteLesson(@PathVariable("courseId")Long courseId,@PathVariable("id")Long id){
        lessonService.deleteById(id);
        return "redirect:/getLessons/"+courseId;
    }
    @GetMapping("/{id}/updateLessons")
    public String update(@PathVariable("id")Long id,Model model){
        Lesson lesson = lessonService.getById(id);
        model.addAttribute("lesson",lesson);
        model.addAttribute("courseId",lesson.getCourse().getId());
        return "lesson/updateLesson";
    }
    @PostMapping("/{courseId}/updateLesson/{id}")
    public String updateLesson(@PathVariable("courseId")Long courseId,@ModelAttribute("lesson")Lesson lesson,
                               @PathVariable("id")Long id){
        lessonService.update(id,lesson);
        return "redirect:/getLessons/"+courseId;
    }
}
