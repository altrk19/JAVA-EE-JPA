package dao;

import java.util.List;

import model.Department;
import model.Employee;
import model.ParkingSpace;

public interface EmployeeDAO {

	public void insertEmployee(Employee employee);

	public Employee getEmployeeById(int id);

	public List<Employee> getEmployees();

	public void deleteEmployee(int id);

	public void raiseSalary(int id, int raise);	
	
	public void insertDeparment(Department department);
	
	public void insertParkingSpace(ParkingSpace parkingSpace);
}
