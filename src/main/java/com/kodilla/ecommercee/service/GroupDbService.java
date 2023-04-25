package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupDbService {

    private final GroupRepository groupRepository;

    public GroupDbService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getAllGroups() {
        List<Group> groups = new ArrayList<>();
        Group group1 = new Group(1L, "Group 1", null);
        Group group2 = new Group(2L, "Group 2", null);
        Group group3 = new Group(3L, "Group 3", null);
        groups.add(group1);
        groups.add(group2);
        groups.add(group3);
        return groups;
    }

    public Group getGroupById(Long groupId) {

        return new Group(groupId, "Dummy group", null);
    }

    public Group createGroup(Group group) {

        return new Group(4L, group.getName(), null);
    }

    public void updateGroup(Long groupId, Group group) {

    }

    public void saveGroup(final Group group) {
        groupRepository.save(group);
    }


    public void deleteGroup(Long groupId) {

    }
}
