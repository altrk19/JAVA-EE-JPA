package test;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//import org.omg.Messaging.SyncScopeHelper;

import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import model.Address;
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
		Employee employee2 = new Employee("abdullah", "dagci", 8000);
		Employee employee3 = new Employee("yilmaz", "erdogan", 7000);

		
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
		
		
		
		
		
		
		Address adress1=new Address("Street1","Corum","Ic","19000");
		Address adress2=new Address("Street2","Corum","Dogu","25000");
		Address adress3=new Address("Street3","Denizli","Ege","20000");
	
		employeeDAO.insertAdress(adress1);                      //veritabanýna ekler
		employeeDAO.insertAdress(adress2);  
		employeeDAO.insertAdress(adress3);  
		
		employee1.setAddress(adress1);
		employee2.setAddress(adress2);
		employee3.setAddress(adress3);
		
		employeeDAO.insertEmployee(employee1);
		employeeDAO.insertEmployee(employee2);
		employeeDAO.insertEmployee(employee3);
		
		
		/* ------------------JPQL----------------------*/
		
		employeeDAO.queryBetween();                    //between sorgusu
		 
		employeeDAO.queryLike();						//like sorgusu       adý al ile baþlayanlar
		 
		employeeDAO.queryIn();							//In sorgusu Join Kullanýmý ile
		
		employeeDAO.queryIn2();							//In sorgusu
		
		employeeDAO.querySum();                        //Sum sorgusu
		
		employeeDAO.queryMax();                        //Max sorgusu
		
		employeeDAO.queryCount();                        //Count sorgusu
		
		employeeDAO.queryAvg();                        //AVG sorgusu
		
		employeeDAO.queryGroupBy();                    //GroupBy sorgusu
		
		employeeDAO.queryGroupBy2();                     //Join ile kullanýmý.    Çalýþanlarýn illerinin maaþlarýnýn ortalamasý
		
		employeeDAO.queryGroupByHaving();               //GroupByHaving sorgusu
		
		employeeDAO.queryOrderBy(); 					//OrderBy sorgusu
		
	
		
		
		/* ------------------Criteria  API -------------------------------*/
	
		employeeDAO.queryCriteriaSelect();				//Criteria Select sorgusu
		
		employeeDAO.queryCriteriaSelect2();				//Criteria Select sorgusu - Name bilgisi sadece
		
		employeeDAO.queryCriteriaSelectMultiple();      //Criteria Select Birden Fazla Alan. Tuple kullanýldý.
		
		employeeDAO.queryCriteriaJoin();               //Criteria Join - ismini verdigimiz employee'nin department bilgisi döner
		
		employeeDAO.queryCriteriaGreaterThan();       //Criteria greaterThan - Maaþý 6000 den yüksek olanlarý getirir
		
		employeeDAO.queryCriteriaBetween();           //Criteria between  - Maaþý 4500-5500 arasýnda olanlarý döndürür
	}
}



