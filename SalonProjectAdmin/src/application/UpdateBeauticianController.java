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
import models.Beautician;

public class UpdateBeauticianController implements Initializable{
	@FXML
	private TextField name;
	@FXML
	private TextField email;
	@FXML
	private TextField phone;
	@FXML
	private Button updateBtn;
	@FXML
	private ChoiceBox<String> idCB;
	
	private ArrayList<String> retrieved_beauticianIDS = new ArrayList<String>();
	
	public void retrieveBeauticianIDS() {
		idCB.getItems().clear();
		idCB.getSelectionModel().clearSelection();
		idCB.setValue(null);
		retrieved_beauticianIDS.clear();
		
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		String retrieve_ids = "SELECT beautician_id FROM beauticians";
		
		try {
			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(retrieve_ids);
			
			while(queryResult.next()) {
				retrieved_beauticianIDS.add(queryResult.getString(1));
			}
			
			String[] id_array = new String[retrieved_beauticianIDS.size()];
			for (int i = 0; i < retrieved_beauticianIDS.size(); i++) {
	            id_array[i] = retrieved_beauticianIDS.get(i);
	        }
	        
			idCB.getItems().addAll(id_array);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void retrieveData() {
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		String retrieveBeauticians = "SELECT * FROM beauticians WHERE beautician_id = " + idCB.getValue();
		try {
			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(retrieveBeauticians);
			
			while(queryResult.next()) {
				name.setText(queryResult.getString(2));
				email.setText(queryResult.getString(3));
				phone.setText(queryResult.getString(4));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateData() {
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		try {
			PreparedStatement updateBeautician = connectDB.prepareStatement("UPDATE beauticians SET beautician_name = ?, beautician_email = ?, beautician_phone = ? WHERE beautician_id = ?");
			
			updateBeautician.setString(1, name.getText());
			updateBeautician.setString(2, email.getText());
			updateBeautician.setString(3, phone.getText());
			updateBeautician.setString(4, idCB.getValue());
			
			updateBeautician.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Stage stage = (Stage) updateBtn.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		retrieveBeauticianIDS();
		idCB.setOnAction(new EventHandler() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				retrieveData();
			}
			
		});
	}
}
