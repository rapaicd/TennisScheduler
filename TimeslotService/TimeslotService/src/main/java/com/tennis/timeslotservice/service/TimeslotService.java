package com.tennis.timeslotservice.service;

import com.tennis.timeslotservice.exception.UserNotAuthorizedException;
import com.tennis.timeslotservice.exception.UserNotFoundException;
import com.tennis.timeslotservice.model.Timeslot;
import com.tennis.timeslotservice.repository.TimeslotRepository;
import com.tennis.timeslotservice.response.TimeslotResponse;
import javax.persistence.EntityNotFoundException;

import com.tennis.timeslotservice.security.UserCredentials;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TimeslotService {
    private final TimeslotRepository timeslotRepository;
    private final TennisCourtService tennisCourtService;

    public TimeslotResponse update(long id, Timeslot timeslot, List<ObjectError> result, String role, String email){
        TimeslotResponse timeslotResponse = new TimeslotResponse();
        timeslotResponse.setMessage(result);
//        if(result.hasErrors())
//            return timeslotResponse;

        Timeslot existingTimeslot = timeslotRepository.findById(id).orElseThrow(()->new UserNotFoundException("Timeslot does not exist!"));

        if (role.equals("ROLE_user") && !timeslot.getUserEmail().equals(email))
            throw new UserNotAuthorizedException("You don't have permission.");

        if(existingTimeslot.getId() == timeslot.getId() && !existingTimeslot.getUserEmail().equals(email) && role.equals("ROLE_user"))
            throw new UserNotAuthorizedException("You don't have permission.");

        existingTimeslot.setStartDate(timeslot.getStartDate());
        existingTimeslot.setEndDate(timeslot.getEndDate());
        existingTimeslot.setUserEmail(timeslot.getUserEmail());
        existingTimeslot.setTennisCourt(tennisCourtService.getTennisCourtById(timeslot.getTennisCourt().getId()));

        timeslotResponse.setTimeslot(save(existingTimeslot));

        return timeslotResponse;
    }
    public TimeslotResponse reserveTimeslot(Timeslot timeslot, List<ObjectError> result, String userEmail, String userRole, String authUserEmail) {
        TimeslotResponse timeslotResponse = new TimeslotResponse();
        timeslotResponse.setMessage(result);

        if (result.isEmpty() && userRole.equals("ROLE_user") && userEmail.equals(authUserEmail)) {
            timeslot.setUserEmail(authUserEmail);
            timeslotResponse.setTimeslot(save(timeslot));
        }
        else if (userRole.equals("ROLE_admin"))
            timeslotResponse.setTimeslot(save(timeslot));

        return timeslotResponse;
    }
    public Timeslot getById(Long id){
        return timeslotRepository.findById(id).orElseThrow(()->new UserNotFoundException("This id isn't valid!"));
    }

    public void deleteById(Long id, String role, String email){
        Timeslot existingTimeslot = timeslotRepository.findById(id).orElseThrow(()->new UserNotFoundException("This id isn't valid!"));
        if (role.equals("ROLE_user") && !Objects.equals(existingTimeslot.getUserEmail(), email))
            throw new UserNotAuthorizedException("You don't have permission.");

        timeslotRepository.deleteById(id);
    }

    @Scheduled(cron = "${greeting.cron}")
    public void deleteDeprecatedTimeslots(){
        for (Timeslot timeslot: timeslotRepository.getDeprecatedTimeslots(new Date())) {
            timeslotRepository.deleteById(timeslot.getId());
        }
    }

    private Timeslot save(Timeslot timeslot) {
        timeslot.setTennisCourt(tennisCourtService.getTennisCourtById(timeslot.getTennisCourt().getId()));
        return timeslotRepository.save(timeslot);
    }

    public List<Timeslot> getAllTimeslots(String role, String email) {
        if(role.equals("ROLE_user"))
            return timeslotRepository.getAllTimeslotsForUser(email);
        else
            return timeslotRepository.findAll();
    }

}
