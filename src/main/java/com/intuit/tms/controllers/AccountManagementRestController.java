package com.intuit.tms.controllers;

import java.util.List;
import java.util.Map;

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

import com.intuit.tms.dto.AccountRegistrationDTO;
import com.intuit.tms.entities.Account;
import com.intuit.tms.entities.Team;
import com.intuit.tms.services.AccountService;
import com.intuit.tms.services.TeamService;

@RestController
public class AccountManagementRestController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private TeamService teamService;

	@ModelAttribute("account")
	public AccountRegistrationDTO accountRegistrationDTO() {
		return new AccountRegistrationDTO();
	}

	@PostMapping("/admin/api/v1/account/{teamId}")
	public ResponseEntity<Object> saveAccount(
			@ModelAttribute("account") @Valid AccountRegistrationDTO accountRegistrationDTO, BindingResult result,
			@PathVariable(name = "teamId") Long teamId) {

		Team existingTeam = teamService.getTeamById(teamId);
		if (existingTeam == null) {
			return new ResponseEntity<Object>("The team with team id as " + teamId + " is not found",
					HttpStatus.BAD_REQUEST);
		}

		Account existing = accountService.getAccountByEmail(accountRegistrationDTO.getEmail());
		if (existing != null) {
			return new ResponseEntity<Object>(
					"There is already exist an account with name as " + accountRegistrationDTO.getName(),
					HttpStatus.BAD_REQUEST);
		}

		if (result.hasErrors()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}

		accountService.save(accountRegistrationDTO, teamId);

		return new ResponseEntity<Object>("You have successfully added an account", HttpStatus.OK);

	}

	@GetMapping("/admin/api/v1/accounts")
	public ResponseEntity<Object> getAllAccounts() {

		try {
			Map<Long, Account> accounts = accountService.getAllAccounts();
			return new ResponseEntity<Object>(accounts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/admin/api/v1/accounts/{teamId}")
	public ResponseEntity<Object> getAllAccountsByTeamId(@PathVariable(name = "teamId") Long teamId) {

		try {
			List<Account> accounts = accountService.getAccountByTeams(teamId);
			return new ResponseEntity<Object>(accounts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/admin/api/v1/account/{accountId}")
	public ResponseEntity<Object> updateAccount(@PathVariable(name = "accountId") Long accountId,
			@ModelAttribute("account") @Valid AccountRegistrationDTO accountRegistrationDTO, BindingResult result) {

		Account existing = accountService.getAccountById(accountId);
		if (existing == null) {
			return new ResponseEntity<Object>("The account with account id as " + accountId + " is not found",
					HttpStatus.BAD_REQUEST);
		}

		if (result.hasErrors()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}

		accountService.updateAccount(accountRegistrationDTO, accountId);

		return new ResponseEntity<Object>("You have successfully updated an account with account id " + accountId,
				HttpStatus.OK);
	}

	@DeleteMapping("/admin/api/v1/account/{accountId}")
	public ResponseEntity<Object> deleteAccount(@PathVariable(name = "accountId") Long accountId) {

		Account existing = accountService.getAccountById(accountId);
		if (existing == null) {
			return new ResponseEntity<Object>("The account with account id as " + accountId + " is not found",
					HttpStatus.BAD_REQUEST);
		}

		try {
			accountService.deleteAccount(accountId);
			return new ResponseEntity<Object>("account successfully deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
