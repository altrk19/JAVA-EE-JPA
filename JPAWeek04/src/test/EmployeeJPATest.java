package test;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;import org.omg.Messaging.SyncScopeHelper;

import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import model.Department;
import model.Employee;
import model.EmployeeDetails;
import model.EmployeeType;
import model.ParkingSpace;

public class EmployeeJPATest {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmployeePersistenceUnit");
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

	
		employeeDAO.insertEmployee(employee1);
		employeeDAO.insertEmployee(employee2);
		employeeDAO.insertEmployee(employee3);
		
		System.out.println("getEmployeeNames :");
		employeeDAO.getEmployeeNames().forEach(System.out::println);                    //dönen listedeki elemanlarý teker teker yazdýrýr.
		
		System.out.println("getEmployeeNameById");
		String employeeNameId2=employeeDAO.getEmployeeNameById(2);
		System.out.println(employeeNameId2);
		System.out.println();
		//isim ve soyisim'i arasýnda : ile yazdýrma iþlemi
		System.out.println("getEmployeeNameAndSurname");
		List<Object[]> nameAndSurnameArray=employeeDAO.getEmployeeNameAndSurname();        
		for(Object[] e:nameAndSurnameArray) {
			System.out.println(e[0]+" : "+e[1]);
		}
		
		System.out.println();
		
		 List<EmployeeDetails> employeeDetailsList=employeeDAO.getEmployeeConstructorExpression();
		 employeeDetailsList.forEach(System.out::println);
		
	}
}



