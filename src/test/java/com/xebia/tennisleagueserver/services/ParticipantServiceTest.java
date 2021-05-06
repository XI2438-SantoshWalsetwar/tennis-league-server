package com.xebia.tennisleagueserver.services;

import com.xebia.tennisleagueserver.entities.Participant;
import com.xebia.tennisleagueserver.exceptions.RegistrationFullException;
import com.xebia.tennisleagueserver.repositories.ParticipantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.ZonedDateTime;

@SpringBootTest
public class ParticipantServiceTest {

    @Autowired
    private ParticipantService participantService;

    @MockBean
    private ParticipantRepository participantRepository;



    @Test
    @DisplayName("Test participant registration")
    void testAddingParticipant(){
        //Given
        Participant participant = new Participant();
        participant.setId(1l);
        participant.setName("Santosh Walsetwar");
        participant.setQualified(true);
        participant.setLastModifiedOn(ZonedDateTime.now());
        participant.setCreatedOn(ZonedDateTime.now());
        participant.setEmail("santosh.walsetwar@xebia.com");

        doReturn(participant).when(participantRepository).save(any());

        //when
        Participant addedParticipant = participantRepository.save(participant);

        //then
        Assertions.assertNotNull(addedParticipant);
        Assertions.assertEquals(participant,addedParticipant);
        verify(participantRepository,atLeastOnce()).save(any());
    }


    @Test
    @DisplayName("Test registration full")
    void testRegistrationFull(){
        //Given
        Participant participant = new Participant();
        doReturn(4L).when(participantRepository).count();
        doReturn(participant).when(participantRepository).save(any());

        //then
        Assertions.assertThrows(RegistrationFullException.class,()->{
                participantService.addParticipant(participant);
        });
    }







}
