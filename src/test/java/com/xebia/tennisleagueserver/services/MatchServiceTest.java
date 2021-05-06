package com.xebia.tennisleagueserver.services;

import com.xebia.tennisleagueserver.entities.Match;
import com.xebia.tennisleagueserver.entities.Participant;
import com.xebia.tennisleagueserver.exceptions.InsufficientParticipantsException;
import com.xebia.tennisleagueserver.repositories.MatchRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@SpringBootTest
public class MatchServiceTest {

    @Autowired
    private MatchService matchService;

    @MockBean
    private RoundService roundService;

    @MockBean
    private ParticipantService participantService;

    @MockBean
    private MatchRepository matchRepository;

    @Test
    @DisplayName("Ensuring sufficient number of qualified participants to start scheduling of matches")
    public void shouldThrowExceptionWhenNoQualifiedParticipant(){
        //given
        doReturn(new ArrayList<Participant>()).when(participantService).getParticipants();

        //then
        Assertions.assertThrows(InsufficientParticipantsException.class,()->{
            matchService.createGroups();
        });
    }

}
