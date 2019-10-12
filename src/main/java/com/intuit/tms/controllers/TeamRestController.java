package com.intuit.tms.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.tms.dto.TeamDTO;
import com.intuit.tms.entities.Team;
import com.intuit.tms.services.TeamService;

@RestController
public class TeamRestController {

	@Autowired
	private TeamService teamService;

	@ModelAttribute("team")
	public TeamDTO teamDTO() {
		return new TeamDTO();
	}

	@PostMapping("/admin/api/v1/team")
	public ResponseEntity<Object> saveTeam(@ModelAttribute("team") @Valid TeamDTO teamDTO, BindingResult result) {

		Team existing = teamService.getTeamByName(teamDTO.getName());
		if (existing != null) {
			return new ResponseEntity<Object>("There is already exist a team with name as " + teamDTO.getName(),
					HttpStatus.BAD_REQUEST);
		}

		if (result.hasErrors()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}

		teamService.saveTeam(teamDTO);

		return new ResponseEntity<Object>("You have successfully added a team", HttpStatus.OK);

	}

	@GetMapping("/admin/api/v1/teams")
	public ResponseEntity<Object> getTeams() {

		try {
			List<Team> teams = teamService.getAllTeams();
			return new ResponseEntity<Object>(teams, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/admin/api/v1/team/{teamId}")
	public ResponseEntity<Object> updateTeam(@PathVariable(name = "teamId") Long teamId,
			@ModelAttribute("team") @Valid TeamDTO teamDTO, BindingResult result) {

		Team existing = teamService.getTeamById(teamId);
		if (existing == null) {
			return new ResponseEntity<Object>("The team with team id as " + teamId + " is not found",
					HttpStatus.BAD_REQUEST);
		}

		if (result.hasErrors()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}

		teamService.updateTeam(teamDTO, teamId);

		return new ResponseEntity<Object>("You have successfully updated a team with team id " + teamId, HttpStatus.OK);
	}

	@DeleteMapping("/admin/api/v1/team/{teamId}")
	public ResponseEntity<Object> deleteTeam(@PathVariable(name = "teamId") Long teamId) {

		Team existing = teamService.getTeamById(teamId);
		if (existing == null) {
			return new ResponseEntity<Object>("The team with team id as " + teamId + " is not found",
					HttpStatus.BAD_REQUEST);
		}

		try {
			teamService.deleteTeam(teamId);
			return new ResponseEntity<Object>("team successfully deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
