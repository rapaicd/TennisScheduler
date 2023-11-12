package com.tennis.timeslotservice.controller;

import com.tennis.timeslotservice.dto.TimeslotDto;
import com.tennis.timeslotservice.dto.TimeslotResponseDto;
import com.tennis.timeslotservice.mapper.TimeslotDtoMapper;
import com.tennis.timeslotservice.mapper.TimeslotResponseDtoMapper;
import com.tennis.timeslotservice.security.UserCredentials;
import com.tennis.timeslotservice.service.TimeslotService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/timeslots")
@AllArgsConstructor
public class TimeslotController {
    private final TimeslotService timeslotService;
    private final TimeslotDtoMapper timeslotDtoMapper;
    private final TimeslotResponseDtoMapper timeslotResponseDtoMapper;

    @GetMapping(value = "/")
    public List<TimeslotDto> getAll(Authentication authentication){
        return timeslotService.getAllTimeslots(UserCredentials.getUserRole(authentication),UserCredentials.getUserEmail(authentication)).stream()
                .map(timeslotDtoMapper::fromTimeslotToTimeslotDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public TimeslotDto getById(@PathVariable long id){
        return timeslotDtoMapper.fromTimeslotToTimeslotDto(timeslotService.getById(id));
    }

    @PostMapping(value = "/")
    public ResponseEntity<TimeslotResponseDto> save(@RequestBody @Valid TimeslotDto timeslotNew, BindingResult result, Authentication authentication){
        TimeslotResponseDto timeslotResponseDto = timeslotResponseDtoMapper.toTimeslotResponseDto(timeslotService.reserveTimeslot(timeslotDtoMapper.fromTimeslotDtoToTimeslot(timeslotNew), result.getAllErrors(), timeslotNew.getUserEmail(), UserCredentials.getUserRole(authentication), UserCredentials.getUserEmail(authentication)));
        return timeslotResponseDto.getTimeslot()==null?new ResponseEntity<>(timeslotResponseDto, HttpStatus.BAD_REQUEST):new ResponseEntity<>(timeslotResponseDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TimeslotResponseDto> update(@PathVariable long id, @RequestBody @Valid TimeslotDto timeslotUpdate, BindingResult result, Authentication authentication){
        TimeslotResponseDto timeslotResponseDto = timeslotResponseDtoMapper.toTimeslotResponseDto(timeslotService.update(id, timeslotDtoMapper.fromTimeslotDtoToTimeslot(timeslotUpdate), result.getAllErrors(), UserCredentials.getUserRole(authentication),UserCredentials.getUserEmail(authentication)));
        return timeslotResponseDto.getTimeslot()==null?new ResponseEntity<>(timeslotResponseDto, HttpStatus.BAD_REQUEST):new ResponseEntity<>(timeslotResponseDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void cancelTimeslot(@PathVariable long id, Authentication authentication){
        timeslotService.deleteById(id, UserCredentials.getUserRole(authentication),UserCredentials.getUserEmail(authentication));
    }
}
