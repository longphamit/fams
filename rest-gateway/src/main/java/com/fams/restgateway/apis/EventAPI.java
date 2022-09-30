package com.fams.restgateway.apis;

import com.fams.controller.controllers.EventController;
import com.fams.manager.dtos.response.ObjectWrapperResponse;
import com.fams.manager.dtos.response.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/events")
public class EventAPI {
    @Autowired
    private EventController eventController;
    @GetMapping("{id}")
    public WrapperResponse getEvent(@PathVariable("id")String id){
        return ObjectWrapperResponse
                .builder()
                .data(eventController.findById(id))
                .build();
    }
    @GetMapping
    public WrapperResponse getAll(){
        return ObjectWrapperResponse.builder()
                .data(eventController.findAll()).build();
    }

}
