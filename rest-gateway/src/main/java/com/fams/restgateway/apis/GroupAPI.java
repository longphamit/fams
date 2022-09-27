package com.fams.restgateway.apis;

import com.fams.controller.controllers.GroupController;
import com.fams.manager.dtos.response.ObjectWrapperResponse;
import com.fams.manager.dtos.response.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/group")
public class GroupAPI {
    @Autowired
    GroupController groupController;
    @GetMapping("member/{memberId}")
    public WrapperResponse getGroupByMemberId(@PathVariable("memberId") String memberId){
       return ObjectWrapperResponse.builder()
               .data( groupController.findByMemberId(memberId))
               .message("Get groups success")
               .build();
    }
}
