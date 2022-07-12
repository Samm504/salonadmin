package models;

public class Appointment {
	int appointment_id;
	String appointment_date;
	String appointment_time;
	String customer_name;
	String beautician_name;
	String services_availed;
	
	public Appointment(int appointment_id, String appointment_date, String appointment_time, String customer_name,
			String beautician_name, String services_availed) {
		super();
		this.appointment_id = appointment_id;
		this.appointment_date = appointment_date;
		this.appointment_time = appointment_time;
		this.customer_name = customer_name;
		this.beautician_name = beautician_name;
		this.services_availed = services_availed;
	}
	
	public int getAppointment_id() {
		return appointment_id;
	}
	public void setAppointment_id(int appointment_id) {
		this.appointment_id = appointment_id;
	}
	public String getAppointment_date() {
		return appointment_date;
	}
	public void setAppointment_date(String appointment_date) {
		this.appointment_date = appointment_date;
	}
	public String getAppointment_time() {
		return appointment_time;
	}
	public void setAppointment_time(String appointment_time) {
		this.appointment_time = appointment_time;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getBeautician_name() {
		return beautician_name;
	}
	public void setBeautician_name(String beautician_name) {
		this.beautician_name = beautician_name;
	}
	public String getServices_availed() {
		return services_availed;
	}
	public void setServices_availed(String services_availed) {
		this.services_availed = services_availed;
	}
}	
