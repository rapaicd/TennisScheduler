package com.tennis.timeslotservice.service;

import com.tennis.timeslotservice.exception.UserNotFoundException;
import com.tennis.timeslotservice.model.TennisCourt;
import com.tennis.timeslotservice.repository.TennisCourtRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TennisCourtService {
    private final TennisCourtRepository tennisCourtRepository;

    public List<TennisCourt> getAllTennisCourts(){
        return tennisCourtRepository.findAll();
    }

    public TennisCourt getTennisCourtById(long id){
        return tennisCourtRepository.findById(id).orElseThrow(()->new UserNotFoundException("This id isn't valid"));
    }

    public TennisCourt saveTennisCourt(TennisCourt tennisCourt){
        return tennisCourtRepository.save(tennisCourt);
    }

    public void deleteTennisCourtById(Long id){
        tennisCourtRepository.findById(id).orElseThrow(()->new UserNotFoundException("This id isn't valid"));
        tennisCourtRepository.deleteById(id);
    }

    public TennisCourt updateTennisCourt(long id, TennisCourt tennisCourt){
        TennisCourt existingTennisCourt = tennisCourtRepository.findById(id).orElseThrow(()->new UserNotFoundException("This id isn't valid"));
        existingTennisCourt.setName(tennisCourt.getName());
        existingTennisCourt.setDescription(tennisCourt.getDescription());
        existingTennisCourt.setImage(tennisCourt.getImage());
        existingTennisCourt.setSurfaceType(tennisCourt.getSurfaceType());
        existingTennisCourt.setWorkingTime(tennisCourt.getWorkingTime());
        existingTennisCourt.setAddress(tennisCourt.getAddress());

        return tennisCourtRepository.save(existingTennisCourt);
    }
}
