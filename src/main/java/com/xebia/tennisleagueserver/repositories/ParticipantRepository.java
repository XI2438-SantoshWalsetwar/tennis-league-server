package com.xebia.tennisleagueserver.repositories;

import com.xebia.tennisleagueserver.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant,Long> {
}
