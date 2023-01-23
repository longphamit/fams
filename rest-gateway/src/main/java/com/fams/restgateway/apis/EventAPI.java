package com.fams.restgateway.apis;

import com.fams.controller.controllers.EventController;
import com.fams.controller.models.AccountDetailAuthenModel;
import com.fams.controller.utils.AuthenUtil;
import com.fams.manager.constants.enums.RolesEnum;
import com.fams.manager.dtos.request.AddEventMemberRequest;
import com.fams.manager.dtos.request.AddEventRequest;
import com.fams.manager.dtos.request.GetAccountRequest;
import com.fams.manager.dtos.response.GetAccountResponse;
import com.fams.manager.dtos.response.GetEventResponse;
import com.fams.manager.dtos.response.ObjectWrapperResponse;
import com.fams.manager.dtos.response.WrapperResponse;
import com.fams.manager.entities.AccountEntity;
import com.fams.manager.models.FindEventParamModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
