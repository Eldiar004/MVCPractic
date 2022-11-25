package peaksoft.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import peaksoft.model.Task;
import peaksoft.service.TaskService;

@Controller
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/getTask/{id}")
    public String getAllLesson(@PathVariable("id")Long lessonId, Model model){
        model.addAttribute("allTask",taskService.getAll(lessonId));
        model.addAttribute("lessonId",lessonId);
        return "task/tasks";
    }

    @GetMapping("/tasks/{id}/addTask")
    public String addTask(@PathVariable Long id, Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("lessonId", id);
        return "/task/saveTask";
    }

    @PostMapping("/{id}/saveTask")
    public String saveTask(@ModelAttribute("task") Task task,
                            @PathVariable Long id){
        taskService.saveTaskByLessonId(id, task);
        return "redirect:/getTask/"+id;
    }
    @GetMapping("/updateTask/{id}")
    private String updateTask(@PathVariable("id")Long id, Model model) {
        Task task = taskService.getById(id);
        model.addAttribute("task",task);
        model.addAttribute("lessonId",task.getLesson().getId());
        return "/task/updateTask";
    }

    @PostMapping("/{lessonId}/{id}/saveUpdateTask")
    private String saveUpdateTask(@PathVariable("lessonId")Long lessonId,
                                  @PathVariable("id")Long id,
                                  @ModelAttribute("task") Task task) {
        taskService.update(id,task);
        return "redirect:/getTask/ " + lessonId;
    }

    @GetMapping("/{lessonId}/{id}/deleteTask")
    private String deleteTask(@PathVariable("lessonId") Long lessonId, @PathVariable("id") Long id) {
        taskService.deleteById(id);
        return "redirect:/getTask/" + lessonId;
    }

}
