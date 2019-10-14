package com.intuit.tms.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intuit.tms.entities.Status;
import com.intuit.tms.repositories.AccountTeamMapRepository;
import com.intuit.tms.repositories.StatusRepository;
import com.intuit.tms.security.CustomUserDetailsService;
import com.intuit.tms.services.StatusService;
import com.intuit.tms.dto.StatusDTO;
import com.intuit.tms.dto.StatusUpdateDTO;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	StatusRepository statusRepository;

	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Autowired
	AccountTeamMapRepository accountTeamMapRepository;

	@Override
	public Status getStatusByStatus(String strStatus) {
		Optional<Status> status = statusRepository.findByStatus(strStatus);
		if (status.isPresent()) {
			return status.get();
		}
		return null;
	}

	@Override
	public Status saveStatus(StatusDTO statusDTO) {
		Long createdBy = customUserDetailsService.getCurrentLoggedInUserId();
		Status status = new Status();
		status.setStatus(statusDTO.getStatus());
		status.setDescription(statusDTO.getDescription());
		status.setCreatedBy(createdBy);
		return statusRepository.save(status);
	}

	@Override
	public List<Status> getAllStatuses() {
		return statusRepository.findAll();
	}

	@Override
	public void deleteStatus(String status) {
		statusRepository.deleteByStatus(status);

	}

	@Override
	public Status updateStatus(StatusUpdateDTO statusUpdateDTO, String status) {
		Long updatedBy = customUserDetailsService.getCurrentLoggedInUserId();
		Status updatedStatus = new Status();
		updatedStatus.setStatus(status);
		updatedStatus.setDescription(statusUpdateDTO.getDescription());
		updatedStatus.setUpdatedBy(updatedBy);
		updatedStatus.setUpdatedOn(LocalDateTime.now());
		return statusRepository.save(updatedStatus);
	}
}
