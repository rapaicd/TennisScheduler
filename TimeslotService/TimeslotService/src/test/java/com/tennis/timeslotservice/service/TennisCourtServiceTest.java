package com.tennis.timeslotservice.service;

import com.tennis.timeslotservice.model.TennisCourt;
import com.tennis.timeslotservice.model.enumes.SurfaceType;
import com.tennis.timeslotservice.repository.TennisCourtRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TennisCourtServiceTest {
    @Mock
    private TennisCourtRepository tennisCourtRepository;
    @InjectMocks
    private TennisCourtService tennisCourtService;

    private final TennisCourt tennisCourt = TennisCourt.builder()
            .id(0)
            .name("Court in Prigrevica")
            .surfaceType(SurfaceType.CLAY)
            .description("Very nice court in Prigrevica")
            .image("imageForCourtInPrigrevica")
            .timeslot(null)
            .address(null)
            .build();


    @Test
   void canGetAllTennisCourts(){
        List<TennisCourt> tennisCourtList = List.of(
                new TennisCourt(1L, "Court in Kac",SurfaceType.CLAY,"This is a very nice court in Kac","imageForCourtInKac",null,null,null),
                new TennisCourt(1L, "Court in Prigrevica",SurfaceType.CLAY,"Very nice court in Prigrevica","imageForCourtInPrigrevica",null,null,null)
        );
        when(tennisCourtRepository.findAll()).thenReturn(tennisCourtList);
        assertEquals(tennisCourtList,tennisCourtService.getAllTennisCourts());
    }
    @Test
    void canSaveTennisCourt(){
        when(tennisCourtRepository.save(any(TennisCourt.class))).thenReturn(tennisCourt);
        assertEquals(tennisCourt,tennisCourtService.saveTennisCourt(tennisCourt));
    }

    @Test
    void canGetTennisCourtById(){
        Optional<TennisCourt> getTennisCourt = Optional.of(tennisCourt);

        when(tennisCourtRepository.findById(any(Long.class))).thenReturn(getTennisCourt);
        assertEquals(getTennisCourt,Optional.of(tennisCourtService.getTennisCourtById(0L)));
    }
    @Test
    void canUpdateTennisCourt() {
        TennisCourt newTennisCourt = TennisCourt.builder()
                .name("Sombor")
                .image("newImage")
                .description("nice court in Sombor")
                .surfaceType(SurfaceType.HARD)
                .build();

        when(tennisCourtRepository.findById(any(Long.class))).thenReturn(Optional.of(tennisCourt));
        doAnswer(AdditionalAnswers.returnsFirstArg()).when(this.tennisCourtRepository).save(isA(TennisCourt.class));

        TennisCourt tennisCourt = tennisCourtService.updateTennisCourt(0L,newTennisCourt);

        assertEquals("Sombor",tennisCourt.getName());
        assertEquals("newImage",tennisCourt.getImage());
        assertEquals("nice court in Sombor",tennisCourt.getDescription());
        assertEquals(SurfaceType.HARD,tennisCourt.getSurfaceType());
    }

    @Test
    void canDeleteTennisCourtById() {
        when(tennisCourtRepository.findById(any(Long.class))).thenReturn(Optional.of(tennisCourt));
        tennisCourtService.deleteTennisCourtById(tennisCourt.getId());
        verify(tennisCourtRepository).deleteById(tennisCourt.getId());
    }
}
