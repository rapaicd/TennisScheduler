package com.tennis.timeslotservice.repository;

import com.tennis.timeslotservice.model.Address;
import com.tennis.timeslotservice.model.TennisCourt;
import com.tennis.timeslotservice.model.Timeslot;
import com.tennis.timeslotservice.model.enumes.SurfaceType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class TimeslotRepositoryTest {

    @Autowired
    TimeslotRepository timeslotRepository;

    @Autowired
    TennisCourtRepository tennisCourtRepository;

    private long tennisCourtId;


    private final TennisCourt tennisCourt = TennisCourt.builder()
            .id(0L)
            .name("Court in Sabac")
            .description("This is very nice court in Sabac")
            .surfaceType(SurfaceType.CLAY)
            .address(Address.builder()
                    .city("Sabac")
                    .country("Serbia")
                    .street("Cerska")
                    .number(23)
                    .build())
            .build();
    private final Timeslot timeslot = Timeslot.builder()
                .startDate(new Date(2022, 12,12,19,45))
                .endDate(new Date(2022, 12,12,20,30))
                .tennisCourt(tennisCourt)
                .build();


    @AfterEach
    void tearDown() {
        timeslotRepository.deleteAll();
    }

    @BeforeEach
    void setUp() {
        tennisCourtId = tennisCourtRepository.save(tennisCourt).getId();
        timeslotRepository.save(timeslot);
    }

    @Test
    void checkOverlappingWithStartOfExistingTimeslot(){
        List<Timeslot> expected = timeslotRepository.checkOverlapping(new Date(2022, 12,12,18,0), new Date(2022, 12,12,20,0), tennisCourtId, 100L);

        assertThat(expected).isEqualTo(timeslotRepository.findAll());
    }

    @Test
    void overlappingWithEndOfExistingTimeslot(){
        List<Timeslot> expected = timeslotRepository.checkOverlapping(new Date(2022, 12,12,20,0), new Date(2022, 12,12,21,0), tennisCourtId, 100L);

        assertThat(expected).isEqualTo(timeslotRepository.findAll());
    }

    @Test
    void overlappingWithMiddleOfExistingTimeslot(){
        List<Timeslot> expected = timeslotRepository.checkOverlapping(new Date(2022, 12,12,20,0), new Date(2022, 12,12,20,15), tennisCourtId, 100L);

        assertThat(expected).isEqualTo(timeslotRepository.findAll());
    }

    @Test
    void overlappingWithStartAndEndOfExistingTimeslot(){
        List<Timeslot> expected = timeslotRepository.checkOverlapping(new Date(2022, 12,12,19,0), new Date(2022, 12,12,20,45), tennisCourtId, 100L);

        assertThat(expected).isEqualTo(timeslotRepository.findAll());
    }

}
