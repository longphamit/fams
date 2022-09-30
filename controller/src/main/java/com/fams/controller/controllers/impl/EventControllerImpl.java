package com.fams.controller.controllers.impl;

import com.fams.controller.controllers.EventController;
import com.fams.manager.dtos.response.GetEventResponse;
import com.fams.manager.entities.EventEntity;
import com.fams.manager.repositories.EventManager;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventControllerImpl implements EventController {

    private final EventManager eventManager;

    private final ModelMapper modelMapper;

    public GetEventResponse findById(String id) {
        EventEntity eventEntity = eventManager.findById(id);
        return modelMapper.map(eventEntity, GetEventResponse.class);
    }

    public List<GetEventResponse> findAll() {
        List<EventEntity> eventEntity = eventManager.findAll();
        return modelMapper.map(eventEntity, new TypeToken<List<GetEventResponse>>() {
        }.getType());
    }
}
