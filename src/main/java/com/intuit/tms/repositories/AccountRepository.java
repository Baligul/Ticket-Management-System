package com.intuit.tms.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intuit.tms.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Optional<Account> findByEmail(String email);

	List<Account> findDistinctByTeams_Id(Long id);
}
