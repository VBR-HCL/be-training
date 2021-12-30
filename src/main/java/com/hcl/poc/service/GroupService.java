package com.hcl.poc.service;

import com.hcl.poc.api.model.group.GroupCreate;
import com.hcl.poc.api.model.group.GroupResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupService {
    Page<GroupResponse> find(Pageable pageable);

    GroupResponse findById(Long id);

    GroupResponse save(GroupCreate groupCreate);
}
