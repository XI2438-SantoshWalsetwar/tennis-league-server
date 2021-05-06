package com.xebia.tennisleagueserver.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Entity
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @Email
    @NotEmpty
    private String email;

    private boolean qualified;

    @JsonIgnoreProperties(value = {"participants","winner"})
    @ManyToMany(mappedBy = "participants")
    List<Match> matches;

    private ZonedDateTime createdOn;

    private ZonedDateTime lastModifiedOn;
}
