package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateServiceController implements Initializable{
	@FXML
	private TextField name;
	@FXML
	private TextField price;
	@FXML
	private Button updateBtn;
	@FXML
	private ChoiceBox<String> idCB;
	
	private ArrayList<String> retrieved_serviceIDS = new ArrayList<String>();
	
	public void retrieveServiceIDS() {
		idCB.getItems().clear();
		idCB.getSelectionModel().clearSelection();
		idCB.setValue(null);
		retrieved_serviceIDS.clear();
		
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		String retrieve_ids = "SELECT service_code FROM services";
		
		try {
			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(retrieve_ids);
			
			while(queryResult.next()) {
				retrieved_serviceIDS.add(queryResult.getString(1));
			}
			
			String[] id_array = new String[retrieved_serviceIDS.size()];
			for (int i = 0; i < retrieved_serviceIDS.size(); i++) {
	            id_array[i] = retrieved_serviceIDS.get(i);
	        }
	        
			idCB.getItems().addAll(id_array);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void retrieveData() {
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		String retrieveServices = "SELECT * FROM services WHERE service_code = " + idCB.getValue();
		try {
			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(retrieveServices);
			
			while(queryResult.next()) {
				name.setText(queryResult.getString(2));
				price.setText(queryResult.getString(3));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateData() {
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		try {
			PreparedStatement updateService = connectDB.prepareStatement("UPDATE services SET service_name = ?, service_price = ? WHERE service_code = ?");
			
			updateService.setString(1, name.getText());
			updateService.setString(2, price.getText());
			
			updateService.setString(3, idCB.getValue());
			
			updateService.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Stage stage = (Stage) updateBtn.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		retrieveServiceIDS();
		idCB.setOnAction(new EventHandler() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				retrieveData();
			}
			
		});
	}
	
	
}
