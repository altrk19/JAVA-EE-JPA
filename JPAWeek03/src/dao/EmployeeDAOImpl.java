package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Department;
import model.Employee;
import model.NickName;
import model.ParkingSpace;
import model.Phone;
import model.Project;

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

	@Override
	public void insertProject(Project project) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(project);
		transaction.commit();
		
	}

	@Override
	public void insertPhone(Phone phone) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(phone);
		transaction.commit();
		
	}

	@Override
	public void updateNickName(Employee employee ,NickName nickName) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		employee.setNickName(nickName);
		//entityManager.persist(nickName);        nickname entity olmad�g� i�in persist yapam�yorum. update i�lemi yap�yorum transactionlar ile
		transaction.commit();
		
	}

	@Override
	public void updateEmails(Employee employee, String email) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		employee.getEmails().add(email);                              //Employee'un email hesaplar� eklenecek. email listesi �ekildi ve eklendi.
		transaction.commit();
		
	}

}
