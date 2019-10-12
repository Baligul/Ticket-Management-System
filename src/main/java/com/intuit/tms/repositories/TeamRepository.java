package com.intuit.tms.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intuit.tms.entities.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

	Optional<Team> findByName(String name);
}
