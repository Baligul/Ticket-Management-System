package com.intuit.tms.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.tms.dto.StatusDTO;
import com.intuit.tms.entities.Status;
import com.intuit.tms.services.StatusService;

@RestController
public class StatusManagementRestController {

	@Autowired
	private StatusService statusService;

	@ModelAttribute("status")
	public StatusDTO statusDTO() {
		return new StatusDTO();
	}

	@PostMapping("/admin/api/v1/status")
	public ResponseEntity<Object> saveStatus(@ModelAttribute("status") @Valid StatusDTO statusDTO,
			BindingResult result) {

		Status existing = statusService.getStatusByStatus(statusDTO.getStatus());
		if (existing != null) {
			return new ResponseEntity<Object>("There is already exist a status with status as " + statusDTO.getStatus(),
					HttpStatus.BAD_REQUEST);
		}

		if (result.hasErrors()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}

		statusService.saveStatus(statusDTO);

		return new ResponseEntity<Object>("You have successfully added a status", HttpStatus.OK);

	}

	@GetMapping("/admin/api/v1/statuses")
	public ResponseEntity<Object> getStatuses() {

		try {
			List<Status> statuss = statusService.getAllStatuses();
			return new ResponseEntity<Object>(statuss, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/admin/api/v1/status/{statusId}")
	public ResponseEntity<Object> updateStatus(@PathVariable(name = "statusId") Long statusId,
			@ModelAttribute("status") @Valid StatusDTO statusDTO, BindingResult result) {

		Status existing = statusService.getStatusById(statusId);
		if (existing == null) {
			return new ResponseEntity<Object>("The status with status id as " + statusId + " is not found",
					HttpStatus.BAD_REQUEST);
		}

		if (result.hasErrors()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}

		statusService.updateStatus(statusDTO, statusId);

		return new ResponseEntity<Object>("You have successfully updated a status with status id " + statusId,
				HttpStatus.OK);
	}

	@DeleteMapping("/admin/api/v1/status/{statusId}")
	public ResponseEntity<Object> deleteStatus(@PathVariable(name = "statusId") Long statusId) {

		Status existing = statusService.getStatusById(statusId);
		if (existing == null) {
			return new ResponseEntity<Object>("The status with status id as " + statusId + " is not found",
					HttpStatus.BAD_REQUEST);
		}

		try {
			statusService.deleteStatus(statusId);
			return new ResponseEntity<Object>("status successfully deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
