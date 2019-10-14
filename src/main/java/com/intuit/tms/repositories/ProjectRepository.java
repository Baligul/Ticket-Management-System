package com.intuit.tms.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intuit.tms.entities.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

	Optional<Project> findByName(String name);
}
