package com.tennis.timeslotservice.validator;

import com.tennis.timeslotservice.dto.TimeslotDto;
import com.tennis.timeslotservice.model.Timeslot;
import com.tennis.timeslotservice.repository.TimeslotRepository;
import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class IsReservedValidatorTest {
    ConstraintValidatorContext constraintValidatorContext;
    @Mock
    TimeslotRepository timeslotRepository;
    @InjectMocks
    IsReservedValidator isReservedValidator;
    private final Timeslot timeslot = Timeslot.builder()
            .id(1)
            .startDate(new DateTime(2021, 12, 12, 21,30).toDate())
            .endDate(new DateTime(2021, 12, 12, 22,30).toDate())
            .userEmail("user@gmail.com")
            .build();


    @Test
    void isReservedValidatorValid(){
        TimeslotDto timeslotDto = TimeslotDto.builder()
                .id(2)
                .dateStart(new DateTime(2021, 12, 12, 21,30).toDate())
                .dateEnd(new DateTime(2021, 12, 12, 22,30).toDate())
                .userEmail("user@gmail.com")
                .build();

       Mockito.when(timeslotRepository.checkIfTimeslotIsAlreadyReserved(timeslotDto.getDateStart(), timeslotDto.getUserEmail(), timeslotDto.getId())).thenReturn(null);

        assertTrue(isReservedValidator.isValid(timeslotDto, constraintValidatorContext));
    }

    @Test
    void isReservedValidatorNotValid() {
        TimeslotDto timeslotDto = TimeslotDto.builder()
                .id(2)
                .dateStart(new DateTime(2021, 12, 12, 21,30).toDate())
                .dateEnd(new DateTime(2021, 12, 12, 22,30).toDate())
                .userEmail("user@gmail.com")
                .build();

        doReturn(timeslot).when(timeslotRepository).checkIfTimeslotIsAlreadyReserved(timeslotDto.getDateStart(), timeslotDto.getUserEmail(), timeslotDto.getId());

        assertFalse(isReservedValidator.isValid(timeslotDto, constraintValidatorContext));
    }
}
