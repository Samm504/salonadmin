package models;

public class Beautician {

	int beautician_id;
	String beautician_name;
	String beautician_email;
	String beautician_phone;
	
	public Beautician(int beautician_id, String beautician_name, String beautician_email, String beautician_phone) {
		super();
		this.beautician_id = beautician_id;
		this.beautician_name = beautician_name;
		this.beautician_email = beautician_email;
		this.beautician_phone = beautician_phone;
	}

	public int getBeautician_id() {
		return beautician_id;
	}

	public void setBeautician_id(int beautician_id) {
		this.beautician_id = beautician_id;
	}

	public String getBeautician_name() {
		return beautician_name;
	}

	public void setBeautician_name(String beautician_name) {
		this.beautician_name = beautician_name;
	}

	public String getBeautician_email() {
		return beautician_email;
	}

	public void setBeautician_email(String beautician_email) {
		this.beautician_email = beautician_email;
	}

	public String getBeautician_phone() {
		return beautician_phone;
	}

	public void setBeautician_phone(String beautician_phone) {
		this.beautician_phone = beautician_phone;
	}
	
}
