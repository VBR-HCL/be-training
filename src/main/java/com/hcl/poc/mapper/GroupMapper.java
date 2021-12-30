package com.hcl.poc.mapper;


import com.hcl.poc.api.model.group.GroupCreate;
import com.hcl.poc.entity.Group;
import com.hcl.poc.api.model.group.GroupResponse;

public interface GroupMapper {

    GroupResponse toGroupResponse(Group group);

    Group toGroup(GroupCreate groupCreate);
}
