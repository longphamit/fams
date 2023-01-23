package com.fams.core.controllers.impl;

import com.fams.core.controllers.EventController;
import com.fams.core.utils.AuthenUtil;
import com.fams.core.utils.MapperUtil;
import com.fams.core.dtos.request.AddEventMemberRequest;
import com.fams.core.dtos.request.AddEventRequest;
import com.fams.core.dtos.request.GetEventElementRequest;
import com.fams.core.dtos.response.GetAccountResponse;
import com.fams.core.dtos.response.GetEventResponse;
import com.fams.core.entities.AccountEntity;
import com.fams.core.entities.EventElementEntity;
import com.fams.core.entities.EventEntity;
import com.fams.core.models.FindEventParamModel;
import com.fams.core.repositories.AccountManager;
import com.fams.core.repositories.EventManager;
import com.fams.core.repositories.GroupManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventControllerImpl implements EventController {

    private final EventManager eventManager;
    private final AccountManager accountManager;
    private final GroupManager groupManager;


    public GetEventResponse findById(String id) {
        AccountEntity account = AuthenUtil.getAccountByAuthenContext();
        EventEntity eventEntity = eventManager.findById(id);
        return mapEventResponseByEntity(account.getId(), eventEntity);
    }

    public List<GetEventResponse> findAll() {
        List<EventEntity> eventEntity = eventManager.findAll();
        return MapperUtil.map(eventEntity, GetEventResponse.class);
    }

    public GetEventResponse addMember(String eventId, AddEventMemberRequest addEventMemberRequest) {
        if (eventManager.addMemberToEvent(eventId, addEventMemberRequest.getMemberIds())) {
            return MapperUtil.map(findById(eventId), GetEventResponse.class);
        }
        throw new IllegalArgumentException("Add member fail");
    }

    public List<GetAccountResponse> getMembers(String eventId) {
        EventEntity eventEntity = eventManager.findById(eventId);
        if (eventEntity == null) {
            throw new IllegalArgumentException("Event not existed");
        }
        return MapperUtil.map(accountManager.findByIds(eventEntity.getMembers()), GetAccountResponse.class);
    }

    public List<GetEventResponse> find(FindEventParamModel findEventParamModel) {
        AccountEntity account = AuthenUtil.getAccountByAuthenContext();
        return eventManager.find(findEventParamModel).stream().map(e -> mapEventResponseByEntity(account.getId(), e)).collect(Collectors.toList());
    }

    public List<GetEventElementRequest> getEventElement(String eventId) {
        EventEntity eventEntity = eventManager.findById(eventId);
        List<String> elements = eventEntity.getElements();
        List<EventElementEntity> eventElementEntities = groupManager.getEventElements(eventEntity.getGroupId(), elements);
        return MapperUtil.map(eventElementEntities, GetEventElementRequest.class);
    }

    public GetEventResponse add(String accountId, AddEventRequest addEventRequest) {
        EventEntity eventEntity = MapperUtil.map(addEventRequest, EventEntity.class);
        eventEntity.setCreator(accountId);
        List<String> members = new ArrayList<>();
        members.add(accountId);
        eventEntity.setMembers(members);
        return MapperUtil.map(eventManager.save(eventEntity), GetEventResponse.class);
    }

    private GetEventResponse mapEventResponseByEntity(String accountId, EventEntity event) {
        GetEventResponse eventResponse = MapperUtil.map(event, GetEventResponse.class);
        if (event.getMembers() != null && event.getMembers().size() > 0) {
            eventResponse.setMembers(accountManager.findByIds(event.getMembers()));
            if (event.getMembers().contains(accountId)) {
                eventResponse.setJoined(true);
            }
        }
        eventResponse.setCreator(accountManager.findById(event.getCreator()));
        eventResponse.setGroup(groupManager.findById(event.getGroupId()));
        return eventResponse;
    }
}
