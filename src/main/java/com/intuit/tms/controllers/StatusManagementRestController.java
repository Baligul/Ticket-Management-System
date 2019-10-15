package com.intuit.tms.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.tms.dto.StatusDTO;
import com.intuit.tms.dto.StatusUpdateDTO;
import com.intuit.tms.entities.Status;
import com.intuit.tms.services.ErrorBuilderService;
import com.intuit.tms.services.StatusService;

import io.swagger.annotations.Api;

@RestController
@Api(description = "Status Management APIs", produces = "application/json", tags = { "A3" })
public class StatusManagementRestController {

	@Autowired
	private StatusService statusService;

	@Autowired
	private ErrorBuilderService errorBuilderService;

	@ModelAttribute("status")
	public StatusDTO statusDTO() {
		return new StatusDTO();
	}

	@ModelAttribute("statusUpdate")
	public StatusUpdateDTO statusUpdateDTO() {
		return new StatusUpdateDTO();
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
			return new ResponseEntity<Object>(errorBuilderService.generateErrorMessage(result), HttpStatus.BAD_REQUEST);
		}

		try {
			statusService.saveStatus(statusDTO);
			return new ResponseEntity<Object>("You have successfully added a status", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Status cannot be saved, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/admin/api/v1/statuses")
	public ResponseEntity<Object> getStatuses() {

		try {
			List<Status> statuss = statusService.getAllStatuses();
			return new ResponseEntity<Object>(statuss, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Cannot ftech the statuses, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/admin/api/v1/status/{status}")
	public ResponseEntity<Object> updateStatus(@PathVariable(name = "status") String strStatus,
			@ModelAttribute("statusUpdate") @Valid StatusUpdateDTO statusUpdateDTO, BindingResult result) {

		Status existing = statusService.getStatusByStatus(strStatus);
		if (existing == null) {
			return new ResponseEntity<Object>("The status as " + strStatus + " is not found", HttpStatus.BAD_REQUEST);
		}

		if (result.hasErrors()) {
			return new ResponseEntity<Object>(errorBuilderService.generateErrorMessage(result), HttpStatus.BAD_REQUEST);
		}

		try {
			statusService.updateStatus(statusUpdateDTO, strStatus);
			return new ResponseEntity<Object>("You have successfully updated a status as " + strStatus, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Status cannot be updated, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Transactional
	@DeleteMapping("/admin/api/v1/status/{status}")
	public ResponseEntity<Object> deleteStatus(@PathVariable(name = "status") String strStatus) {

		Status existing = statusService.getStatusByStatus(strStatus);
		if (existing == null) {
			return new ResponseEntity<Object>("The status as " + strStatus + " is not found", HttpStatus.BAD_REQUEST);
		}

		try {
			statusService.deleteStatus(strStatus);
			return new ResponseEntity<Object>("Status successfully deleted", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Status cannot be deleted, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}
}
