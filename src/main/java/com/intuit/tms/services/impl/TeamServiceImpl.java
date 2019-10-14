package com.intuit.tms.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intuit.tms.entities.Team;
import com.intuit.tms.enums.TeamTypeEnum;
import com.intuit.tms.repositories.AccountTeamMapRepository;
import com.intuit.tms.repositories.TeamRepository;
import com.intuit.tms.security.CustomUserDetailsService;
import com.intuit.tms.services.TeamService;
import com.intuit.tms.dto.TeamDTO;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	TeamRepository teamRepository;

	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Autowired
	AccountTeamMapRepository accountTeamMapRepository;

	@Override
	public Team getTeamByName(String name) {
		Optional<Team> team = teamRepository.findByName(name);
		if (team.isPresent()) {
			return team.get();
		}
		return null;
	}

	@Override
	public Team getTeamById(Long id) {
		Optional<Team> team = teamRepository.findById(id);
		if (team.isPresent()) {
			return team.get();
		}
		return null;
	}

	@Override
	public Team saveTeam(TeamDTO teamDTO) {
		Long createdBy = customUserDetailsService.getCurrentLoggedInUserId();
		Team team = new Team();
		team.setName(teamDTO.getName());
		team.setTeamType(TeamTypeEnum.getByValue(teamDTO.getTeamType()));
		team.setDescription(teamDTO.getDescription());
		team.setCreatedBy(createdBy);
		return teamRepository.save(team);
	}

	@Override
	public List<Team> getAllTeams() {
		return teamRepository.findAll();
	}

	@Override
	public void deleteTeam(Long teamId) {
		teamRepository.deleteById(teamId);
		accountTeamMapRepository.deleteByTeamId(teamId);
	}

	@Override
	public Team updateTeam(TeamDTO teamDTO, Long teamId) {
		Long updatedBy = customUserDetailsService.getCurrentLoggedInUserId();
		Team team = new Team();
		team.setId(teamId);
		team.setName(teamDTO.getName());
		team.setTeamType(TeamTypeEnum.getByValue(teamDTO.getTeamType()));
		team.setDescription(teamDTO.getDescription());
		team.setUpdatedBy(updatedBy);
		team.setUpdatedOn(LocalDateTime.now());
		return teamRepository.save(team);
	}
}
