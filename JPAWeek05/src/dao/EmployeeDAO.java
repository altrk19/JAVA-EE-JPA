package dao;

import java.util.List;

import model.Address;
import model.Department;
import model.Employee;
import model.EmployeeDetails;
import model.ParkingSpace;


public interface EmployeeDAO {

	public void insertEmployee(Employee employee);

	public Employee getEmployeeById(int id);

	public List<Employee> getEmployees();
	
	public List<String> getEmployeeNames();
	
	public String getEmployeeNameById(int id);
	
	public List<Object[]> getEmployeeNameAndSurname();
	
	public List<EmployeeDetails> getEmployeeConstructorExpression();
	
	public void deleteEmployee(int id);

	public void raiseSalary(int id, int raise);	
	
	public void insertDeparment(Department department);
	
	public void insertParkingSpace(ParkingSpace parkingSpace);
	
	public List<Employee> queryBetween();
	
	public List<Employee> queryLike();
	
	public void insertAdress(Address adress);
	
	public List<Employee> queryIn();
	
	public List<Employee> queryIn2();
	
	public void querySum();
	
	public void queryMax();
	
	public void queryCount();
	
	public void queryAvg();
	
	public List<Object[]> queryGroupBy();
	
	public List<Object[]> queryGroupBy2();
	
	public List<Object[]> queryGroupByHaving();
	
	public void queryOrderBy();
	
	public void queryCriteriaSelect();
	
	public void queryCriteriaSelect2();
	
	public void queryCriteriaSelectMultiple();
	
	public void queryCriteriaJoin();
	
	public void queryCriteriaGreaterThan();
	
	public void queryCriteriaBetween();
}
