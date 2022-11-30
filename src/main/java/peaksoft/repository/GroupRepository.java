package peaksoft.repository;

import peaksoft.model.Group;

import java.util.List;

public interface GroupRepository {
    void saveGroupByCourseId(Long courseId, Group group);

    List<Group> getAllGroupByCourseId(Long courseId);

    public List<Group> getAllGroup();

    void deleteGroupById(Long id);

    void update(Long id,Group group);

    Group getById(Long id);

    public void assignGroup(Long courseId, Long groupId);
}
