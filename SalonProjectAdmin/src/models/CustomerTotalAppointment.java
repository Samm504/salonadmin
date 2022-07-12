package models;

public class CustomerTotalAppointment {
	int customer_id;
	String customer_name;
	int total_appointments;
	
	public CustomerTotalAppointment(int customer_id, String customer_name, int total_appointments) {
		super();
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.total_appointments = total_appointments;
	}
	
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public int getTotal_appointments() {
		return total_appointments;
	}
	public void setTotal_appointments(int total_appointments) {
		this.total_appointments = total_appointments;
	}
	
	
}
