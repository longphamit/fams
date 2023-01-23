package com.fams.core.controllers;

import com.fams.manager.dtos.request.AddEventMemberRequest;
import com.fams.manager.dtos.request.AddEventRequest;
import com.fams.manager.dtos.request.GetEventElementRequest;
import com.fams.manager.dtos.response.GetAccountResponse;
import com.fams.manager.dtos.response.GetEventResponse;
import com.fams.manager.models.FindEventParamModel;

import java.util.List;

public interface EventController {
    GetEventResponse findById(String id);

    List<GetEventResponse> findAll();

    GetEventResponse addMember(String eventId, AddEventMemberRequest addEventMemberRequest);

    List<GetAccountResponse> getMembers(String eventId);

    List<GetEventResponse> find(FindEventParamModel findEventParamModel);

    GetEventResponse add(String accountId, AddEventRequest addEventRequest);

    List<GetEventElementRequest> getEventElement(String eventId);
}
