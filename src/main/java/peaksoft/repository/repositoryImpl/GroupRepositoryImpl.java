package peaksoft.repository.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.repository.GroupRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class GroupRepositoryImpl implements GroupRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveGroupByCourseId(Long courseId, Group group) {
        Course course = entityManager.find(Course.class, courseId);
        course.addGroup(group);
        group.addCourse(course);
        entityManager.merge(course);
//        entityManager.merge(group);
    }
    @Override
    public List<Group> getAllGroup(Long courseId) {
            List<Group> groups = entityManager.find(Course.class, courseId).getGroups();
            return groups;
    }

    @Override
    public void deleteGroupById(Long id) {
        Group group = entityManager.find(Group.class , id);
        entityManager.remove(group);
    }

    @Override
    public void update(Long id,Group group) {
        Group group1 = entityManager.find(Group.class,id);
        group1.setGroupName(group.getGroupName());
        group1.setImage(group.getImage());
        group1.setDateOfStart(group.getDateOfStart());
        entityManager.merge(group1);
    }
    @Override
    public Group getById(Long id) {
        return entityManager.find(Group.class,id);
    }
}
