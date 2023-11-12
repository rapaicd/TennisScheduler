package com.tennis.userservice.model;

import com.tennis.userservice.model.enume.Gender;

import javax.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private String phoneNumber;
    private Date birthday;

}
