package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Department;
import model.Employee;
import model.EmployeeDetails;
import model.ParkingSpace;


public class EmployeeDAOImpl implements EmployeeDAO {

	private EntityManager entityManager;

	public EmployeeDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void insertEmployee(Employee employee) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(employee);
		transaction.commit();

	}

	@Override
	public Employee getEmployeeById(int id) {
		// read islemi yaptik bu nedenle transaction.begin /commit demeyiz.
		Employee employee = entityManager.find(Employee.class, id);
		return employee;
	}

	@Override
	public List<Employee> getEmployees() {
		// Select * from Employee
		// Query query= entityManager.createQuery("Select e from Employee e");
		TypedQuery<Employee> query = entityManager.createQuery("Select e from Employee e", Employee.class);
		return query.getResultList();
	}
	
	@Override
	public List<String> getEmployeeNames() {                                                                              //t�m isimlerin oldu�u tek bir liste d�ner
		TypedQuery<String> query = entityManager.createQuery("Select e.name from Employee e", String.class);
		return query.getResultList();
	}
	
	public String getEmployeeNameById(int id) {
		//1. yaklas�m
		//TypedQuery<String> query = entityManager.createQuery("Select e.name from Employee e where e.id=?1", String.class).setParameter(1, id);     
		
		//2. yaklas�m
		TypedQuery<String> query = entityManager.createQuery("Select e.name from Employee e where e.id=:empId", String.class).setParameter("empId", id);
		return query.getSingleResult();
	}
	@Override
	public List<Object[]> getEmployeeNameAndSurname(){
		TypedQuery<Object[]> query = entityManager.createQuery("Select e.name,e.surname from Employee e ", Object[].class);
		return query.getResultList();
	}
	
	
	@Override																																								
	public List<EmployeeDetails> getEmployeeConstructorExpression(){
		TypedQuery<EmployeeDetails> query = entityManager.createQuery("Select New model.EmployeeDetails(e.name,e.surname,e.department.name) from Employee e",EmployeeDetails.class);           
		//Normalde SQL'de JOIN kullanrak yapmam�z gerekiyordu. JPQL ile objeye sorgu at�yoruz. Arkadaki i�lemleri JPA hallediyor..
		return query.getResultList();
	}
	
	
	@Override
	public void deleteEmployee(int id) {

		Employee employee = getEmployeeById(id);
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(employee);
		transaction.commit();
	}

	@Override
	public void raiseSalary(int id, int raise) {

		Employee employee = getEmployeeById(id);

		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();
		// transaction arsinda!
		employee.setSalary(employee.getSalary() + raise);
		transaction.commit();

	}

	@Override
	public void insertDeparment(Department department) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(department);
		transaction.commit();
	}

	@Override
	public void insertParkingSpace(ParkingSpace parkingSpace) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(parkingSpace);
		transaction.commit();
	}






}
