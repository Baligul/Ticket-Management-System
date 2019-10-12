package com.intuit.tms.services.impl;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.intuit.tms.entities.Account;
import com.intuit.tms.entities.Role;
import com.intuit.tms.repositories.AccountRepository;
import com.intuit.tms.services.AccountService;
import com.intuit.tms.dto.AccountRegistrationDTO;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;

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
	public Account save(AccountRegistrationDTO registration) {
		Account account = new Account();
		account.setName(registration.getName());
		account.setUsername(registration.getUsername());
		account.setMobilePhone(registration.getMobilePhone());
		account.setEmail(registration.getEmail());
		account.setPassword(passwordEncoder.encode(registration.getPassword()));
		account.setRoles(Arrays.asList(new Role("ROLE_CANDIDATE")));
		return accountRepository.save(account);
	}
}
