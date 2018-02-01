package test;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import model.Department;
import model.Employee;
import model.EmployeeType;
import model.ParkingSpace;
import model.Phone;
import model.Project;

public class EmployeeJPATest {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmployeePersistenceUnit");
		// EntityManagerFactory entityManagerFactory =
		// Persistence.createEntityManagerFactory("EmployeePersistenceUnitForHibernate");
		// javax.persistence.Persistence sinifi yardimiyla EntityManagerFactory
		// olusturabiliriz.
		// Bunun icin persistence unit bilgisini kullaniriz (persistence.xml)

		// EntityManagerFactoryden N tane EntityManager olusturabiliriz.
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		EmployeeDAO employeeDAO = new EmployeeDAOImpl(entityManager);

		Employee employee1 = new Employee("ali", "bey", 5000);
		Employee employee2 = new Employee("abdullah", "dagci", 5900);
		Employee employee3 = new Employee("yilmaz", "erdogan", 1500);

		
		employee1.setEmployeeType(EmployeeType.FULL_TIME);										// @Enumerated
		employee2.setEmployeeType(EmployeeType.PART_TIME);
		employee3.setEmployeeType(EmployeeType.CONTRACTED_EMPLOYEE);

		
		LocalDate date1 = LocalDate.of(2017, Month.AUGUST, 1);									//@Temporal
		Date employeeStartDate1 = Date.valueOf(date1);

		LocalDate date2 = LocalDate.of(2016, Month.JULY, 12);
		Date employeeStartDate2 = Date.valueOf(date2);

		LocalDate date3 = LocalDate.of(2014, Month.AUGUST, 28);
		Date employeeStartDate3 = Date.valueOf(date3);

		employee1.setStartDate(employeeStartDate1);
		employee2.setStartDate(employeeStartDate2);
		employee3.setStartDate(employeeStartDate3);

		byte[] image1 = "image1.jpg".getBytes();											//@Lob
		byte[] image2 = "image2.jpg".getBytes();
		byte[] image3 = "image3.jpg".getBytes();

		
		employee1.setPicture(image1);
		employee2.setPicture(image2);
		employee3.setPicture(image3);

		
		Department department = new Department("IT Deparment");							//@ManyToOne

		employeeDAO.insertDeparment(department);

		employee1.setDepartment(department);
		employee2.setDepartment(department);
		employee3.setDepartment(department);

		

		ParkingSpace ps1 = new ParkingSpace(3, "A25");								//@OneToOne 
		ParkingSpace ps2 = new ParkingSpace(2, "C30");
		ParkingSpace ps3 = new ParkingSpace(-2, "D35");

		
		employeeDAO.insertParkingSpace(ps1);                       //veritabanýna ekler
		employeeDAO.insertParkingSpace(ps2);
		employeeDAO.insertParkingSpace(ps3);


		employee1.setParkingSpace(ps1);						 //employee1'in parking alanýný setler
		employee2.setParkingSpace(ps2);
		employee3.setParkingSpace(ps3);
		
		Project project1=new Project("WhatsApp Project");
		Project project2=new Project("Hangout Project");
		Project project3=new Project("Skype Project");
		Project project4=new Project("HabCam Project");
		
		employeeDAO.insertProject(project1);				//veritabanýna ekledik
		employeeDAO.insertProject(project2);
		employeeDAO.insertProject(project3);
		employeeDAO.insertProject(project4);
		
		List <Project> employee1Projects=new ArrayList <Project>();              //employee1'in projeleri eklendi ve setlendi
		employee1Projects.add(project1);
		employee1Projects.add(project2);
		employee1Projects.add(project3);
		employee1.setProjects(employee1Projects);
		
		Phone phone1=new Phone("111","HOME");
		Phone phone2=new Phone("222","WORK");
		
		Phone phone3=new Phone("333","HOME");
		Phone phone4=new Phone("444","MOBILE");
		
		employeeDAO.insertPhone(phone1);						//veritabanýna eklendi.
		employeeDAO.insertPhone(phone2);
		employeeDAO.insertPhone(phone3);
		employeeDAO.insertPhone(phone4);
		
		List<Phone> employee1Phones =new ArrayList<>();
		employee1Phones.add(phone1);
		employee1Phones.add(phone2);							//employee1'in telefonlarý eklendi ve setlendi
		employee1.setPhones(employee1Phones);
		
		
		List<Phone> employee2Phones =new ArrayList<>();
		employee2Phones.add(phone3);
		employee2Phones.add(phone4);						//employee2'in telefonlarý eklendi ve setlendi
		employee2.setPhones(employee2Phones);
		
		
		employeeDAO.insertEmployee(employee1);
		employeeDAO.insertEmployee(employee2);
		employeeDAO.insertEmployee(employee3);

	}
}

//telefon ve proje  joincolumn

