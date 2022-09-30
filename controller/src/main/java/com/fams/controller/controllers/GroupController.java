package com.fams.controller.controllers;

import com.fams.manager.dtos.response.GetGroupResponse;

import java.util.List;

public interface GroupController {
    List<GetGroupResponse> findByMemberId(String memberId);
}
