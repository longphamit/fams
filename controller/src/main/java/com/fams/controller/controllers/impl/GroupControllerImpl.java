package com.fams.controller.controllers.impl;

import com.fams.controller.controllers.GroupController;
import com.fams.manager.dtos.request.GetGroupRequest;
import com.fams.manager.entities.GroupEntity;
import com.fams.manager.repositories.GroupManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupControllerImpl implements GroupController {
    @Autowired
    private GroupManager groupManager;

    @Override
    public List<GetGroupRequest> findByMemberId(String memberId) {
        List<GroupEntity> groupEntities= groupManager.findByMemberId(memberId);
        if(groupEntities==null||groupEntities.size()==0){
            return new ArrayList<>();
        }
        return groupEntities.stream().map(e->new GetGroupRequest(e.getId(),e.getName())).collect(Collectors.toList());
    }
}
