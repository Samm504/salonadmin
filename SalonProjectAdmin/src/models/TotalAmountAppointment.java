package models;

public class TotalAmountAppointment {
	int appointment_id;
	String customer_name;
	double total_amount;
	
	public TotalAmountAppointment(int appointment_id, String customer_name, double total_amount) {
		super();
		this.appointment_id = appointment_id;
		this.customer_name = customer_name;
		this.total_amount = total_amount;
	}
	
	public int getAppointment_id() {
		return appointment_id;
	}
	public void setAppointment_id(int appointment_id) {
		this.appointment_id = appointment_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}
}
