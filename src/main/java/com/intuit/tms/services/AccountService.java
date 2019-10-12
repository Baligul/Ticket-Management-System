package com.intuit.tms.services;

import com.intuit.tms.entities.Account;
import com.intuit.tms.dto.AccountRegistrationDTO;

public interface AccountService {
	Account getAccountByEmail(String email);

	Account save(AccountRegistrationDTO registration);
}
