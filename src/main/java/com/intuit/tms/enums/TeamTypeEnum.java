package com.intuit.tms.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum TeamTypeEnum {

	QA("QA Team"),

	DEV("Development Team"),

	SALES("Sales Team"),

	CUSTOMER_CARE("Customer Care Team");

	private String value;

	private TeamTypeEnum(String val) {
		this.value = val;
	}

	public String getValue() {
		return value;
	}

	public static List<String> getList() {
		List<String> teamTypeList = new ArrayList<String>();
		for (TeamTypeEnum item : TeamTypeEnum.values()) {
			teamTypeList.add(item.name());
		}
		return teamTypeList;
	}

	public static Map<String, String> getMap() {
		Map<String, String> hashMap = new HashMap<String, String>();
		for (TeamTypeEnum item : TeamTypeEnum.values()) {
			hashMap.put(item.getValue(), item.name());
		}
		return hashMap;
	}

	public static TeamTypeEnum getByValue(String value) {
		TeamTypeEnum result = TeamTypeEnum.DEV;
		for (TeamTypeEnum item : TeamTypeEnum.values()) {
			if (item.getValue().equals(value)) {
				result = item;
				break;
			}
		}
		return result;
	}
}