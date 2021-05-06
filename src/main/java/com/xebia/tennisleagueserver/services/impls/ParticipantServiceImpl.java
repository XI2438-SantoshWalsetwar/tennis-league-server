package com.xebia.tennisleagueserver.services.impls;

import com.xebia.tennisleagueserver.entities.Participant;
import com.xebia.tennisleagueserver.enums.AppExceptions;
import com.xebia.tennisleagueserver.exceptions.NoSuchElementFoundException;
import com.xebia.tennisleagueserver.exceptions.RegistrationFullException;
import com.xebia.tennisleagueserver.repositories.ParticipantRepository;
import com.xebia.tennisleagueserver.services.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository repository;

    @Value("${participants.limit}")
    private int MAX_PARTICIPANTS;

    @Autowired
    public ParticipantServiceImpl(ParticipantRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Participant> getParticipants() {
        return repository.findAll();
    }


    @Override
    public Participant addParticipant(Participant participant) {
        participant.setCreatedOn(ZonedDateTime.now());
        participant.setLastModifiedOn(ZonedDateTime.now());
        participant.setQualified(true);
        if(repository.count()>=this.MAX_PARTICIPANTS) {
            throw new RegistrationFullException(AppExceptions.REGISTRATION_FULL.msg);
        }
        return repository.save(participant);
    }

    @Override
    public Participant getParticipant(long id) {
        Participant participant = repository.findById(id).orElseThrow(()->  new NoSuchElementFoundException(AppExceptions.PARTICIPANT_NOT_FOUND.msg));
        return participant;
    }

    @Override
    public Participant updateParticipant(Participant participant) {
        return repository.save(participant);
    }

}
