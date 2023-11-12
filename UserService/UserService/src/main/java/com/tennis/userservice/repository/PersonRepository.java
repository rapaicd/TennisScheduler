package com.tennis.userservice.repository;

import com.tennis.userservice.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
    Person findByEmail(String userEmail);
}
