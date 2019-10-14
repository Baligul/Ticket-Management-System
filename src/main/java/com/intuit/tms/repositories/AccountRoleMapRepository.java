package com.intuit.tms.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.intuit.tms.entities.AccountRoleMap;

@Repository
public interface AccountRoleMapRepository extends JpaRepository<AccountRoleMap, Long> {
	@Query(value = "SELECT * account_role_map WHERE role_id=?1", nativeQuery = true)
	List<AccountRoleMap> findByRoleId(Long roleId);

	@Query(value = "SELECT * account_role_map WHERE account_id=?1", nativeQuery = true)
	List<AccountRoleMap> findByAccountId(Long accountId);

	@Query(value = "DELETE FROM account_role_map WHERE account_id=?1", nativeQuery = true)
	void deleteByAccountId(Long accountId);
}
