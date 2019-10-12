package com.intuit.tms.services;

import com.intuit.tms.entities.Team;

import java.util.List;

import com.intuit.tms.dto.TeamDTO;

public interface TeamService {
	Team getTeamByName(String name);

	Team getTeamById(Long id);

	Team saveTeam(TeamDTO teamDTO);

	List<Team> getAllTeams();

	void deleteTeam(Long id);

	Team updateTeam(TeamDTO teamDTO, Long teamId);
}