package com.intuit.tms.services;

import com.intuit.tms.entities.Account;

import java.util.List;
import java.util.Map;

import com.intuit.tms.dto.AccountRegistrationDTO;

public interface AccountService {
	Account getAccountByEmail(String email);

	Account save(AccountRegistrationDTO registration, Long teamId);

	Map<Long, Account> getAllAccounts();

	List<Account> getAccountByTeams(Long teamId);

	Account getAccountById(Long accountId);

	Account updateAccount(AccountRegistrationDTO accountRegistrationDTO, Long accountId);

	void deleteAccount(Long accountId);
}
