package com.tennis.timeslotservice.repository;

import com.tennis.timeslotservice.model.TennisCourt;
import com.tennis.timeslotservice.model.enumes.SurfaceType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class TennisCourtRepositoryTest {

    @Autowired
    TennisCourtRepository tennisCourtRepository;

    @AfterEach
    void tearDown() {
        tennisCourtRepository.deleteAll();
    }

    @Test
    void itShouldFindObjectById() {
        TennisCourt tennisCourt = TennisCourt.builder()
                .id(0)
                .name("Court in Prigrevica")
                .surfaceType(SurfaceType.CLAY)
                .description("Very nice court in Prigrevica")
                .image("imageForCourtInPrigrevica")
                .timeslot(null)
                .address(null)
                .build();

        TennisCourt savedTennisCourt = tennisCourtRepository.save(tennisCourt);
    
        TennisCourt expected = tennisCourtRepository.findById(savedTennisCourt.getId()).get();
      
        assertThat(expected).isEqualTo(savedTennisCourt);
    }
}