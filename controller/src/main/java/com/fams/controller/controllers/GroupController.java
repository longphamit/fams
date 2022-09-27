package com.fams.controller.controllers;

import com.fams.manager.dtos.request.GetGroupRequest;

import java.util.List;

public interface GroupController {
    List<GetGroupRequest> findByMemberId(String memberId);
}
