package models;

public class Customer {
	int customer_id;
	String customer_name;
	String customer_gender;
	String customer_address;
	String customer_email;
	String customer_phone;
	String customer_memid;
	int customer_detailone;
	int customer_detailtwo;
	String customer_detailthree;
	
	public Customer(int customer_id, String customer_name, String customer_gender, String customer_address,
			String customer_email, String customer_phone, String customer_memid, int customer_detailone,
			int customer_detailtwo, String customer_detailthree) {
		super();
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.customer_gender = customer_gender;
		this.customer_address = customer_address;
		this.customer_email = customer_email;
		this.customer_phone = customer_phone;
		this.customer_memid = customer_memid;
		this.customer_detailone = customer_detailone;
		this.customer_detailtwo = customer_detailtwo;
		this.customer_detailthree = customer_detailthree;
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
	public String getCustomer_gender() {
		return customer_gender;
	}
	public void setCustomer_gender(String customer_gender) {
		this.customer_gender = customer_gender;
	}
	public String getCustomer_address() {
		return customer_address;
	}
	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}
	public String getCustomer_email() {
		return customer_email;
	}
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	public String getCustomer_phone() {
		return customer_phone;
	}
	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}
	public String getCustomer_memid() {
		return customer_memid;
	}
	public void setCustomer_memid(String customer_memid) {
		this.customer_memid = customer_memid;
	}
	public int getCustomer_detailone() {
		return customer_detailone;
	}
	public void setCustomer_detailone(int customer_detailone) {
		this.customer_detailone = customer_detailone;
	}
	public int getCustomer_detailtwo() {
		return customer_detailtwo;
	}
	public void setCustomer_detailtwo(int customer_detailtwo) {
		this.customer_detailtwo = customer_detailtwo;
	}
	public String getCustomer_detailthree() {
		return customer_detailthree;
	}
	public void setCustomer_detailthree(String customer_detailthree) {
		this.customer_detailthree = customer_detailthree;
	}
}
