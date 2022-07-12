package models;

public class Service {
	int service_code;
	String service_name;
	double service_price;
	
	public Service(int service_code, String service_name, double service_price) {
		super();
		this.service_code = service_code;
		this.service_name = service_name;
		this.service_price = service_price;
	}
	
	public int getService_code() {
		return service_code;
	}
	public void setService_code(int service_code) {
		this.service_code = service_code;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public double getService_price() {
		return service_price;
	}
	public void setService_price(double service_price) {
		this.service_price = service_price;
	}
}
