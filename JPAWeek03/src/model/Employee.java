package model;

import java.util.Date;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EmployeeTable")
// Varsayilan olarak tablo ismi ile sinif ismi ayni olacaktir.
// Tablo ismini degistirmek icin @Table annotation'inindan yararlarniriz.
public class Employee {

	@Id
	@TableGenerator(name = "EMP_GEN")
	@GeneratedValue(generator = "EMP_GEN")
	@Column(name = "emp_id")
	private int id;

	@Column(name = "emp_name")
	private String name;
	private String surname;
	private int salary;

	//
	@Enumerated(EnumType.STRING)
	// varsayilan olarak ORDINAL dir.
	private EmployeeType employeeType;

	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Lob
	private byte[] picture;

	
	
	
	// Many --> Employee
	// One -> Department
	@ManyToOne
	@JoinColumn(name = "emp_dept_id")

	// ManyToOne
	// OneToOne iliskilere single-valued relationship adi verilir.
	// hedef/target cardinality One oldugunda.
	// kolon ismini degistirebiliriz
	
	
	
	
	
	private Department department;

	@OneToOne
	//Hibernate JPA implemantasyonu icin unique olmazsa HATA veriyor.
	//EclipseLink implementasyonu icin unique olmazsa hata yok.
	@JoinColumn(name="ps_id" ,unique=true)
	private ParkingSpace parkingSpace;

	
	
	@ManyToMany
	@JoinTable(name="EMP_PROJE",
		joinColumns=@JoinColumn(name="EMP_ID"),
		inverseJoinColumns=@JoinColumn(name="PROJE_ID"))                    //oluþan yeni tablonun adýnýn epm_proje olmasýný saðladýk ve kolonlarýn adýný degistirdik.
	private List <Project> projects;
	
	
	
	@OneToMany
	@JoinTable(name = "EMP_PHONE", 
		joinColumns = @JoinColumn(name = "EMP_ID"), 
		inverseJoinColumns = @JoinColumn(name = "PHN_ID"))
	private List <Phone> phones;
	
	
	
	// default constructor gerekli!
	public Employee() {
		super();
	}


	public Employee(String name, String surname, int salary) {
		super();
		this.name = name;
		this.surname = surname;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public ParkingSpace getParkingSpace() {
		return parkingSpace;
	}

	public void setParkingSpace(ParkingSpace parkingSpace) {
		this.parkingSpace = parkingSpace;
	}
	

	public List<Project> getProjects() {
		return projects;
	}


	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}


	public List<Phone> getPhones() {
		return phones;
	}


	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", surname=" + surname + ", salary=" + salary
				+ ", employeeType=" + employeeType + "]";
	}

}
