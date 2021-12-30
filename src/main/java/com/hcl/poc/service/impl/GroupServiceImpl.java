package com.hcl.poc.service.impl;

import com.hcl.poc.api.model.exception.NotFoundException;
import com.hcl.poc.api.model.group.GroupCreate;
import com.hcl.poc.api.model.group.GroupResponse;
import com.hcl.poc.entity.Group;
import com.hcl.poc.mapper.GroupMapper;
import com.hcl.poc.repository.GroupRepository;
import com.hcl.poc.service.GroupService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class GroupServiceImpl implements GroupService {

    private final GroupMapper groupMapper;
    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupMapper groupMapper,
                            GroupRepository groupRepository) {
        this.groupMapper = groupMapper;
        this.groupRepository = groupRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GroupResponse> find(Pageable pageable) {
        return groupRepository.findAll(pageable).map(groupMapper::toGroupResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public GroupResponse findById(Long id) {
        return groupMapper.toGroupResponse(groupRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Could not find Group with id: " + id)));
    }

    @Override
    @Transactional
    public GroupResponse save(GroupCreate groupCreate) {
        final Group savedGroup = groupRepository.save(groupMapper.toGroup(groupCreate));
        return groupMapper.toGroupResponse(savedGroup);
    }
}
