package com.intuit.tms.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.intuit.tms.entities.AccountTeamMap;

@Repository
public interface AccountTeamMapRepository extends JpaRepository<AccountTeamMap, Long> {

	@Query(value = "SELECT * account_team_map WHERE team_id=?1", nativeQuery = true)
	List<AccountTeamMap> findByTeamId(Long teamId);

	@Query(value = "SELECT * account_team_map WHERE account_id=?1", nativeQuery = true)
	List<AccountTeamMap> findByAccountId(Long accountId);

	@Query(value = "DELETE FROM account_team_map WHERE account_id=?1", nativeQuery = true)
	void deleteByAccountId(Long accountId);

	@Query(value = "DELETE FROM account_team_map WHERE team_id=?1", nativeQuery = true)
	void deleteByTeamId(Long teamId);
}
