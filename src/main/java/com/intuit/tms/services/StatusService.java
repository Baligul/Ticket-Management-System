package com.intuit.tms.services;

import com.intuit.tms.entities.Status;

import java.util.List;

import com.intuit.tms.dto.StatusDTO;
import com.intuit.tms.dto.StatusUpdateDTO;

public interface StatusService {

	Status saveStatus(StatusDTO statusDTO);

	List<Status> getAllStatuses();

	void deleteStatus(String status);

	Status updateStatus(StatusUpdateDTO statusUpdateDTO, String status);

	Status getStatusByStatus(String strStatus);
}