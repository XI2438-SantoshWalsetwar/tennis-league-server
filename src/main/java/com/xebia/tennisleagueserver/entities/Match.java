package com.xebia.tennisleagueserver.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;


@Data
@Entity
@Table(name = "Tennis_Match")
public class Match {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @JsonIgnoreProperties("matches")
  @ManyToMany
  @JoinTable(name = "MATCH_PARTICIPANTS",
          joinColumns = @JoinColumn(name = "MATCH_ID", referencedColumnName = "ID"),
          inverseJoinColumns = @JoinColumn(name = "PARTICIPANT_ID", referencedColumnName = "ID"))
  List<Participant> participants;

  @JsonIgnoreProperties("matches")
  @OneToOne
  Participant winner;

  @JsonIgnoreProperties("matches")
  @ManyToOne
  @JoinColumn(name = "ROUND_ID", referencedColumnName = "ID")
  private Round round;

  private ZonedDateTime scheduledOn;
  private ZonedDateTime createdOn;
  private ZonedDateTime lastModifiedOn;
}
