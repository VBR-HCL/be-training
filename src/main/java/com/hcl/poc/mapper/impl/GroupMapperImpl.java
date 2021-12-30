package com.hcl.poc.mapper.impl;

import com.hcl.poc.api.model.group.GroupCreate;
import com.hcl.poc.entity.Group;
import com.hcl.poc.api.model.group.GroupResponse;
import com.hcl.poc.mapper.GroupMapper;
import org.springframework.stereotype.Component;

@Component
public class GroupMapperImpl implements GroupMapper {
    @Override
    public GroupResponse toGroupResponse(Group group) {
        if (group == null) {
            return null;
        }

        final GroupResponse groupResponse = new GroupResponse();
        groupResponse.setId(group.getId());
        groupResponse.setName(group.getName());

        return groupResponse;
    }

    @Override
    public Group toGroup(GroupCreate groupCreate) {
        if (groupCreate == null) {
            return null;
        }

        final Group group = new Group();
        group.setId(groupCreate.getId());
        group.setName(groupCreate.getName());

        return group;
    }
}
