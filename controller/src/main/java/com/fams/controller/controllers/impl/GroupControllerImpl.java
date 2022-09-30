package com.fams.controller.controllers.impl;

import com.fams.controller.controllers.GroupController;
import com.fams.manager.dtos.response.GetGroupResponse;
import com.fams.manager.entities.GroupEntity;
import com.fams.manager.repositories.GroupManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupControllerImpl implements GroupController {
    private final GroupManager groupManager;

    @Override
    public List<GetGroupResponse> findByMemberId(String memberId) {
        List<GroupEntity> groupEntities= groupManager.findByMemberId(memberId);
        if(groupEntities==null||groupEntities.size()==0){
            return new ArrayList<>();
        }
        return groupEntities.stream().map(e->new GetGroupResponse(e.getId(),e.getName())).collect(Collectors.toList());
    }
}
