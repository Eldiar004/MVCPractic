package peaksoft.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.model.Instructor;
import peaksoft.service.*;

@Controller
public class CourseController {
    private final CourseService courseService;
    private final CompanyService companyService;
    private final InstructorService instructorService;
    private final StudentService studentService;

    private final GroupService groupService;

    @Autowired
    public CourseController(CourseService courseService, CompanyService companyService, InstructorService instructorService, StudentService studentService, GroupService groupService) {
        this.courseService = courseService;
        this.companyService = companyService;
        this.instructorService = instructorService;
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping("/courses/{companyId}")
    public String getAllCourses(@PathVariable("companyId") Long id, Model model,
                                @ModelAttribute("group")Group group,
                                @ModelAttribute("instructor")Instructor instructor) {
        model.addAttribute("instructors",instructorService.getAllInstructor());
        model.addAttribute("groups", groupService.getAllGroup());
        model.addAttribute("courses", courseService.getAllCoursesByCompanyId(id));
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
    @PostMapping("/{courseId}/assignGroup")
    private String assignGroup(@PathVariable("courseId") Long courseId,
                               @ModelAttribute("group") Group group) {
        System.out.println(group);
        groupService.assignGroup(courseId, group.getId());
        return "redirect:/groups/" + courseId;
    }
    @PostMapping("/{courseId}/assignInstructor")
    private String assignInstructor(@PathVariable("courseId") Long courseId,
                                    @ModelAttribute("instructor") Instructor instructor) {
        System.out.println(instructor);
        instructorService.assignInstructorToCourseById(courseId, instructor.getId());
        return "redirect:/getInstructors/"+courseId;
    }

}