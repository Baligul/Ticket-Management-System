package com.intuit.tms.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.intuit.tms.entities.AccountTeamMap;

public interface AccountTeamMapRepository extends JpaRepository<AccountTeamMap, Long> {
	List<AccountTeamMap> findByTeamId(Long teamId);

	List<AccountTeamMap> findByAccountId(Long accountId);

	void deleteByAccountId(Long accountId);

	void deleteByTeamId(Long teamId);
}
