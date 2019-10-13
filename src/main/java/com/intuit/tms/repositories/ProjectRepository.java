package com.intuit.tms.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intuit.tms.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	Optional<Project> findByName(String name);
}
