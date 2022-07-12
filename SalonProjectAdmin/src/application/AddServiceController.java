package application;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddServiceController {
	@FXML
	private TextField name;
	@FXML
	private TextField price;
	@FXML
	private Button addBtn;
	
	public void addService() {
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		try {
			PreparedStatement addService = connectDB.prepareStatement("INSERT INTO services(service_name, service_price) VALUES (?,?)");
			
			addService.setString(1, name.getText());
			addService.setString(2, price.getText());
			
			addService.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Stage stage = (Stage) addBtn.getScene().getWindow();
		stage.close();
	}
}
