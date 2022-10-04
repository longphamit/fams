package com.fams.restgateway.apis;

import com.fams.controller.controllers.EventController;
import com.fams.controller.models.AccountDetailAuthenModel;
import com.fams.manager.constants.enums.RolesEnum;
import com.fams.manager.dtos.request.AddEventMemberRequest;
import com.fams.manager.dtos.request.AddEventRequest;
import com.fams.manager.dtos.response.ObjectWrapperResponse;
import com.fams.manager.dtos.response.WrapperResponse;
import com.fams.manager.models.FindEventParamModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/events")
public class EventAPI {
    @Autowired
    private EventController eventController;

    @GetMapping("{id}")
    public WrapperResponse getEvent(@PathVariable("id") String id) {
        return ObjectWrapperResponse
                .builder()
                .data(eventController.findById(id))
                .build();
    }

    @GetMapping
    public WrapperResponse getAll(
            @RequestParam(value = "isMember",required = false) boolean isMember,
            @RequestParam(value = "isCreator",required = false) boolean isCreator,
            Authentication authentication) {

        AccountDetailAuthenModel accountDetailAuthenModel = (AccountDetailAuthenModel) authentication.getPrincipal();
        String accountId = accountDetailAuthenModel.getAccountEntity().getId();
        boolean isAdmin=true;
        if(!accountDetailAuthenModel.getAccountEntity().getRoles().contains(RolesEnum.ADMIN.name())){
            isAdmin=false;
        }
        FindEventParamModel findEventParamModel= FindEventParamModel.builder()
                .accountId(accountId)
                .isAdmin(isAdmin)
                .isMember(isMember)
                .isCreator(isCreator)
                .build();
        return ObjectWrapperResponse.builder().data(
                eventController.find(findEventParamModel)
        ).build();
    }

    @PostMapping("{eventId}/members/")
    public WrapperResponse addMemberToEvent(
            @PathVariable("eventId") String eventId,
            @RequestBody AddEventMemberRequest addEventMemberRequest) {
        return ObjectWrapperResponse.builder().data(eventController.addMember(eventId, addEventMemberRequest)).build();
    }


}
