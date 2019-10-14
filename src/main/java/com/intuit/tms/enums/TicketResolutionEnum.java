package com.intuit.tms.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum TicketResolutionEnum {

	FIXED("Fixed"),

	WONT_FIX("Won't Fix"),

	DUPLICATE("Duplicate"),

	NOT_A_BUG("Not a Bug"),

	CANNOT_REPRODUCE("Cannot Reproduce"),

	DONE("Done"),

	OBSELETE("Obsolete"),

	ENVIRONMENT_ISSUE("Environment Issue"),

	DATA_FIX("Data Fix"),

	INCOMPLETE("Incomplete"),

	THIRD_PARTY("Third Party"),

	NO_RESOLUTION("No Resolution"),

	WONT_DO("Won't Do"),

	DECLINED("Declined");

	private String value;

	private TicketResolutionEnum(String val) {
		this.value = val;
	}

	public String getValue() {
		return value;
	}

	public static List<String> getList() {
		List<String> ticketResolutionList = new ArrayList<String>();
		for (TicketResolutionEnum item : TicketResolutionEnum.values()) {
			ticketResolutionList.add(item.name());
		}
		return ticketResolutionList;
	}

	public static Map<String, String> getMap() {
		Map<String, String> hashMap = new HashMap<String, String>();
		for (TicketResolutionEnum item : TicketResolutionEnum.values()) {
			hashMap.put(item.getValue(), item.name());
		}
		return hashMap;
	}

	public static TicketResolutionEnum getByValue(String value) {
		TicketResolutionEnum result = TicketResolutionEnum.NO_RESOLUTION;
		for (TicketResolutionEnum item : TicketResolutionEnum.values()) {
			if (item.getValue().equals(value)) {
				result = item;
				break;
			}
		}
		return result;
	}
}