package com.xebia.tennisleagueserver.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Entity
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonIgnoreProperties("round")
    @OneToMany(mappedBy = "round")
    List<Match> matches;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private String name;
    private ZonedDateTime createdOn;
    private ZonedDateTime lastModifiedOn;
}

