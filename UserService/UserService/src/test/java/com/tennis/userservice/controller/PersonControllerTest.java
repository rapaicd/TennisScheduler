package com.tennis.userservice.controller;

import com.tennis.userservice.mapper.PersonDtoMapper;
import com.tennis.userservice.model.Person;
import com.tennis.userservice.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.tennis.userservice.model.enume.Gender.FEMALE;
import static com.tennis.userservice.model.enume.Gender.MALE;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
@AutoConfigureMockMvc(addFilters = false)
class PersonControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private PersonService personService;
//    @MockBean
//    private PersonDtoMapper personDtoMapper;
//
//    @Test
//    void canGetAllPersons() throws Exception {
//
//        when(personService.getAllPersons("")).thenReturn(List.of(
//                new Person(1, "Milan", "Milanovic", "milan@gmail.com", MALE, "901901901", new Date(2002, 2, 2)),
//                new Person(2, "Jovana", "Jovanovic", "jovana@gmail.com", FEMALE, "901901901", new Date(2000, 11, 11))
//        ));
//        RequestBuilder request = MockMvcRequestBuilders
//                .get("/persons/")
//                .accept(MediaType.APPLICATION_JSON);
//
//        MvcResult mvcResult = mockMvc.perform(request)
//                .andExpect(status().isOk())
//                .andExpect(content()
//                        .json("[{\"id\":1,\"firstName\": \"Milan\",\"lastName\": \"Milanovic\",\"email\": \"milan@gmail.com\",\"gender\": \"MALE\",\"phoneNumber\": \"901901901\",\"birthday\": \"2023-02-02\",\"password\": null},{\"id\":2,\"firstName\": \"Jovana\",\"lastName\": \"Jovanovic\",\"email\": \"jovana@gmail.com\",\"gender\": \"FEMALE\",\"phoneNumber\": \"901901901\",\"birthday\": \"2023-11-11\",\"password\": null}]"))
//                .andReturn();
//    }
//
//    @Test
//    void canGetPersonById() throws Exception {
//        when(personService.findById(1L))
//                .thenReturn(new Person(1,"Milan","Milanovic","milan@gmail.com",MALE,"901901901",new Date(2002, Calendar.MARCH,2)));
//        RequestBuilder request = MockMvcRequestBuilders
//                .get("/persons/1")
//                .accept(MediaType.APPLICATION_JSON);
//
//        MvcResult mvcResult = mockMvc.perform(request)
//                .andExpect(status().isOk())
//                .andExpect(content().json("{\"id\":1,\"firstName\": \"Milan\",\"lastName\": \"Milanovic\",\"email\": \"milan@gmail.com\",\"gender\": \"MALE\",\"phoneNumber\": \"901901901\",\"birthday\": \"2023-02-02\"}"))
//                .andReturn();
//    }
}