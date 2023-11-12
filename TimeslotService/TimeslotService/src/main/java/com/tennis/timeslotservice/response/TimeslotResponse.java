package com.tennis.timeslotservice.response;

import com.tennis.timeslotservice.model.Timeslot;
import lombok.*;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeslotResponse {
    private List<ObjectError> message;
    private Timeslot timeslot;
}
