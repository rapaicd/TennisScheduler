package com.tennis.timeslotservice.repository;

import com.tennis.timeslotservice.model.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TimeslotRepository extends JpaRepository<Timeslot,Long> {
    Optional<Timeslot> findById(long id);
    @Query("select t from Timeslot t where t.userEmail = ?2 and t.id <> ?3 and date(t.startDate) = date(?1) and t.id <> ?3")
    Timeslot checkIfTimeslotIsAlreadyReserved(Date startDate, String idPerson, long idTimeslot);
    @Query("select t from Timeslot t where t.userEmail = ?1")
    List<Timeslot> getAllTimeslotsForUser(String userEmail);
    @Query("select t from Timeslot t where date(t.startDate) < date(?1) and date(t.endDate)<date(?1)")
    List<Timeslot> getDeprecatedTimeslots(Date today);
    @Query("select t from Timeslot t join fetch t.tennisCourt court where ?1 <= t.endDate and t.startDate <= ?2 and court.id = ?3 and t.id <> ?4")
    List<Timeslot> checkOverlapping(Date startDate, Date endDate, long idTennisCourt, long idTimeslot);
}
