package com.intuit.tms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.intuit.tms.entities.Message;
import com.intuit.tms.services.MessageService;

@Controller
public class HomeController {
	@Autowired
	private MessageService serviceMessage;

	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("msgs", serviceMessage.getAllMessages());
		return "userhome";
	}

	@PostMapping("/messages")
	public String saveMessage(Message message) {
		serviceMessage.saveMessage(message);
		return "redirect:/home";
	}
}
