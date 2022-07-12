package application;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCustomerController {
	@FXML
	private TextField name;
	@FXML
	private TextField gender;
	@FXML
	private TextField address;
	@FXML
	private TextField email;
	@FXML
	private TextField phone;
	@FXML
	private TextField memberid;
	@FXML
	private TextField products;
	@FXML
	private RadioButton yesCB;
	@FXML
	private RadioButton noCB;
	@FXML
	private Button addBtn;
	
	
	
	public void addCustomer() {
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		try {
			PreparedStatement addCustomer = connectDB.prepareStatement("INSERT INTO customers(customer_name, customer_gender, customer_address, customer_email, customer_phone, customer_memid, customer_detailone, customer_detailtwo, customer_detailthree) VALUES (?,?,?,?,?,?,?,?,?)");
			
			addCustomer.setString(1, name.getText());
			addCustomer.setString(2, gender.getText());
			addCustomer.setString(3, address.getText());
			addCustomer.setString(4, email.getText());
			addCustomer.setString(5, phone.getText());
			
			//checkIfCustomerFirstTimer
			if(memberid.getText().isBlank()) {
				addCustomer.setNull(6, java.sql.Types.INTEGER);
				addCustomer.setInt(7, 1);
			}else {
				addCustomer.setString(6, memberid.getText());
				addCustomer.setInt(7, 0);
			}
			
			//checkIfAllergyProduct
			if(yesCB.isSelected()) {
				addCustomer.setInt(8, 1);
				addCustomer.setString(9, products.getText());
				
			}else {
				addCustomer.setInt(8, 0);
				addCustomer.setNull(9, java.sql.Types.INTEGER);
			}
			
			addCustomer.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Stage stage = (Stage) addBtn.getScene().getWindow();
		stage.close();
	}
}
