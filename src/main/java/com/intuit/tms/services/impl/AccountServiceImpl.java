package com.intuit.tms.services.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.intuit.tms.entities.Account;
import com.intuit.tms.entities.AccountRoleMap;
import com.intuit.tms.entities.AccountTeamMap;
import com.intuit.tms.entities.Team;
import com.intuit.tms.repositories.AccountRepository;
import com.intuit.tms.repositories.AccountRoleMapRepository;
import com.intuit.tms.repositories.AccountTeamMapRepository;
import com.intuit.tms.repositories.TeamRepository;
import com.intuit.tms.security.CustomUserDetailsService;
import com.intuit.tms.services.AccountService;
import com.intuit.tms.dto.AccountRegistrationDTO;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	AccountRoleMapRepository accountRoleMapRepository;

	@Autowired
	TeamRepository teamRepository;

	@Autowired
	AccountTeamMapRepository accountTeamMapRepository;

	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Account getAccountByEmail(String email) {
		Optional<Account> account = accountRepository.findByEmail(email);
		if (account.isPresent()) {
			return account.get();
		}
		return null;
	}

	@Override
	public Account save(AccountRegistrationDTO registration, Long teamId) {
		Account account = new Account();
		account.setName(registration.getName());
		account.setUsername(registration.getEmail());
		account.setMobilePhone(registration.getMobilePhone());
		account.setEmail(registration.getEmail());
		account.setPassword(passwordEncoder.encode(registration.getPassword()));

		Account saveAccount = accountRepository.save(account);

		accountRoleMapRepository.save(new AccountRoleMap(saveAccount.getId(), 2L));
		accountTeamMapRepository.save(new AccountTeamMap(saveAccount.getId(), teamId));

		account.setCreatedBy(customUserDetailsService.getCurrentLoggedInUserId());

		return accountRepository.save(account);
	}

	@Override
	public Map<Long, Account> getAllAccounts() {
		List<Team> teams = teamRepository.findAll();
		Map<Long, Account> accounts = new HashMap<Long, Account>();

		teams.parallelStream().forEach(team -> {
			accountRepository.findDistinctByTeams_Id(team.getId()).parallelStream().forEach(account -> {
				if (!accounts.containsKey(account.getId()))
					accounts.put(account.getId(), account);
			});

		});

		return accounts;
	}

	@Override
	public List<Account> getAccountByTeams(Long teamId) {
		Optional<Team> team = teamRepository.findById(teamId);

		if (team.isPresent()) {
			List<Account> accounts = accountRepository.findDistinctByTeams_Id(team.get().getId());

			return accounts;
		}
		return null;
	}

	@Override
	public Account getAccountById(Long accountId) {
		Optional<Account> account = accountRepository.findById(accountId);
		if (account.isPresent()) {
			return account.get();
		}
		return null;
	}

	@Override
	public Account updateAccount(AccountRegistrationDTO accountRegistrationDTO, Long accountId) {
		Long updatedBy = customUserDetailsService.getCurrentLoggedInUserId();
		Account account = new Account();
		account.setId(accountId);
		account.setName(accountRegistrationDTO.getName());
		account.setUsername(accountRegistrationDTO.getEmail());
		account.setMobilePhone(accountRegistrationDTO.getMobilePhone());
		account.setEmail(accountRegistrationDTO.getEmail());
		account.setPassword(passwordEncoder.encode(accountRegistrationDTO.getPassword()));
		account.setUpdatedBy(updatedBy);
		account.setUpdatedOn(LocalDateTime.now());

		return accountRepository.save(account);
	}

	@Override
	public void deleteAccount(Long accountId) {
		accountRepository.deleteById(accountId);
		accountRoleMapRepository.deleteByAccountId(accountId);
		accountTeamMapRepository.deleteByAccountId(accountId);
	}
}
