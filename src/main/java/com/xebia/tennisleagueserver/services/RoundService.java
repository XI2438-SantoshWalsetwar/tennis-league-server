package com.xebia.tennisleagueserver.services;

import com.xebia.tennisleagueserver.entities.Round;

import java.util.Optional;

public interface RoundService {
    public Round addRound(Round round);
    public String nextRound();
    public Round updateRound(Round round);
    public Optional<Round> findTopByOrderByIdDesc();
}
