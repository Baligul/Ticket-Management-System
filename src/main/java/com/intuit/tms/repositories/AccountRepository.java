package com.intuit.tms.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intuit.tms.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Optional<Account> findByEmail(String email);
}
