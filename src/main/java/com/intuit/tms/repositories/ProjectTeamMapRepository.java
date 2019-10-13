package com.intuit.tms.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.intuit.tms.entities.ProjectTeamMap;

public interface ProjectTeamMapRepository extends JpaRepository<ProjectTeamMap, Long> {
	@Query(value = "SELECT * FROM project_team_map WHERE team_id=?1", nativeQuery = true)
	List<ProjectTeamMap> findByTeamId(Long teamId);

	@Query(value = "SELECT * FROM project_team_map WHERE project_id=?1", nativeQuery = true)
	List<ProjectTeamMap> findByProjectId(Long projectId);

	@Modifying(flushAutomatically = true)
	@Query(value = "DELETE FROM project_team_map WHERE project_id=?1", nativeQuery = true)
	void deleteByProjectId(Long projectId);

	@Modifying(flushAutomatically = true)
	@Query(value = "DELETE FROM project_team_map WHERE team_id=?1", nativeQuery = true)
	void deleteByTeamId(Long teamId);
}
