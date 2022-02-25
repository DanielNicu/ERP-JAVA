package p3;
/**
 * 
 * the employees that work in the company
 *
 */
public class Employee {
	int id;
	String role;
	String name;
	int deptno;
	int sal;
	String email;
	int vacancy;
	/**
	 * 
	 * @param id the unique id of the employee
	 * @param role the role of the employee in the department
	 * @param name the name of the employee
	 * @param deptno the department number of the employee
	 * @param sal the salary of the employee
	 * @param email the email of the employee
	 * @param vacancy 0 if the employee is working an 1 if he is in vacation
	 */
	
	public Employee(int id, String role, String name, int deptno, int sal, String email, int vacancy) {
		super();
		this.id = id;
		this.role = role;
		this.name = name;
		this.deptno = deptno;
		this.sal = sal;
		this.email = email;
		this.vacancy = vacancy;
	}
	/**
	 * 
	 * @return the id of the employee
	 */

	public int getId() {
		return id;
	}
	/**
	 * 
	 * @param id the id of the employee
	 */

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return the role of the employee
	 */
	public String getRole() {
		return role;
	}
	/**
	 * 
	 * @param role the role of the employee
	 */

	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * 
	 * @return the name of the employee
	 */

	public String getName() {
		return name;
	}
	/**
	 * 
	 * @param name the name of the employee
	 */

	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @return the name of the employee
	 */

	public int getDeptno() {
		return deptno;
	}
	/**
	 * 
	 * @param deptno the department number of the employee
	 */

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	/**
	 * 
	 * @return the department number of the employee
	 */

	public int getSal() {
		return sal;
	}
	/**
	 * 
	 * @param sal the salary of the employee
	 */

	public void setSal(int sal) {
		this.sal = sal;
	}
	/**
	 * 
	 * @return the salary of the employee
	 */

	public String getEmail() {
		return email;
	}
	/**
	 * 
	 * @param email the email of the employee
	 */

	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 
	 * @return the current status of the employee
	 */

	public int isVacancy() {
		return vacancy;
	}
	/**
	 * 
	 * @param vacancy the current status of the employee
	 */

	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}


}
