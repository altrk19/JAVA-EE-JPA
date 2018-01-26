package _02.ornek.dao;

import java.util.List;

import _02.ornek.model.Employee;



public interface EmployeeDAO {

	public void insertEmployee(Employee employee);

	public Employee getEmployeeById(int id);

	public List<Employee> getEmployees();

	public void deleteEmployee(int id);

	public void raiseSalary(int id, int raise);
}
