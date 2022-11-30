package peaksoft.service.serviceImpl;
import org.springframework.stereotype.Service;
import peaksoft.model.Group;
import peaksoft.repository.GroupRepository;
import peaksoft.service.GroupService;
import java.util.List;
@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public void saveGroupByCourseId(Long courseId,Group group) {
        groupRepository.saveGroupByCourseId(courseId,group);
    }

    @Override
    public List<Group> getAllGroupByCourseId(Long courseId) {
        return groupRepository.getAllGroupByCourseId(courseId);
    }

    @Override
    public Group deleteGroupById(Long id) {
        groupRepository.deleteGroupById(id);
        return null;
    }
    public void assignGroup(Long courseId, Long groupId){
        groupRepository.assignGroup(courseId,groupId);
    }
    @Override
    public void update(Long id,Group group) {
        groupRepository.update(id,group);
    }

    @Override
    public Group getById(Long id) {
        return groupRepository.getById(id);
    }

    @Override
    public List<Group> getAllGroup(){
        return groupRepository.getAllGroup();
    }
}
