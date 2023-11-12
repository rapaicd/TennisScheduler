package com.tennis.timeslotservice.controller;

import com.tennis.timeslotservice.dto.TennisCourtDto;
import com.tennis.timeslotservice.mapper.TennisCourtDtoMapper;
import com.tennis.timeslotservice.model.TennisCourt;
import com.tennis.timeslotservice.model.enumes.SurfaceType;
import com.tennis.timeslotservice.service.TennisCourtService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TennisCourtController.class)
@AutoConfigureMockMvc(addFilters = false)
class TennisCourtControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TennisCourtService tennisCourtService;
    @MockBean
    private TennisCourtDtoMapper tennisCourtDtoMapper;
    @Test
    @Disabled
    void getAllTennisCourts() throws Exception {

        List<TennisCourt> list = List.of((new TennisCourt(1,"test-court", SurfaceType.HARD,"desc","image.jpeg",null,null,null)));
        when(tennisCourtDtoMapper.fromTennisCourtToTennisCourtDto(list.get(0))).thenReturn(
                new TennisCourtDto(1,"test-court", SurfaceType.HARD,"desc","image.jpeg",null,null)
        );

        RequestBuilder request = MockMvcRequestBuilders
                .get("/tennis-courts/")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\": 1, \"name\": \"test-court\", \"surfaceType\": \"HARD\", \"description\": \"desc\", \"image\": \"image.jpeg\", \"address\": null, \"workingTimeDto\":null}]"))
                .andReturn();
    }

}