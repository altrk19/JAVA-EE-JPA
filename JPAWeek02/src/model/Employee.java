package model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EmployeeTableHibernate")
// Varsayilan olarak tablo ismi ile sinif ismi ayni olacaktir.
// Tablo ismini degistirmek icin @Table annotation'inindan yararlarniriz.
public class Employee {

	
	// Annotation'lari property/instance variable uzerine koyabiliriz.
	// bununla birlikte getter metotlar uzerine de koyabiliriz.
	// setter uzerine koymak etkisizdir.

	// Column/kolon isimlerini degistirmek icin @Column annotation'ini
	// kullaniriz.

	// Primary key uretmek icin , @GeneratedValue annotation'ini kullanabiliriz.
	// AUTO
	// TABLE
	// SEQUENCE
	// IDENTITY
	// @GeneratedValue(strategy=GenerationType.AUTO)

	// @GeneratedValue(strategy=GenerationType.TABLE)

	////@TableGenerator(name = "EMP_GEN")
	////@GeneratedValue(generator = "EMP_GEN")

	// @TableGenerator(name="EMP_GEN_DETAILED",
	// table="KEY_GEN",
	// pkColumnName="ID_NAME",
	// valueColumnName="COUNT",
	// initialValue = 200
	// )
	// @GeneratedValue(generator="EMP_GEN_DETAILED")

	// create sequence sequenceName;
	// TEST Oracle!
	// @SequenceGenerator(name="generatorName" , sequenceName="sequenceName")
	// @GeneratedValue(generator="generatorName")
	// @GeneratedValue(strategy=GenerationType.SEQUENCE)
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	//

	@Lob
	private byte[] picture;

	// Many --> Employee
	// One -> Department



	// default constructor gerekli!
	public Employee() {
		super();
	}

	// public Employee(int id, String name, String surname, int salary) {
	// super();
	// this.id = id;
	// this.name = name;
	// this.surname = surname;
	// this.salary = salary;
	// }

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



	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", surname=" + surname + ", salary=" + salary
				+ ", employeeType=" + employeeType + "]";
	}

}
