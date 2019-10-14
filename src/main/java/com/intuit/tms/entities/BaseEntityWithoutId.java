package com.intuit.tms.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MappedSuperclass
public class BaseEntityWithoutId {
	private static final Logger log = LoggerFactory.getLogger(BaseEntityWithoutId.class);

	@Column(name = "created_on", updatable = false)
	private LocalDateTime createdOn;

	@Transient
	private int createdYear;

	@Transient
	private int createdMonth;

	@Transient
	private int createdDay;

	@Transient
	private String createdStrMonth;

	@Transient
	private String createdOnFormatted;

	@PrePersist
	private void onCreate() {
		setCreatedOn(LocalDateTime.now());
	}

	@PostLoad
	private void onLoad() {
		this.createdYear = createdOn.getYear();
		this.createdMonth = createdOn.getMonthValue();
		this.createdDay = createdOn.getDayOfMonth();
		this.createdStrMonth = createdOn.getMonth().toString();
		this.createdOnFormatted = this.createdStrMonth + " " + this.createdDay + ", " + this.createdYear;
	}

	// Getters and Setters
	// ===================

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public static Logger getLog() {
		return log;
	}
}
