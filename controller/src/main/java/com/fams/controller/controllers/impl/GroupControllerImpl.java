package com.fams.controller.controllers.impl;

import com.fams.controller.controllers.GroupController;
import com.fams.controller.utils.AuthenUtil;
import com.fams.controller.utils.MapperUtil;
import com.fams.manager.dtos.request.*;
import com.fams.manager.dtos.response.GetAccountResponse;
import com.fams.manager.dtos.response.GetGroupResponse;
import com.fams.manager.entities.AccountEntity;
import com.fams.manager.entities.EventElementEntity;
import com.fams.manager.entities.GroupEntity;
import com.fams.manager.repositories.AccountManager;
import com.fams.manager.repositories.GroupManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupControllerImpl implements GroupController {
    private final GroupManager groupManager;
    private final AccountManager accountManager;

    @Override
    public List<GetGroupResponse> findByMemberId() {
        String accountId=AuthenUtil.getAccountIdByAuthenContext();
        List<GroupEntity> groupEntities= groupManager.findByMemberId(accountId);
        if(groupEntities==null||groupEntities.size()==0){
            return new ArrayList<>();
        }

        return groupEntities.stream().map(e->
                GetGroupResponse.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .creator(
                                MapperUtil.map(accountManager.findById(e.getAdmin()), GetAccountResponse.class)
                        )
                        .build()
                ).collect(Collectors.toList());
    }
    @Override
    public GetGroupResponse addGroup(String accountId, AddGroupRequest addGroupRequest){
        if(groupManager.countJoinedGroupExistName(accountId,addGroupRequest.getName())>0){
            throw new IllegalArgumentException("group name is existed");
        }
        List<String> members= new ArrayList<>();
        members.add(accountId);
        GroupEntity groupEntity=GroupEntity.builder()
                .name(addGroupRequest.getName())
                .admin(accountId)
                .members(members)
                .build();
        return MapperUtil.map(groupManager.save(groupEntity),GetGroupResponse.class);
    }
    public List<GetAccountResponse> addMembers(String groupId, AddGroupMemberRequest addGroupMemberRequest){
        if(groupManager.addMember(groupId,addGroupMemberRequest.getMemberIds())){
            return findMembers(groupId);
        }
        throw new IllegalArgumentException("Add member fail");
    }
    public List<GetAccountResponse> findMembers(String groupId){
        List<String> members= groupManager.getMembers(groupId);
        return MapperUtil.map(accountManager.findByIds(members),GetAccountResponse.class);
    }
    public long countJoinedGroup(){
        String accountId= AuthenUtil.getAccountIdByAuthenContext();
        return groupManager.countJoinedGroup(accountId);
    }
    public GetGroupResponse findById(String id){
        GroupEntity groupEntity=groupManager.findById(id);
        GetGroupResponse getGroupResponse=MapperUtil.map(groupEntity,GetGroupResponse.class);
        getGroupResponse.setCreator(MapperUtil.map(accountManager.findById(groupEntity.getAdmin()),GetAccountResponse.class));
        return getGroupResponse;
    }
    public GetEventElementRequest addEventElement(String groupId, AddGroupEventElementRequest addEventElementRequest){
        EventElementEntity eventElement= MapperUtil.map(addEventElementRequest,EventElementEntity.class);
        groupManager.addEventElement(groupId,eventElement);
        return null;
    }
}
