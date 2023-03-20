package com.fams.gateway.rest.apis;

import com.fams.core.controllers.EventController;
import com.fams.core.enums.RolesEnum;
import com.fams.core.models.AccountDetailAuthenModel;
import com.fams.core.utils.AuthenUtil;

import com.fams.core.dtos.request.AddEventMemberRequest;
import com.fams.core.dtos.request.AddEventRequest;
import com.fams.core.dtos.response.GetAccountResponse;
import com.fams.core.dtos.response.GetEventResponse;
import com.fams.core.dtos.response.ObjectWrapperResponse;
import com.fams.core.dtos.response.WrapperResponse;

import com.fams.core.models.FindEventParamModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public WrapperResponse get(
            @RequestParam(value = "groupId", required = false) String groupId,
            @RequestParam(value = "isMember", required = false) boolean isMember,
            @RequestParam(value = "isCreator", required = false) boolean isCreator) {

        String accountId = AuthenUtil.getAccountIdByAuthenContext();

        boolean isAdmin = true;
        if (!AuthenUtil.getAccountByAuthenContext().getRoles().contains(RolesEnum.ADMIN.name())) {
            isAdmin = false;
        }
        FindEventParamModel findEventParamModel = FindEventParamModel.builder()
                .accountId(accountId)
                .isAdmin(isAdmin)
                .isMember(isMember)
                .isCreator(isCreator)
                .groupId(groupId)
                .build();
        return ObjectWrapperResponse.builder().data(
                eventController.find(findEventParamModel)
        ).build();
    }

    @PostMapping("{eventId}/members")
    public WrapperResponse addMemberToEvent(
            @PathVariable("eventId") String eventId,
            @RequestBody AddEventMemberRequest addEventMemberRequest) {
        return ObjectWrapperResponse.builder().data(eventController.addMember(eventId, addEventMemberRequest)).build();
    }

    @GetMapping("{eventId}/members")
    public WrapperResponse getMembers(@PathVariable("eventId") String eventId) {
        List<GetAccountResponse> members = eventController.getMembers(eventId);
        return ObjectWrapperResponse.builder().data(members).build();
    }

    @PostMapping
    public WrapperResponse add(Authentication authentication, @RequestBody AddEventRequest addEventRequest) {
        AccountDetailAuthenModel accountDetailAuthenModel = (AccountDetailAuthenModel) authentication.getPrincipal();
        String accountId = accountDetailAuthenModel.getAccountEntity().getId();
        GetEventResponse getEventResponse = eventController.add(accountId, addEventRequest);
        return ObjectWrapperResponse.builder().data(getEventResponse).build();
    }

    @GetMapping("{eventId}/event-elements")
    public WrapperResponse getEventElements(@PathVariable("eventId") String eventId) {
        return ObjectWrapperResponse.builder().data(eventController.getEventElement(eventId)).build();
    }
}
