package peaksoft.service;

import peaksoft.model.Group;

import java.util.List;

public interface GroupService {
    void saveGroupByCourseId(Long courseId , Group group);

    List<Group> getAllGroupByCourseId(Long courseId);

    Group deleteGroupById(Long id);

    void update(Long id,Group group);

    Group getById(Long id);

    public void assignGroup(Long courseId, Long groupId);

    public List<Group> getAllGroup();
}
