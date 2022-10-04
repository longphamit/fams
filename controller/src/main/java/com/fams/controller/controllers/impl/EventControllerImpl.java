package com.fams.controller.controllers.impl;

import com.fams.controller.controllers.EventController;
import com.fams.controller.utils.MapperUtil;
import com.fams.manager.dtos.request.AddEventMemberRequest;
import com.fams.manager.dtos.request.AddEventRequest;
import com.fams.manager.dtos.response.GetEventResponse;
import com.fams.manager.entities.AccountEntity;
import com.fams.manager.entities.EventEntity;
import com.fams.manager.entities.GroupEntity;
import com.fams.manager.models.FindEventParamModel;
import com.fams.manager.repositories.AccountManager;
import com.fams.manager.repositories.EventManager;
import com.fams.manager.repositories.GroupManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventControllerImpl implements EventController {

    private final EventManager eventManager;
    private final AccountManager accountManager;
    private final GroupManager groupManager;


    public GetEventResponse findById(String id) {
        EventEntity eventEntity = eventManager.findById(id);
        return mapEventResponseByEntity(eventEntity);
    }

    public List<GetEventResponse> findAll() {
        List<EventEntity> eventEntity = eventManager.findAll();
        return MapperUtil.map(eventEntity, GetEventResponse.class);
    }

    public GetEventResponse addMember(String eventId, AddEventMemberRequest addEventMemberRequest) {
        eventManager.addMemberToEvent(eventId, addEventMemberRequest.getMemberId());
        return MapperUtil.map(findById(eventId), GetEventResponse.class);
    }

    public List<GetEventResponse> find(FindEventParamModel findEventParamModel) {
        return eventManager.find(findEventParamModel).stream().map(
                this::mapEventResponseByEntity
        ).collect(Collectors.toList());
    }
    public GetEventResponse add(AddEventRequest addEventRequest){
        EventEntity eventEntity= EventEntity.builder().build();
        eventManager.save(eventEntity);
        return null;
    }
    private GetEventResponse mapEventResponseByEntity(EventEntity event){
        GetEventResponse eventResponse = MapperUtil.map(event, GetEventResponse.class);
        eventResponse.setMembers(accountManager.findByIds(event.getMembers()));
        eventResponse.setCreator(accountManager.findById(event.getCreator()));
        eventResponse.setGroup(groupManager.findById(event.getGroupId()));
        return eventResponse;
    }
}
