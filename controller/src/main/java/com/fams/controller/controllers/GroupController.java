package com.fams.controller.controllers;

import com.fams.manager.dtos.request.AddGroupMemberRequest;
import com.fams.manager.dtos.request.AddGroupRequest;
import com.fams.manager.dtos.response.GetAccountResponse;
import com.fams.manager.dtos.response.GetGroupResponse;
import com.fams.manager.entities.AccountEntity;

import java.util.List;

public interface GroupController {
    List<GetGroupResponse> findByMemberId(String memberId);
    GetGroupResponse addGroup(String memberId, AddGroupRequest addGroupRequest);
    long countJoinedGroup(String memberId);

    GetGroupResponse findById(String id);
    List<GetAccountResponse> findMembers(String groupId);

    List<GetAccountResponse> addMembers(String groupId, AddGroupMemberRequest addGroupMemberRequest);
}
