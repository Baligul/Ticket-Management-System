package com.intuit.tms.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.intuit.tms.entities.AccountRoleMap;

public interface AccountRoleMapRepository extends JpaRepository<AccountRoleMap, Long> {
	List<AccountRoleMap> findByRoleId(Long roleId);

	List<AccountRoleMap> findByAccountId(Long accountId);

	void deleteByAccountId(Long accountId);
}
