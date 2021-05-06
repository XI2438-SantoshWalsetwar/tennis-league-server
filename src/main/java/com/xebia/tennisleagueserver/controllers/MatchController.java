package com.xebia.tennisleagueserver.controllers;

import com.xebia.tennisleagueserver.entities.Match;
import com.xebia.tennisleagueserver.services.MatchService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Santosh Walsetwar
 */

@RestController
@Validated
@RequestMapping("/league/api/v1")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }


    @ApiOperation("Create Match Groups")
    @PostMapping("/create-groups")
    public ResponseEntity<List<Match>> createMatchGroups(){
        return new ResponseEntity<>(matchService.createGroups(), HttpStatus.CREATED);
    }

    @ApiOperation("Get All Matches List")
    @GetMapping("/matches")
    public ResponseEntity<List<Match>> getListOfMatches(){
        return new ResponseEntity<>(matchService.getAllMatches(), HttpStatus.OK);
    }

    @ApiOperation("Update Match Winner")
    @PutMapping("/matches/{matchId}")
    public ResponseEntity<Match> updateMatchWinner(@PathVariable @Min(1) @NotNull Long matchId, @RequestParam @Min(1) @NotNull Long winnerParticipantId){
        return new ResponseEntity<>(matchService.updateMatchWinner(matchId,winnerParticipantId), HttpStatus.OK);
    }

}
