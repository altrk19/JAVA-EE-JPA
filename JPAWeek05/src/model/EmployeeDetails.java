package model;

public class EmployeeDetails {
	private String name;
	private String surname;
	private String departmantName;
	
	
	public EmployeeDetails() {
		super();
	}

	public EmployeeDetails(String name, String surname, String departmantName) {
		super();
		this.name = name;
		this.surname = surname;
		this.departmantName = departmantName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}

	
	public String getDepartmantName() {
		return departmantName;
	}


	public void setDepartmantName(String departmantName) {
		this.departmantName = departmantName;
	}

	
	@Override
	public String toString() {
		return "EmployeeDetails [name=" + name + ", surname=" + surname + ", departmantName=" + departmantName + "]";
	}
	
	
	
}
