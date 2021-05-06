package com.xebia.tennisleagueserver.services;

import com.xebia.tennisleagueserver.entities.Participant;

import java.util.List;

public interface ParticipantService {
    public List<Participant>  getParticipants();
    public Participant addParticipant(Participant participant);
    public Participant getParticipant(long id);
    public Participant updateParticipant(Participant participant);
}
