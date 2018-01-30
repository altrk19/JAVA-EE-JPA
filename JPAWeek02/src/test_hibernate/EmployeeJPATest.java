package test_hibernate;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.Persistence;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import model.Employee;
import model.EmployeeType;

public class EmployeeJPATest {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmployeePersistenceUnitForHibernate");
		// EntityManagerFactory entityManagerFactory =
		// Persistence.createEntityManagerFactory("EmployeePersistenceUnitForHibernate");
		// javax.persistence.Persistence sinifi yardimiyla EntityManagerFactory
		// olusturabiliriz.
		// Bunun icin persistence unit bilgisini kullaniriz (persistence.xml)

		EntityManager entityManager = entityManagerFactory.createEntityManager();

		EmployeeDAO employeeDAO = new EmployeeDAOImpl(entityManager);



		Employee employee1 = new Employee("ali", "bey", 5000);                	// @Id ve @GeneratedValue(generator = "EMP_GEN")
		Employee employee2 = new Employee("abdullah", "dagci", 5900);
		Employee employee3 = new Employee("yilmaz", "erdogan", 1500);

		
		employee1.setEmployeeType(EmployeeType.FULL_TIME);                         // @Enumerated
		employee2.setEmployeeType(EmployeeType.PART_TIME);
		employee3.setEmployeeType(EmployeeType.CONTRACTED_EMPLOYEE);

		
		LocalDate date1 = LocalDate.of(2017, Month.AUGUST, 1);
		Date employeeStartDate1 = Date.valueOf(date1);                               //@Temporal(TemporalType.DATE)

		LocalDate date2 = LocalDate.of(2016, Month.JULY, 12);
		Date employeeStartDate2 = Date.valueOf(date2);

		LocalDate date3 = LocalDate.of(2014, Month.AUGUST, 28);
		Date employeeStartDate3 = Date.valueOf(date3);

		employee1.setStartDate(employeeStartDate1);
		employee2.setStartDate(employeeStartDate2);
		employee3.setStartDate(employeeStartDate3);

		byte[] image1 = "image1.jpg".getBytes();
		byte[] image2 = "image2.jpg".getBytes();
		byte[] image3 = "image3.jpg".getBytes();

		//
		employee1.setPicture(image1);
		employee2.setPicture(image2);
		employee3.setPicture(image3);



		employeeDAO.insertEmployee(employee1);
		employeeDAO.insertEmployee(employee2);
		employeeDAO.insertEmployee(employee3);

	}
}



