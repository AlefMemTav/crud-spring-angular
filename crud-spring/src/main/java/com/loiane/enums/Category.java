package com.loiane.enums;

public enum Category {
	BACKEND("Back-end"), FROTEND("Front-end");
	
	private String value;

	private Category(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}

}
