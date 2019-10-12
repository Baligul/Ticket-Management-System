package com.intuit.tms.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.intuit.tms.entities.Status;
import com.intuit.tms.repositories.AccountTeamMapRepository;
import com.intuit.tms.repositories.StatusRepository;
import com.intuit.tms.security.CustomUserDetailsService;
import com.intuit.tms.services.StatusService;
import com.intuit.tms.dto.StatusDTO;

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
	public Status getStatusById(Long statusId) {
		Optional<Status> status = statusRepository.findById(statusId);
		if (status.isPresent()) {
			return status.get();
		}
		return null;
	}

	@Override
	public Status saveStatus(StatusDTO statusDTO) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long createdBy = customUserDetailsService.getAccountIdByUserName(auth.getName());
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
	public void deleteStatus(Long statusId) {
		statusRepository.deleteById(statusId);
	}

	@Override
	public Status updateStatus(StatusDTO statusDTO, Long statusId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long updatedBy = customUserDetailsService.getAccountIdByUserName(auth.getName());
		Status status = new Status();
		status.setId(statusId);
		status.setStatus(statusDTO.getStatus());
		status.setDescription(statusDTO.getDescription());
		status.setUpdatedBy(updatedBy);
		status.setUpdatedOn(LocalDateTime.now());
		return statusRepository.save(status);
	}
}
