package com.intuit.tms.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum TicketPriorityEnum {

	IMMEDIATE("Immediate"),

	HIGH("High"),

	MEDIUM("Medium"),

	LOW("Low");

	private String value;

	private TicketPriorityEnum(String val) {
		this.value = val;
	}

	public String getValue() {
		return value;
	}

	public static List<String> getList() {
		List<String> ticketPriorityList = new ArrayList<String>();
		for (TicketPriorityEnum item : TicketPriorityEnum.values()) {
			ticketPriorityList.add(item.name());
		}
		return ticketPriorityList;
	}

	public static Map<String, String> getMap() {
		Map<String, String> hashMap = new HashMap<String, String>();
		for (TicketPriorityEnum item : TicketPriorityEnum.values()) {
			hashMap.put(item.getValue(), item.name());
		}
		return hashMap;
	}

	public static TicketPriorityEnum getByValue(String value) {
		TicketPriorityEnum result = TicketPriorityEnum.MEDIUM;
		for (TicketPriorityEnum item : TicketPriorityEnum.values()) {
			if (item.getValue().equals(value)) {
				result = item;
				break;
			}
		}
		return result;
	}
}