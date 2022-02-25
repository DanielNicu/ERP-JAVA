package p3;
/**
 * 
 * A class for the providers of the company
 *
 */
public class Provider {
	String name;
	String material;
	String contact;
	int delivery_time;
	/**
	 * 
	 * @param name name of the company
	 * @param material the material that if provides
	 * @param contact the contact of the company
	 * @param delivery_time the time to deliver of the company in days
	 */
	public Provider(String name, String material, String contact, int delivery_time) {
		super();
		this.name = name;
		this.material = material;
		this.contact = contact;
		this.delivery_time = delivery_time;
	}
	/**
	 * 
	 * @return the name of the company
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @return the material of the company
	 */
	public String getMaterial() {
		return material;
	}
	/**
	 * 
	 * @return the contact of the company
	 */
	public String getContact() {
		return contact;
	}
	/**
	 * 
	 * @return the delivery time of the company
	 */
	public int getDelivery_time() {
		return delivery_time;
	}
	/**
	 * A method that deletes a provider
	 * @return null
	 */
	public Provider delete()
	{
		return null;
	}
}
