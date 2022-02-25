package p3;
/**
 * 
 * departments of the employees
 *
 */
public class Dept {
	int deptno;
	String job;
	int ordersnf;
	/**
	 * 
	 * @param deptno number of the department
	 * @param job specialization of the department
	 * @param ordersnf the orders that are not finished
	 */
	public Dept(int deptno, String job, int ordersnf) {
		super();
		this.deptno = deptno;
		this.job = job;
		this.ordersnf = ordersnf;
	}
	/**
	 * 
	 * @return the number of the department
	 */
	public int getDeptno() {
		return deptno;
	}
	/**
	 * 
	 * @param deptno the number of the department
	 */
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	/**
	 * 
	 * @return getter for the job of the department
	 */
	public String getJob() {
		return job;
	}
	/**
	 * 
	 * @param job the job of the department
	 */
	public void setJob(String job) {
		this.job = job;
	}
	/**
	 * 
	 * @return the not finished orders of the department
	 */
	public int getOrdersnf() {
		return ordersnf;
	}
	/**
	 * 
	 * @param ordersnf the not finished orders of the department
	 */
	public void setOrdersnf(int ordersnf) {
		this.ordersnf = ordersnf;
	}
	
}
