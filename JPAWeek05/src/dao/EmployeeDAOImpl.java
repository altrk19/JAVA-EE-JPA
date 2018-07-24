package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.Address;
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
	public List<String> getEmployeeNames() {                                                                              //tüm isimlerin olduðu tek bir liste döner
		TypedQuery<String> query = entityManager.createQuery("Select e.name from Employee e", String.class);
		return query.getResultList();
	}
	
	public String getEmployeeNameById(int id) {
		//1. yaklasým
		//TypedQuery<String> query = entityManager.createQuery("Select e.name from Employee e where e.id=?1", String.class).setParameter(1, id);     
		
		//2. yaklasým
		TypedQuery<String> query = entityManager.createQuery("Select e.name from Employee e where e.id=:empId", String.class).setParameter("empId", id);
		return query.getSingleResult();
	}
	@Override
	public List<Object[]> getEmployeeNameAndSurname(){
		TypedQuery<Object[]> query = entityManager.createQuery("Select e.name,e.surname from Employee e ", Object[].class);
		return query.getResultList();
	}
	
	
	@Override																																								//ÖNEMLÝ!!
	public List<EmployeeDetails> getEmployeeConstructorExpression(){
		TypedQuery<EmployeeDetails> query = entityManager.createQuery("Select New model.EmployeeDetails(e.name,e.surname,e.department.name) from Employee e",EmployeeDetails.class);           //Normalde SQL'de JOIN kullanrak yapmamýz gerekiyordu. JPQL ile objeye sorgu atýyoruz. Arkadaki iþlemleri JPA hallediyor..
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
	public void insertAdress(Address adress ) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(adress);
		transaction.commit();
	}

	@Override
	public List<Employee> queryBetween(){
		TypedQuery<Employee> query=entityManager
				.createQuery("Select e from Employee e where e.salary BETWEEN ?1 and ?2",Employee.class)
				.setParameter(1, 4000)
				.setParameter(2, 5000);
				;
		List<Employee> emplist=query.getResultList();
		System.out.println();
		System.out.println("queryEmployee BETWEEN 4000-5000");
		emplist.forEach(System.out::println);
		System.out.println();
		return emplist;
	}
	
	

	@Override
	public List<Employee> queryLike(){
		TypedQuery<Employee> query=entityManager
				.createQuery("Select e from Employee e where e.name LIKE 'al%' ",Employee.class);
		
				
		List<Employee> emplist=query.getResultList();
		System.out.println("queryEmployee LIKE");
		emplist.forEach(System.out::println);
		System.out.println();
		return emplist;
	}
	
	@Override
	public List<Employee> queryIn(){   //Join Ýþlemi var
		TypedQuery<Employee> query = entityManager.createQuery("Select e from Employee e where e.address.city IN('Corum','Izmir') ", Employee.class);          
		//SQL karþýlýgý
		//SELECT * FROM EmployeeTable e JOIN Address a on e.emp_id = a.id where a.city in('Istanbul','Izmir');
		List<Employee> emplist = query.getResultList();
		System.out.println("queryEmployee IN");
		emplist.forEach(System.out::println);
		System.out.println();
		return emplist;
	}
	
	@Override
	public List<Employee> queryIn2(){ 
		TypedQuery<Employee> query = entityManager.createQuery("Select e from Employee e where e.name IN('ali','abdullah') ",Employee.class);
		List<Employee> emplist = query.getResultList();
		System.out.println("queryEmployee IN 2. sorgu");
		emplist.forEach(System.out::println);
		System.out.println();
		return emplist;
	}

	@Override
	public void querySum() {
		TypedQuery<Long> query = entityManager.createQuery("Select SUM(e.salary) from Employee e",Long.class);
		Long result = query.getSingleResult();
		System.out.print("queryEmployee SUM\nCalisanlarin toplam maasi:");
		System.out.println(result);
		System.out.println();
	}

	@Override
	public void queryMax() {
		TypedQuery<Integer> query = entityManager.createQuery("Select MAX(e.salary) from Employee e",Integer.class);
		Integer result = query.getSingleResult();
		System.out.print("queryEmployee MAX\nEn yuksek maas:");
		System.out.println(result);
		System.out.println();
		
	}

	@Override
	public void queryCount() {
		TypedQuery<Long> query = entityManager.createQuery("Select COUNT(e.id) from Employee e",Long.class);
		Long result = query.getSingleResult();
		System.out.print("queryEmployee COUNT\nCalisan sayisi:");
		System.out.println(result);
		System.out.println();
		
	}

	@Override
	public void queryAvg() {
		TypedQuery<Double> query = entityManager.createQuery("Select AVG(e.salary) from Employee e",Double.class);
		Double result = query.getSingleResult();
		System.out.print("queryEmployee AVG\nMaaslarin ortalamasi:");
		System.out.println(result);
		System.out.println();
		
	}

	@Override
	public List<Object[]> queryGroupBy() {
		TypedQuery<Object[]> query=entityManager
				.createQuery("Select e.salary,COUNT(e) from Employee e GROUP BY e.salary ",Object[].class);
		
		System.out.println("Maaslara gore gruplama");  //maaþ ve kaç kiþi alýyor bilgisi		
		List<Object[]> emplist=query.getResultList();
		for(Object[] element : emplist) {
			System.out.println(element[0]+","+element[1]);
		}
		System.out.println();
		return emplist;
		
	}
	
	@Override
	public List<Object[]> queryGroupBy2() {          //JOIN Ýþlemi ile GROUP BY
		TypedQuery<Object[]> query=entityManager
				.createQuery("Select e.address.city,AVG(e.salary) from Employee e GROUP BY e.address.city ",Object[].class);        
		
		System.out.println("Maaslara gore gruplama");  //maaþ ve kaç kiþi alýyor bilgisi		
		List<Object[]> emplist=query.getResultList();
		for(Object[] element : emplist) {
			System.out.println(element[0]+","+element[1]);
		}
		System.out.println();
		return emplist;
		  
		 
	}

	@Override
	public List<Object[]> queryGroupByHaving() {
		TypedQuery<Object[]> query=entityManager
				.createQuery("Select e.salary,COUNT(e) from Employee e GROUP BY e.salary HAVING AVG(e.salary)>6000 ",Object[].class);
		
		System.out.println("Maaslara gore grupla ve 6000 den buyuk olanlarý getir");  //maaþ ve kaç kiþi alýyor bilgisi		
		List<Object[]> emplist=query.getResultList();
		for(Object[] element : emplist) {
			System.out.println(element[0]+","+element[1]);
		}
		System.out.println();
		return emplist;
	}

	@Override
	public void queryOrderBy() {
		TypedQuery<Integer> query=entityManager
				.createQuery("Select e.salary from Employee e ORDER BY e.salary DESC ",Integer.class);        //artan için default(birþey yazma) veya ASC
		
		System.out.println("Maaslarý buyukten kucuge sirala");  //maaþ ve kaç kiþi alýyor bilgisi		
		List<Integer> emplist=query.getResultList();
		emplist.forEach(System.out::println);//veya
		/*for(Integer element : emplist) {
		System.out.println(element);
		}
		 */
		System.out.println();
	}

	
	
	/* ------------------Criteria  API -------------------------------*/
	
	@Override
	public void queryCriteriaSelect() { //Bu kod JPQL de Select e Employee e    ile aynýdýr.
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Employee> query = builder.createQuery(Employee.class);                  //CriteriaQuery<Employee>  sorgudan Employee dönecek
	    Root<Employee> root = query.from(Employee.class);									  //Root<Employee>   sorgu Employee'a yapýlacak
	    query.select(root); 
	    TypedQuery<Employee> typedQ = entityManager.createQuery(query); 
	    System.out.println("\nCriteria API Query Select");
	    typedQ.getResultList().forEach(System.out::println);                               //birden fazla deger ya da nesne döndürülürken böyle yazdýrýlýr.
		
	}

	@Override
	public void queryCriteriaSelect2() {         //Employee'larýn sadece ad bilgisini getirir.
		CriteriaBuilder builder = entityManager.getCriteriaBuilder(); 
	    CriteriaQuery<String> query = builder.createQuery(String.class);                  // CriteriaQuery<String>  sorgudan String dönecek              
	    Root<Employee> root = query.from(Employee.class);
	    query.select(root.get("name")); 
	    TypedQuery<String> typedQ = entityManager.createQuery(query); 
	    System.out.println("\nCriteria API Query Select - Name Bilgisi");
	    typedQ.getResultList().forEach(System.out::println);
	}

	@Override
	public void queryCriteriaSelectMultiple() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Tuple> query = builder.createQuery(Tuple.class); 					// CriteriaQuery<Tuple>  sorgudan Tuple veri tipi dönecek
	    Root<Employee> root = query.from(Employee.class);
	    query.select(builder.tuple(root.get("name"),root.get("salary"))); 
	    
	    TypedQuery<Tuple> typedQ = entityManager.createQuery(query); 
	    System.out.println("\nCriteria API Query Select Multiple with Tuple");
	    List<Tuple> tupleList=typedQ.getResultList();
	    for(Tuple t:tupleList) {
	    	System.out.println(t.get(0)+","+t.get(1));
	    }
		
	}

	@Override
	public void queryCriteriaJoin() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<String> query = builder.createQuery(String.class);
	    Root<Employee> root = query.from(Employee.class);
	    query.select(
	    		root.get("department").get("name")).where(
	    		builder.equal(root.get("name"), "ali"));                                  //adý ali olan çalýþanýn departman adýný döndürür.
	    
	    TypedQuery<String> typedQ = entityManager.createQuery(query); 
	    System.out.println("\nCriteria API Query Join ");
	    System.out.println(typedQ.getSingleResult());                                        //tek deger döndürülürken böyle yazdýrýlýr
	}

	@Override
	public void queryCriteriaGreaterThan() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
	    Root<Employee> root = query.from(Employee.class);
	    query.select(root).where(builder.greaterThan(root.get("salary"), 6000));
	    
	    TypedQuery<Employee> typedQ = entityManager.createQuery(query); 
	    System.out.println("\nCriteria API Query greaterThan ");
	    typedQ.getResultList().forEach(System.out::println);
		
	}

	@Override
	public void queryCriteriaBetween() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
	    Root<Employee> root = query.from(Employee.class);
	    query.select(root).where(builder.between(root.get("salary"),
	    		builder.parameter(Integer.class,"lowSalary"),
	    		builder.parameter(Integer.class,"highSalary")
	    		));
	    		
	    
	    TypedQuery<Employee> typedQ = entityManager.createQuery(query)
	    		.setParameter("lowSalary", 4500).
	    		setParameter("highSalary", 5500);
	    System.out.println("\nCriteria API Query between ");
	    typedQ.getResultList().forEach(System.out::println);
		
	}



}
