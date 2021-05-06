package com.xebia.tennisleagueserver.services;

import com.xebia.tennisleagueserver.entities.Match;
import com.xebia.tennisleagueserver.entities.Participant;

import java.util.List;

public interface MatchService {
    public List<Match> createGroups();
    public List<Match> getAllMatches();
    public Match getMatch(long matchId);
    public Match updateMatchWinner(long matchId,long winnerParticipantId);
}
