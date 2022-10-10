package com.fams.controller.controllers;

import com.fams.manager.dtos.request.AddGroupEventElementRequest;
import com.fams.manager.dtos.request.AddGroupMemberRequest;
import com.fams.manager.dtos.request.AddGroupRequest;
import com.fams.manager.dtos.request.GetEventElementRequest;
import com.fams.manager.dtos.response.GetAccountResponse;
import com.fams.manager.dtos.response.GetGroupResponse;
import com.fams.manager.entities.AccountEntity;

import java.util.List;

public interface GroupController {
    List<GetGroupResponse> findByMemberId();
    GetGroupResponse addGroup(String memberId, AddGroupRequest addGroupRequest);
    long countJoinedGroup();

    GetGroupResponse findById(String id);
    List<GetAccountResponse> findMembers(String groupId);

    List<GetAccountResponse> addMembers(String groupId, AddGroupMemberRequest addGroupMemberRequest);

    GetEventElementRequest addEventElement(String groupId, AddGroupEventElementRequest addEventElementRequest);
}
