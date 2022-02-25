package p3;
/**
 * 
 * a class for the ordders of the company
 *
 */
public class Order extends Book {
	int id;
	int quantity;
	String date_created;
	String date_finished;
	int dept;
	int cid;
	/**
	 * 
	 * @param type the predefined type of book
	 * @param ink the ink needed to craft 10 books
	 * @param paper the paper needed to craft 10 books
	 * @param glue the glue needed to craft 10 books
	 * @param cover the cover units needed to craft 10 books
	 * @param id the id of the order
	 * @param quantity the quantity of the book type
	 * @param date_created the date  in which the order was created
	 * @param date_finished the date in which the order was finished, 2021-10-21 if not finished
	 * @param dept the dept to that the order is assigned
	 * @param cid the id of the customer that ordered
	 */
	public Order(int type, int ink, int paper, int glue, int cover, int id, int quantity, String date_created,
			String date_finished, int dept,int cid) {
		super(type, ink, paper, glue, cover);
		this.id = id;
		this.quantity = quantity;
		this.date_created = date_created;
		this.date_finished = date_finished;
		this.dept = dept;
		this.cid = cid;
	}
	/**
	 * 
	 * @return the id of the order
	 */
	public int getId() {
		return id;
	}
	/**
	 * 
	 * @param id the id of the order
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 
	 * @return the creation date of the order
	 */
	public String getDate_created() {
		return date_created;
	}
	/**
	 * 
	 * @param date_created the creation date of the order
	 */
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	/**
	 * 
	 * @return the finish date of the order
	 */
	public String getDate_finished() {
		return date_finished;
	}
	/**
	 * 
	 * @param date_finished the finish date of the order
	 */
	public void setDate_finished(String date_finished) {
		this.date_finished = date_finished;
	}
	/**
	 * 
	 * @return the quantity of the order
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * 
	 * @param quantity the quantity of the order
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * 
	 * @return the dept of the order
	 */
	public int getDept() {
		return dept;
	}
	/**
	 * 
	 * @param dept the dept of the order
	 */
	public void setDept(int dept) {
		this.dept = dept;
	}
	
	
}
