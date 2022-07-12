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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateCustomerController implements Initializable{
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
	private Button updateBtn;
	@FXML
	private ChoiceBox<String> idCB;
	
	private ArrayList<String> retrieved_customerIDS = new ArrayList<String>();
	
	public void retrieveCustomerIDS() {
		idCB.getItems().clear();
		idCB.getSelectionModel().clearSelection();
		idCB.setValue(null);
		retrieved_customerIDS.clear();
		
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		String retrieve_ids = "SELECT customer_id FROM customers";
		
		try {
			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(retrieve_ids);
			
			while(queryResult.next()) {
				retrieved_customerIDS.add(queryResult.getString(1));
			}
			
			String[] id_array = new String[retrieved_customerIDS.size()];
			for (int i = 0; i < retrieved_customerIDS.size(); i++) {
	            id_array[i] = retrieved_customerIDS.get(i);
	        }
	        
			idCB.getItems().addAll(id_array);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void retrieveData() {
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		String retrieveCustomers = "SELECT * FROM customers WHERE customer_id = " + idCB.getValue();
		try {
			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(retrieveCustomers);
			
			while(queryResult.next()) {
				name.setText(queryResult.getString(2));
				gender.setText(queryResult.getString(3));
				address.setText(queryResult.getString(4));
				email.setText(queryResult.getString(5));
				phone.setText(queryResult.getString(6));
				memberid.setText(queryResult.getString(7));
				
				//retrieveFirstTime
				if(queryResult.getInt(9) == 1) {
					yesCB.setSelected(true);
					products.setText(queryResult.getString(10));
				}else {
					noCB.setSelected(true);
					products.setText("");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateData() {
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		try {
			PreparedStatement updateCustomer = connectDB.prepareStatement("UPDATE customers SET customer_name = ?, customer_gender = ?, customer_address = ?, customer_email = ?, customer_phone = ?, customer_memid = ?, customer_detailone = ?, customer_detailtwo = ?, customer_detailthree = ? WHERE customer_id = ?");
			
			updateCustomer.setString(1, name.getText());
			updateCustomer.setString(2, gender.getText());
			updateCustomer.setString(3, address.getText());
			updateCustomer.setString(4, email.getText());
			updateCustomer.setString(5, phone.getText());
			
			if(memberid.getText().isBlank()) {
				updateCustomer.setNull(6, java.sql.Types.INTEGER);
				updateCustomer.setInt(7, 1);
			}else {
				updateCustomer.setString(6, memberid.getText());
				updateCustomer.setInt(7, 0);
			}
			
			//checkIfAllergyProduct
			if(yesCB.isSelected()) {
				updateCustomer.setInt(8, 1);
				updateCustomer.setString(9, products.getText());
				
			}else {
				updateCustomer.setInt(8, 0);
				updateCustomer.setNull(9, java.sql.Types.INTEGER);
			}
			
			updateCustomer.setString(10, idCB.getValue());
			
			updateCustomer.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Stage stage = (Stage) updateBtn.getScene().getWindow();
		stage.close();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		retrieveCustomerIDS();
		idCB.setOnAction(new EventHandler() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				retrieveData();
			}
			
		});
	}

}
