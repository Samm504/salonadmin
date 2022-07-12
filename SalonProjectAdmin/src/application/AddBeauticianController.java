package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBeauticianController {
	@FXML 
	private TextField name;
	@FXML
	private TextField email;
	@FXML
	private TextField phone;
	@FXML
	private Button addBtn;
	
	public void addBeautician() {
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		try {
			PreparedStatement addBeautician = connectDB.prepareStatement("INSERT INTO beauticians(beautician_name, beautician_email, beautician_phone) VALUES (?,?,?)");
			
			addBeautician.setString(1, name.getText());
			addBeautician.setString(2, email.getText());
			addBeautician.setString(3, phone.getText());
			
			addBeautician.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Stage stage = (Stage) addBtn.getScene().getWindow();
		stage.close();
	}
}
