package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
public class Department {

	@Id
	//@GeneratedValue(strategy = GenerationType.TABLE)
	
	@TableGenerator(name="DEPT_GEN")
	@GeneratedValue(generator="DEPT_GEN")
	private int id;

	private String name;

	public Department() {
		//No-arg constructor GEREKLI!
		super();
	}

	public Department(String name) {
		super();
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
