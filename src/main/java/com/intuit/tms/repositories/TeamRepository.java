package com.intuit.tms.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intuit.tms.entities.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

	Optional<Team> findByName(String name);
}
