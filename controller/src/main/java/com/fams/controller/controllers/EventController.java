package com.fams.controller.controllers;

import com.fams.manager.dtos.response.GetEventResponse;

import java.util.List;

public interface EventController {
    GetEventResponse findById(String id);
    List<GetEventResponse> findAll();
}
