package models;

import javafx.scene.control.CheckBox;

public class ServiceWithSelect {
	int service_code;
	String service_name;
	double service_price;
	CheckBox service_select;
	
	public ServiceWithSelect(int service_code, String service_name, double service_price) {
		super();
		this.service_code = service_code;
		this.service_name = service_name;
		this.service_price = service_price;
		this.service_select = new CheckBox();
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
	public CheckBox getService_select() {
		return service_select;
	}
	public void setService_select(CheckBox service_select) {
		this.service_select = service_select;
	}
}
