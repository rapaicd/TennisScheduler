package com.tennis.timeslotservice.repository;

import com.tennis.timeslotservice.model.TennisCourt;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TennisCourtRepository extends JpaRepository<TennisCourt,Long> {
}
