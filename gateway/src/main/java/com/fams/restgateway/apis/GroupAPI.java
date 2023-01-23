package com.fams.restgateway.apis;

import com.fams.core.controllers.GroupController;
import com.fams.core.models.AccountDetailAuthenModel;
import com.fams.core.dtos.request.AddGroupEventElementRequest;
import com.fams.core.dtos.request.AddGroupMemberRequest;
import com.fams.core.dtos.request.AddGroupRequest;
import com.fams.core.dtos.response.ObjectWrapperResponse;
import com.fams.core.dtos.response.WrapperResponse;
import com.fams.core.entities.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/groups")
public class GroupAPI {
    @Autowired
    GroupController groupController;

    @GetMapping
    public WrapperResponse getGroups() {
        return ObjectWrapperResponse.builder()
                .data(groupController.findByMemberId())
                .message("Get groups success")
                .build();
    }

    @PostMapping
    public WrapperResponse addGroup(Authentication authentication, @RequestBody AddGroupRequest addGroupRequest) {
        AccountDetailAuthenModel accountDetailAuthenModel = (AccountDetailAuthenModel) authentication.getPrincipal();
        AccountEntity account = accountDetailAuthenModel.getAccountEntity();
        return ObjectWrapperResponse.builder()
                .data(groupController.addGroup(account.getId(), addGroupRequest))
                .message("Get groups success")
                .build();
    }

    @GetMapping("{id}")
    public WrapperResponse getGroup(@PathVariable("id") String id) {
        return ObjectWrapperResponse.builder()
                .data(groupController.findById(id))
                .message("Get group success")
                .build();
    }

    @GetMapping("{id}/members")
    public WrapperResponse getMembers(@PathVariable("id") String id) {
        return ObjectWrapperResponse.builder()
                .data(groupController.findMembers(id))
                .message("Get members success")
                .build();
    }

    @PostMapping("{id}/members")
    public WrapperResponse addMembers(@PathVariable("id") String id, @RequestBody AddGroupMemberRequest addGroupMemberRequest) {
        return ObjectWrapperResponse.builder()
                .data(groupController.addMembers(id,addGroupMemberRequest))
                .message("Get members success")
                .build();
    }
//    @DeleteMapping("{id}/members/{memberId}")
//    public WrapperResponse removeMember(@PathVariable("id") String id,@PathVariable("memberId") String memberId){
//
//    }

    @GetMapping("{id}/event-elements")
    public WrapperResponse getEventElements() {
        return WrapperResponse.builder().build();
    }

    @PostMapping("{id}/event-elements")
    public WrapperResponse addEventElement(
            @PathVariable("id") String id,
            @RequestBody AddGroupEventElementRequest addGroupEventElementRequest) {

        return WrapperResponse.builder().data(groupController.addEventElement(id,addGroupEventElementRequest))
                .message("Add event element success")
                .build();
    }


    @GetMapping("/count")
    public WrapperResponse countGroup() {
        return ObjectWrapperResponse.builder()
                .data(groupController.countJoinedGroup())
                .message("Count groups success")
                .build();
    }

}
