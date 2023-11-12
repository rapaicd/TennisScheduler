package com.tennis.timeslotservice.model;

import javax.persistence.*;
import lombok.*;

import java.sql.Time;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkingTime {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private Time startWorkingTimeWeekDay;
    private Time endWorkingTimeWeekDay;
    private Time startWorkingTimeWeekend;
    private Time endWorkingTimeWeekend;
}
