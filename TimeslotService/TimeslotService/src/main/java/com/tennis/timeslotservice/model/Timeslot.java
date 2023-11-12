package com.tennis.timeslotservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Timeslot {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private Date startDate;
    private Date endDate;
    private String userEmail;
    @ManyToOne
    @JoinColumn(name="tennis_court_id")
    @JsonIgnore
    private TennisCourt tennisCourt;
}
