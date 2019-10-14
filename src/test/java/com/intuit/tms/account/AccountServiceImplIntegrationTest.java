package com.intuit.tms.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.intuit.tms.entities.Account;
import com.intuit.tms.repositories.AccountRepository;
import com.intuit.tms.repositories.AccountRoleMapRepository;
import com.intuit.tms.repositories.AccountTeamMapRepository;
import com.intuit.tms.repositories.TeamRepository;
import com.intuit.tms.security.CustomUserDetailsService;
import com.intuit.tms.services.AccountService;
import com.intuit.tms.services.impl.AccountServiceImpl;

@RunWith(SpringRunner.class)
public class AccountServiceImplIntegrationTest {

	@TestConfiguration
	static class AccountServiceImplTestContextConfiguration {

		@Bean
		public AccountService accountService() {
			return new AccountServiceImpl();
		}
	}

	@Autowired
	private AccountService accountService;

	@MockBean
	private AccountRepository accountRepository;

	@MockBean
	private AccountRoleMapRepository accountRoleMapRepository;

	@MockBean
	private TeamRepository teamRepository;

	@MockBean
	private AccountTeamMapRepository accountTeamMapRepository;

	@MockBean
	private CustomUserDetailsService customUserDetailsService;

	@MockBean
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Before
	public void setUp() {

		Account a = new Account();
		a.setId(10L);
		a.setName("John");
		a.setUsername("john@intuit.com");
		a.setEmail("john@intuit.com");
		a.setPassword("$2a$10$9NhAigH0vdTPk1M45AVSYO1UpA0ZQSl1ce6drvP4KdPzlBHnnHGm2");
		a.setMobilePhone("8796572685");

		Mockito.when(accountRepository.findById(a.getId())).thenReturn(Optional.of(a));
	}

	@Test
	public void whenValidId_thenAccountShouldBeFound() {
		Long id = 10L;
		Account found = accountService.getAccountById(id);

		assertThat(found.getName()).isEqualTo("John");
		assertThat(found.getUsername()).isEqualTo("john@intuit.com");
		assertThat(found.getEmail()).isEqualTo("john@intuit.com");
		assertThat(found.getPassword()).isEqualTo("$2a$10$9NhAigH0vdTPk1M45AVSYO1UpA0ZQSl1ce6drvP4KdPzlBHnnHGm2");
		assertThat(found.getMobilePhone()).isEqualTo("8796572685");
	}
}