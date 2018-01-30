package dao;

import java.util.List;

import model.Employee;



public interface EmployeeDAO {

	public void insertEmployee(Employee employee);

	public Employee getEmployeeById(int id);

	public List<Employee> getEmployees();

	public void deleteEmployee(int id);

	public void raiseSalary(int id, int raise);
}
