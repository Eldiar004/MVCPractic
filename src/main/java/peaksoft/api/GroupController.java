package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Group;
import peaksoft.service.GroupService;
@Controller
@RequestMapping
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/groups/{id}")
    public String getAllGroups(@PathVariable("id") Long id, Model model) {
        model.addAttribute("groups", groupService.getAllGroup(id));
        model.addAttribute("courseId", id);
        return "group/groups";
    }

    @GetMapping("/groups/{courseId}/addGroupByCourseId")
    public String addGroupByCourseId(@PathVariable("courseId") Long id, Model model) {
        model.addAttribute("group", new Group());
        model.addAttribute("courseId", id);
        return "/group/saveGroup";
    }

    @PostMapping("/{courseId}/saveGroup")
    public String saveGroupByCourseId(@ModelAttribute("group") Group group,
                                      @PathVariable("courseId") Long id) {
        groupService.saveGroupByCourseId(id,group);
        return "redirect:/groups/"+id ;
    }

    @GetMapping("/{id}/deleteGroup/{courseId}")
    public String deleteGroup(@PathVariable("id")Long id,@PathVariable("courseId")Long courseId){
        groupService.deleteGroupById(id);
        return "redirect:/groups/"+courseId;
    }

    @GetMapping("/{courseId}/update/{id}")
    public String updateGroup(@PathVariable("id") Long id, Model model,
                              @PathVariable("courseId")Long courseId) {
        Group group = groupService.getById(id);
        model.addAttribute("group", group);
        model.addAttribute("courseId", courseId);
        return "group/updateGroup";
    }

    @PostMapping("/{courseId}/saveUpdateCourse/{id}")
    public String saveUpdateCourse(@PathVariable("id")Long id,@PathVariable("courseId") Long courseId,
                                   @ModelAttribute("group") Group group) {
        groupService.update(id,group);
        return "redirect:/groups/"+courseId;
    }
}

