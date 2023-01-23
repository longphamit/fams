package com.fams.core.controllers;

import com.fams.core.dtos.request.AddGroupEventElementRequest;
import com.fams.core.dtos.request.AddGroupMemberRequest;
import com.fams.core.dtos.request.AddGroupRequest;
import com.fams.core.dtos.request.GetEventElementRequest;
import com.fams.core.dtos.response.GetAccountResponse;
import com.fams.core.dtos.response.GetGroupResponse;

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
