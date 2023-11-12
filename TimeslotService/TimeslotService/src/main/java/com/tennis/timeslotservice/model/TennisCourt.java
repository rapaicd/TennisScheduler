package com.tennis.timeslotservice.model;

import com.tennis.timeslotservice.model.enumes.SurfaceType;
import javax.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TennisCourt {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    private SurfaceType surfaceType;
    private String description;
    private String image;
    @OneToMany(mappedBy = "tennisCourt", fetch = FetchType.LAZY,cascade =  CascadeType.ALL)
    private Set<Timeslot> timeslot;
    @OneToOne(fetch = FetchType.EAGER,cascade =  CascadeType.ALL)
    private Address address;
    @OneToOne(fetch = FetchType.EAGER,cascade =  CascadeType.ALL)
    private WorkingTime workingTime;
}