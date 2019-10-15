package com.intuit.tms.controllers;

import java.util.List;
import java.util.Set;

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
import com.intuit.tms.services.ErrorBuilderService;
import com.intuit.tms.services.TeamService;

import io.swagger.annotations.Api;

@RestController
@Api(description = "Account Management APIs", produces = "application/json", tags = { "A2" })
public class AccountManagementRestController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private TeamService teamService;

	@Autowired
	private ErrorBuilderService errorBuilderService;

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
					"There is already exist an account with email as " + accountRegistrationDTO.getEmail(),
					HttpStatus.BAD_REQUEST);
		}

		if (result.hasErrors()) {
			return new ResponseEntity<Object>(errorBuilderService.generateErrorMessage(result), HttpStatus.BAD_REQUEST);
		}

		try {
			accountService.save(accountRegistrationDTO, teamId);
			return new ResponseEntity<Object>("You have successfully added an account", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Couldn't save account, Reason: " + ex.getMessage(), HttpStatus.OK);
		}

	}

	@GetMapping("/admin/api/v1/accounts")
	public ResponseEntity<Object> getAllAccounts() {

		try {
			Set<Account> accounts = accountService.getAllAccounts();
			return new ResponseEntity<Object>(accounts, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Couldn't get list of accounts, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/admin/api/v1/accounts/{teamId}")
	public ResponseEntity<Object> getAllAccountsByTeamId(@PathVariable(name = "teamId") Long teamId) {

		try {
			List<Account> accounts = accountService.getAccountByTeams(teamId);
			return new ResponseEntity<Object>(accounts, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Couldn't get list of accounts for team, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
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
			return new ResponseEntity<Object>(errorBuilderService.generateErrorMessage(result), HttpStatus.BAD_REQUEST);
		}

		try {
			accountService.updateAccount(accountRegistrationDTO, accountId);
		} catch (Exception ex) {
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.OK);
		}

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
			return new ResponseEntity<Object>("Account successfully deleted", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("The account cannot be deleted, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}
}
