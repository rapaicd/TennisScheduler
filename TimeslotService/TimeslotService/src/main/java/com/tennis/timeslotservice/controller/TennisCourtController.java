package com.tennis.timeslotservice.controller;

import com.tennis.timeslotservice.dto.TennisCourtDto;
import com.tennis.timeslotservice.mapper.TennisCourtDtoMapper;
import com.tennis.timeslotservice.service.TennisCourtService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tennis-courts")
@AllArgsConstructor
public class TennisCourtController {
    public final TennisCourtService tennisCourtService;
    public final TennisCourtDtoMapper tennisCourtDtoMapper;

    @GetMapping("/")
    public List<TennisCourtDto> getAllTennisCourts(){
        return tennisCourtService.getAllTennisCourts().stream().map(tennisCourtDtoMapper::fromTennisCourtToTennisCourtDto).collect(Collectors.toList());
    }

    @PostMapping("/")
    public TennisCourtDto saveTennisCourt(@RequestBody @Valid TennisCourtDto tennisCourtDto){
        return tennisCourtDtoMapper.fromTennisCourtToTennisCourtDto(tennisCourtService.saveTennisCourt(tennisCourtDtoMapper.fromTennisCourtDtoToTennisCourt(tennisCourtDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteTennisCourt(@PathVariable long id){
        tennisCourtService.deleteTennisCourtById(id);
    }

    @GetMapping("/{id}")
    public TennisCourtDto getTennisCourtById(@PathVariable long id){
        return tennisCourtDtoMapper.fromTennisCourtToTennisCourtDto(tennisCourtService.getTennisCourtById(id));
    }

    @PutMapping("/{id}")
    public TennisCourtDto updateTennisCourt(@PathVariable long id, @RequestBody @Valid TennisCourtDto tennisCourtDto){
        return tennisCourtDtoMapper.fromTennisCourtToTennisCourtDto(tennisCourtService.updateTennisCourt(id, tennisCourtDtoMapper.fromTennisCourtDtoToTennisCourt(tennisCourtDto)));
    }

}