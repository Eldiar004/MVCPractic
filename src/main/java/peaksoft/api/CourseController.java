package peaksoft.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Course;
import peaksoft.model.Instructor;
import peaksoft.service.CompanyService;
import peaksoft.service.CourseService;
import peaksoft.service.InstructorService;
import peaksoft.service.StudentService;
@Controller
public class CourseController {
    private final CourseService courseService;
    private final CompanyService companyService;
    private final InstructorService instructorService;

    private final StudentService studentService;

    @Autowired
    public CourseController(CourseService courseService, CompanyService companyService, InstructorService instructorService, StudentService studentService) {
        this.courseService = courseService;
        this.companyService = companyService;
        this.instructorService = instructorService;
        this.studentService = studentService;
    }

    @GetMapping("/courses/{companyId}")
    public String getAllCourses(@PathVariable("companyId") Long id, Model model) {
        model.addAttribute("courses",
                courseService.getAllCourses(id));
        return "course/courses";
    }

    @GetMapping("/courses/{companyId}/addCourse")
    public String addCourse(@PathVariable("companyId") Long id, Model model) {
        model.addAttribute("companyId", id);
        model.addAttribute("course", new Course());
        return "course/saveCourse";
    }

    @PostMapping("/{companyId}/saveCourse")
    public String saveCourse(@PathVariable("companyId") Long id,
                             @ModelAttribute("course") Course course) {
        courseService.saveCourseByCompanyId(id, course);
        return "redirect:/courses/{companyId}";
    }

    @GetMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") Long id, Model model) {
        Course course = courseService.getById(id);
        model.addAttribute("course", course);
        model.addAttribute("companyId", course.getCompany().getId());
        return "course/updateCourse";
    }

    @PostMapping("/{companyId}/{id}/saveUpdateCourse")
    public String saveUpdateCourse(@PathVariable("companyId") Long companyId,
                                   @PathVariable("id") Long id,
                                   @ModelAttribute("course") Course course) {
        courseService.update(id,course);
        return "redirect:/courses/"+companyId;
    }

    @GetMapping("/{companyId}/{id}/deleteCourse")
    public String deleteCourse(@PathVariable("id") Long id, @PathVariable("companyId") Long companyId) {
        courseService.deleteById(id);
        return "redirect:/courses/"+companyId;
    }
}