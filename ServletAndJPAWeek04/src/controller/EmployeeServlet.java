package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import model.Employee;

@WebServlet("/employeeController")
public class EmployeeServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		EmployeeDAO employeeDAO=new EmployeeDAOImpl();
		
		//a�a��daki �ekilde kodlar �al���r ama validasyon yap�lmad�g� i�in yanl��t�r. Veri �nce validasyon yap�lmal� sonra eklenmelidir.
		String empId=req.getParameter("employeeId");            // Delete linkine bast�g�m�zda g�nderilen parametre
		
		if(empId != null) {                                    //empId null degil ise silme yap       else        ise   ekleme yap
			employeeDAO.removeEmployee(Integer.parseInt(empId));
		}else {
			String name =req.getParameter("name");
			String surname =req.getParameter("surname");
			int salary =Integer.parseInt(req.getParameter("salary"));
			
			Employee employee=new Employee(name,surname,salary);
			employeeDAO.insertEmployee(employee);
		}
		List<Employee> employeeList=employeeDAO.findAllEmployees();
		
		req.setAttribute("allEmployees", employeeList);
		
		RequestDispatcher requestDispatcher =req.getRequestDispatcher("employee.jsp");
		requestDispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,resp);
	}
}
