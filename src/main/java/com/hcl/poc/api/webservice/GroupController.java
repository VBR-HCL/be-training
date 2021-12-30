package com.hcl.poc.api.webservice;

import com.hcl.poc.api.model.GroupResponse;
import com.hcl.poc.service.GroupService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.hcl.poc.config.PocConfigProperties.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("")
    public ResponseEntity<Page<GroupResponse>> find(Pageable pageable) {
        return ResponseEntity.ok(groupService.find(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(groupService.findById(id));
    }
}
