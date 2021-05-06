package com.xebia.tennisleagueserver.controllers;

import com.xebia.tennisleagueserver.entities.Participant;
import com.xebia.tennisleagueserver.services.ParticipantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Santosh Walsetwar
 */

@RestController
public class ParticipantController {

    private final ParticipantService service;

    private static final Logger LOG = LoggerFactory.getLogger(ParticipantController.class);

    @Autowired
    public ParticipantController(ParticipantService service)
    {
        this.service = service;
    }

    @ApiOperation("Get All Participants")
    @GetMapping("/api/v1/participants")
    public ResponseEntity<List<Participant>> getAllParticipants(){
        LOG.info("Get Participant request received");
        return new ResponseEntity<>(service.getParticipants(), HttpStatus.OK);
    }

    @ApiOperation("Add Participant")
    @PostMapping("/api/v1/participants")
    public ResponseEntity<Participant> addParticipant(@Valid @RequestBody Participant participant){
        LOG.info("Add participant request received");
        return new ResponseEntity<>(service.addParticipant(participant), HttpStatus.OK);
    }
}
