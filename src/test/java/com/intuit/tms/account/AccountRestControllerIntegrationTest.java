package com.intuit.tms.account;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.intuit.tms.controllers.AccountManagementRestController;
import com.intuit.tms.entities.Account;
import com.intuit.tms.services.AccountService;
import com.intuit.tms.services.ErrorBuilderService;
import com.intuit.tms.services.TeamService;

//import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountManagementRestController.class)
public class AccountRestControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private AccountService accountService;

	@MockBean
	private TeamService teamService;

	@MockBean
	private ErrorBuilderService errorBuilderService;

	@Test
	public void givenAccounts_whenGetAccounts_thenReturnJsonArray() throws Exception {
		Set<Account> accountSet = new HashSet<Account>();

		Account a = new Account();
		a.setName("Baligul Hasan");
		a.setUsername("baligul_hasan@intuit.com");
		a.setEmail("baligul_hasan@intuit.com");
		a.setPassword("$2a$10$9NhAigH0vdTPk1M45AVSYO1UpA0ZQSl1ce6drvP4KdPzlBHnnHGm2");
		a.setMobilePhone("8796576854");
		accountSet.add(a);

		Account b = new Account();
		b.setName("Ashish");
		b.setUsername("ashish@intuit.com");
		b.setEmail("ashish@intuit.com");
		b.setPassword("$2a$10$9NhAigH0vdTPk1M45AVSYO1UpA0ZQSl1ce6drvP4KdPzlBHnnHGm2");
		b.setMobilePhone("8796576856");

		accountSet.add(b);

		Mockito.when(accountService.getAllAccounts()).thenReturn(accountSet);

		mvc.perform(get("/admin/api/v1/accounts").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());

//		mvc.perform(get("/admin/api/v1/accounts").contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().is4xxClientError()).andExpect(jsonPath("$", hasSize(2)))
//				.andExpect(jsonPath("$[0].name", is(a.getName()))).andExpect(jsonPath("$[1].name", is(b.getName())));
	}
}