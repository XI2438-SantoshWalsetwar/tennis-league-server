package com.xebia.tennisleagueserver.services.impls;

import com.xebia.tennisleagueserver.entities.Match;
import com.xebia.tennisleagueserver.entities.Participant;
import com.xebia.tennisleagueserver.entities.Round;
import com.xebia.tennisleagueserver.enums.AppExceptions;
import com.xebia.tennisleagueserver.exceptions.CanNotProceedException;
import com.xebia.tennisleagueserver.exceptions.InsufficientParticipantsException;
import com.xebia.tennisleagueserver.exceptions.InvalidParticipantException;
import com.xebia.tennisleagueserver.exceptions.NoSuchElementFoundException;
import com.xebia.tennisleagueserver.repositories.MatchRepository;
import com.xebia.tennisleagueserver.services.MatchService;
import com.xebia.tennisleagueserver.services.ParticipantService;
import com.xebia.tennisleagueserver.services.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final ParticipantService participantService;
    private final RoundService roundService;

    @Value("${matches.per.day}")
    private int MATCHES_PER_DAY;

    private final ZonedDateTime matchStartDate = ZonedDateTime.now().plusDays(1);
    private final int gapBetweenRounds = 3;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository, ParticipantService participantService, RoundService roundService) {
        this.matchRepository = matchRepository;
        this.participantService = participantService;
        this.roundService = roundService;
    }


    @Override
    public List<Match> createGroups() {
        //get all qualified participants
        List<Participant> participants = this.getParticipants(participant -> participant.isQualified());

        if(Objects.nonNull(participants) && participants.size()>=2)
        {
            //Create new round before scheduling the matches
            Round round  = addRound();

            //Schedule matches for all the qualified participants for that round
            List<Match> matches = scheduleMatches(round,participants);

            //Save all matches
            return matchRepository.saveAll(matches);
        }
        throw new InsufficientParticipantsException(AppExceptions.INSUFFICIENT_PARTICIPANTS.msg);
    }

    @Override
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    @Override
    public Match getMatch(long matchId) {
        Match match = matchRepository.findById(matchId).orElseThrow(()-> new NoSuchElementFoundException(AppExceptions.MATCH_NOT_FOUND.msg));
        return match;
    }

    @Override
    public Match updateMatchWinner(long matchId, long winnerParticipantId) {

        Participant participant = participantService.getParticipant(winnerParticipantId);
        Match match = getMatch(matchId);
        if(match.getParticipants().get(0).getId() == participant.getId() || match.getParticipants().get(1).getId() == participant.getId())
        {
            match.setWinner(participant);
            match.setLastModifiedOn(ZonedDateTime.now());
            Match updatedMatch = matchRepository.save(match);
            disQualifyOpponent(updatedMatch);
            closeRound(updatedMatch.getRound(),currentMatch -> currentMatch.getWinner() == null);
            return updatedMatch;
        }
        else{
            throw new InvalidParticipantException(AppExceptions.INVALID_MATCH_PARTICIPANT.msg);
        }
    }


    public List<Participant> getParticipants(Predicate<Participant> criteria){
        List<Participant> participants = participantService.getParticipants();
        return  participants.stream().filter(criteria).collect(Collectors.toList());
    }

    public Round addRound()
    {
        Round round = new Round();
        round.setCreatedOn(ZonedDateTime.now());
        round.setLastModifiedOn(ZonedDateTime.now());
        String name = roundService.nextRound();
        round.setName(name);
        round.setStartDate(getNextRoundDate());
        return roundService.addRound(round);
    }

    public List<Match> scheduleMatches(Round round, List<Participant> participants){
        //if odd no of participants skip 1st to divide participants into groups
        if(participants.size()%2!=0){
            participants =  participants.stream().skip(1).collect(Collectors.toList());
        }

        List<Match> matches = new ArrayList<>();
        for(int i=0;i<participants.size();i=i+2){
            Match match = new Match();
            match.setCreatedOn(ZonedDateTime.now());
            match.setLastModifiedOn(ZonedDateTime.now());
            match.setScheduledOn(getMatchDate(i/2,round.getStartDate()));
            match.setParticipants(participants.stream().skip(i).limit(2).collect(Collectors.toList()));
            match.setRound(round);
            matches.add(match);
        }
        return matches;
    }

    public ZonedDateTime getMatchDate(int matchNumber,ZonedDateTime startDate) {
        return startDate.plusDays(matchNumber/MATCHES_PER_DAY);
    }

    public void disQualifyOpponent(Match match){
        Participant looser;
        if(match.getParticipants().get(0).getId() == match.getWinner().getId()){
            looser = match.getParticipants().get(1);
        }else
            looser = match.getParticipants().get(0);

        looser.setQualified(false);
        looser.setLastModifiedOn(ZonedDateTime.now());
        participantService.updateParticipant(looser);
    }

    public ZonedDateTime getNextRoundDate(){
        Optional<Round> lastRound = roundService.findTopByOrderByIdDesc();
        ZonedDateTime lastRoundEndDate = matchStartDate;

        if(lastRound.isPresent() && lastRound.get().getEndDate()!=null)
             lastRoundEndDate = lastRound.get().getEndDate().plusDays(gapBetweenRounds);
        else if(lastRound.isPresent() && lastRound.get().getEndDate()==null)
            throw new CanNotProceedException(AppExceptions.ROUND_NOT_FINISHED.msg);

        return lastRoundEndDate;
    }

    public void closeRound(Round round,Predicate<Match> reverseCondition){
        if(!round.getMatches().stream().anyMatch(reverseCondition)){
            ZonedDateTime endDate = getMatchDate(round.getMatches().size()/2,round.getStartDate());
            round.setEndDate(endDate);
            round.setLastModifiedOn(ZonedDateTime.now());
            Round closedRound = roundService.updateRound(round);
        }
    }
}


