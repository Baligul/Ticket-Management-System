package com.intuit.tms.services;

import com.intuit.tms.entities.Status;

import java.util.List;

import com.intuit.tms.dto.StatusDTO;

public interface StatusService {

	Status getStatusById(Long id);

	Status saveStatus(StatusDTO statusDTO);

	List<Status> getAllStatuses();

	void deleteStatus(Long id);

	Status updateStatus(StatusDTO statusDTO, Long statusId);

	Status getStatusByStatus(String strStatus);
}