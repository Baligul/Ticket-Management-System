package com.intuit.tms.web;

import com.intuit.tms.entities.Account;
import com.intuit.tms.services.AccountService;
import com.intuit.tms.dto.AccountRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class AccountRegistrationController {

	@Autowired
	private AccountService accountService;

	@ModelAttribute("account")
	public AccountRegistrationDTO accountRegistrationDTO() {
		return new AccountRegistrationDTO();
	}

	@GetMapping
	public String showRegistrationForm(Model model) {
		return "registration";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("account") @Valid AccountRegistrationDTO accountDTO,
			BindingResult result) {

		Account existing = accountService.getAccountByEmail(accountDTO.getEmail());
		if (existing != null) {
			result.rejectValue("email", null, "There is already an account registered with that email");
		}

		if (result.hasErrors()) {
			return "registration";
		}

		accountService.save(accountDTO, null);
		return "redirect:/registration?success";
	}

}