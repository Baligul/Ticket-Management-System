package com.intuit.tms.account;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.intuit.tms.entities.Account;
import com.intuit.tms.repositories.AccountRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryIntegrationTest {
	@Autowired
	private AccountRepository accountRepository;

	@Test
	public void whenFindById_thenReturnAccount() {
		// given
		Account a = new Account();

		a.setName("Baligul Hasan");
		a.setUsername("baligul_hasan@intuit.com");
		a.setEmail("baligul_hasan@intuit.com");
		a.setPassword("$2a$10$9NhAigH0vdTPk1M45AVSYO1UpA0ZQSl1ce6drvP4KdPzlBHnnHGm2");
		a.setMobilePhone("8796576854");
		accountRepository.save(a);

		// when
		Account found = accountRepository.findById(a.getId()).get();

		// then
		assertThat(found.getName()).isEqualTo(a.getName());
		assertThat(found.getUsername()).isEqualTo(a.getUsername());
		assertThat(found.getEmail()).isEqualTo(a.getEmail());
		assertThat(found.getPassword()).isEqualTo(a.getPassword());
		assertThat(found.getMobilePhone()).isEqualTo(a.getMobilePhone());
	}
}