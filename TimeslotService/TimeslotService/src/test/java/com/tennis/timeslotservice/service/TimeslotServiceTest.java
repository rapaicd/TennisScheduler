package com.tennis.timeslotservice.service;

import com.tennis.timeslotservice.model.TennisCourt;
import com.tennis.timeslotservice.model.Timeslot;
import com.tennis.timeslotservice.repository.TimeslotRepository;
import com.tennis.timeslotservice.response.TimeslotResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.ObjectError;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TimeslotServiceTest {

    @Mock
    private TimeslotRepository timeslotRepository;
    @InjectMocks
    private TimeslotService timeslotService;
    @Mock
    private TennisCourtService tennisCourtService;

    private final Timeslot timeslot = Timeslot.builder()
            .id(0L)
            .startDate(new Date(2023, Calendar.APRIL,23,13,30))
            .endDate(new Date(2023, Calendar.APRIL,23,14,30))
            .tennisCourt(TennisCourt.builder().id(0).build())
            .userEmail("mico@gmail.com")
            .build();

    @Test
    void canGetAllTimeslotsIfTennisPlayer() {
        when(timeslotRepository.getAllTimeslotsForUser("mico@gmail.com")).thenReturn(List.of(timeslot));
        assertEquals(List.of(timeslot),timeslotService.getAllTimeslots("ROLE_user","mico@gmail.com"));
    }
    @Test
    void canGetAllTimeslotsIfAdmin() {
        when(timeslotRepository.findAll()).thenReturn(List.of(timeslot));
        assertEquals(List.of(timeslot),timeslotService.getAllTimeslots("ROLE_admin","marko@gmail.com"));
    }

    @Test
    void getById() {
        Optional<Timeslot> getTimeslot = Optional.of(timeslot);
        when(timeslotRepository.findById(any(Long.class))).thenReturn(getTimeslot);
        assertEquals(getTimeslot,Optional.of(timeslotService.getById(0L)));
    }

    @Test
    void canAdminDeleteById() {
        when(timeslotRepository.findById(any(Long.class))).thenReturn(Optional.of(timeslot));
        timeslotService.deleteById(0L,"ROLE_admin","admin@gmail.com");
        verify(timeslotRepository).deleteById(0L);
    }
    @Test
    void canUserDeleteById() {
        when(timeslotRepository.findById(any(Long.class))).thenReturn(Optional.of(timeslot));
        timeslotService.deleteById(0L,"ROLE_user","mico@gmail.com");
        verify(timeslotRepository).deleteById(0L);
    }

    @Test
    void canUserUpdateTimeslot() {

        Timeslot newTimeslot = Timeslot.builder()
                .startDate(new Date(2023, Calendar.APRIL,24,14,30))
                .endDate(new Date(2023, Calendar.APRIL,24,15,30))
                .userEmail("mico@gmail.com")
                .tennisCourt(TennisCourt.builder().id(0).build())
                .build();

        List<ObjectError> errors = List.of();
        when(timeslotRepository.findById(anyLong())).thenReturn(Optional.of(timeslot));
        when(tennisCourtService.getTennisCourtById(anyLong())).thenReturn(TennisCourt.builder().id(0).build());
        doAnswer(AdditionalAnswers.returnsFirstArg()).when(this.timeslotRepository).save(isA(Timeslot.class));

        TimeslotResponse timeslotResponse = timeslotService.update(0L,newTimeslot,errors,"ROLE_user","mico@gmail.com");

        assertEquals(timeslotResponse.getTimeslot().getStartDate(),new Date(2023, Calendar.APRIL,24,14,30));
        assertEquals(timeslotResponse.getTimeslot().getEndDate(),new Date(2023, Calendar.APRIL,24,15,30));
        assertEquals(timeslotResponse.getTimeslot().getUserEmail(),"mico@gmail.com");
    }
    @Test
    void canAdminUpdateTimeslot() {

        Timeslot newTimeslot = Timeslot.builder()
                .startDate(new Date(2023, Calendar.APRIL,24,14,30))
                .endDate(new Date(2023, Calendar.APRIL,24,15,30))
                .userEmail("mico12@gmail.com")
                .tennisCourt(TennisCourt.builder().id(0).build())
                .build();

        List<ObjectError> errors = List.of();
        when(timeslotRepository.findById(anyLong())).thenReturn(Optional.of(timeslot));
        when(tennisCourtService.getTennisCourtById(anyLong())).thenReturn(TennisCourt.builder().id(0).build());
        doAnswer(AdditionalAnswers.returnsFirstArg()).when(this.timeslotRepository).save(isA(Timeslot.class));

        TimeslotResponse timeslotResponse = timeslotService.update(0L,newTimeslot,errors,"ROLE_admin","micoAdmin@gmail.com");

        assertEquals(timeslotResponse.getTimeslot().getStartDate(),new Date(2023, Calendar.APRIL,24,14,30));
        assertEquals(timeslotResponse.getTimeslot().getEndDate(),new Date(2023, Calendar.APRIL,24,15,30));
        assertEquals(timeslotResponse.getTimeslot().getUserEmail(),"mico12@gmail.com");
    }
    @Test
    void canUserReserveTimeslot() {

        List<ObjectError> errors = List.of();
        when(tennisCourtService.getTennisCourtById(anyLong())).thenReturn(TennisCourt.builder().id(0).build());
        doAnswer(AdditionalAnswers.returnsFirstArg()).when(this.timeslotRepository).save(isA(Timeslot.class));

        TimeslotResponse timeslotResponse = timeslotService.reserveTimeslot(timeslot,errors,timeslot.getUserEmail(),"ROLE_user","mico@gmail.com");

        assertEquals(timeslotResponse.getTimeslot().getStartDate(),timeslot.getStartDate());
        assertEquals(timeslotResponse.getTimeslot().getEndDate(),timeslot.getEndDate());
        assertEquals(timeslotResponse.getTimeslot().getUserEmail(),timeslot.getUserEmail());
    }

    @Test
    void canAdminReserveTimeslot() {

        List<ObjectError> errors = List.of();
        when(tennisCourtService.getTennisCourtById(anyLong())).thenReturn(TennisCourt.builder().id(0).build());
        doAnswer(AdditionalAnswers.returnsFirstArg()).when(this.timeslotRepository).save(isA(Timeslot.class));

        TimeslotResponse timeslotResponse = timeslotService.reserveTimeslot(timeslot,errors,timeslot.getUserEmail(),"ROLE_admin","micoAdmin@gmail.com");

        assertEquals(timeslotResponse.getTimeslot().getStartDate(),timeslot.getStartDate());
        assertEquals(timeslotResponse.getTimeslot().getEndDate(),timeslot.getEndDate());
        assertEquals(timeslotResponse.getTimeslot().getUserEmail(),timeslot.getUserEmail());
    }
}