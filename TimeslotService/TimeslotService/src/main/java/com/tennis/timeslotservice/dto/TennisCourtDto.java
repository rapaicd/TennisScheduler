package com.tennis.timeslotservice.dto;

import com.tennis.timeslotservice.model.Address;
import com.tennis.timeslotservice.model.enumes.SurfaceType;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TennisCourtDto {

    private long id;
    @NotEmpty(message = "Name cannot be empty")
    @Length(min = 2, message = "The name must have at least 2 characters")
    @NonNull
    private String name;
    @NotNull(message = "Surface type cannot be null")
    private SurfaceType surfaceType;
    private String description;
    @NotEmpty(message = "Image cannot be empty")
    @NonNull
    private String image;
    @NotNull(message = "Address cannot be null")
    private Address address;
    @NotNull(message = "Working time cannot be null")
    private WorkingTimeDto workingTimeDto;
}
