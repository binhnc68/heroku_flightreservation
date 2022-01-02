package com.flightreservation.entities;

public class Department {

	private long id;
	private String departmentCode;
	private String departmentName;
	private String notes;
	private long quantity;
	
	public Department(long id, String departmentCode, String departmentName, String notes, long quantity) {
		this.id = id;
		this.departmentCode = departmentCode;
		this.departmentName = departmentName;
		this.notes = notes;
		this.quantity = quantity;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

}
