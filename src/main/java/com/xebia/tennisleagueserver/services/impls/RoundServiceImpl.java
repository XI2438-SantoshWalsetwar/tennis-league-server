package com.xebia.tennisleagueserver.services.impls;

import com.xebia.tennisleagueserver.entities.Round;
import com.xebia.tennisleagueserver.repositories.RoundRepository;
import com.xebia.tennisleagueserver.services.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoundServiceImpl implements RoundService {

    private final RoundRepository roundRepository;
    private final String ROUND_NAME_PREFIX = "ROUND-";

    @Autowired
    public RoundServiceImpl(RoundRepository roundRepository) {
        this.roundRepository = roundRepository;
    }

    @Override
    public Round addRound(Round round) {
        return roundRepository.save(round);
    }

    @Override
    public String nextRound() {
        return ROUND_NAME_PREFIX + (roundRepository.count()+1);
    }

    @Override
    public Round updateRound(Round round) {
        return addRound(round);
    }

    @Override
    public Optional<Round> findTopByOrderByIdDesc() {
        return roundRepository.findTopByOrderByIdDesc();
    }

}
