package com.fams.controller.controllers;

import com.fams.manager.dtos.request.AddEventMemberRequest;
import com.fams.manager.dtos.request.AddEventRequest;
import com.fams.manager.dtos.response.GetEventResponse;
import com.fams.manager.models.FindEventParamModel;

import java.util.List;

public interface EventController {
    GetEventResponse findById(String id);
    List<GetEventResponse> findAll();

    GetEventResponse addMember(String eventId, AddEventMemberRequest addEventMemberRequest);
    List<GetEventResponse> find(FindEventParamModel findEventParamModel);

    GetEventResponse add(AddEventRequest addEventRequest);
}
