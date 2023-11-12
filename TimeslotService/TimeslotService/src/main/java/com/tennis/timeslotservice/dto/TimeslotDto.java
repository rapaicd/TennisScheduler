package com.tennis.timeslotservice.dto;

import com.tennis.timeslotservice.validator.annotations.*;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;


@SameDateValidate
@FutureDateValidate
@DurationValidate
@WorkingDayValidate
@IsReservedValidate
@OverlappingTimeslotsValidate
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeslotDto {
    private long id = 0L;
    @NotNull(message = "Start date cannot be null")
    private Date dateStart;
    @NotNull(message = "End date cannot be null")
    private Date dateEnd;
    @NotEmpty(message = "User email cannot be empty")
    @NonNull
    private String userEmail;
    @NotNull(message = "Court id cannot be null")
    private long courtId;
}
