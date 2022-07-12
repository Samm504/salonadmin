package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Beautician;
import models.Customer;

public class DashboardController implements Initializable{
	//beauticianAnchorPane
	@FXML
	private TableView<Beautician> beauticianTable;
	@FXML
	private TableColumn<Beautician, String> beauticianidCol;
	@FXML
	private TableColumn<Beautician, String> beauticiannameCol;
	@FXML
	private TableColumn<Beautician, String> beauticianemailCol;
	@FXML
	private TableColumn<Beautician, String> beauticianphoneCol;
	@FXML
	private ChoiceBox<String> deletebeauticianCB;
	
	ObservableList<Beautician> BeauticianList = FXCollections.observableArrayList();
	private ArrayList<String> retrieved_beauticianIDS = new ArrayList<String>();
	
	//customerAnchorPane
	@FXML
	private TableView<Customer> customerTable;
	@FXML
	private TableColumn<Customer, String> customeridCol;
	@FXML
	private TableColumn<Customer, String> customernameCol;
	@FXML
	private TableColumn<Customer, String> customergenderCol;
	@FXML
	private TableColumn<Customer, String> customeraddressCol;
	@FXML
	private TableColumn<Customer, String> customeremailCol;
	@FXML
	private TableColumn<Customer, String> customerphoneCol;
	@FXML
	private TableColumn<Customer, String> customermemidCol;
	
	ObservableList<Customer> CustomerList = FXCollections.observableArrayList();
	
	//methodForBeautician
	public void setBeauticianColumns() {
		beauticianidCol.setCellValueFactory(new PropertyValueFactory<>("beautician_id"));
		beauticiannameCol.setCellValueFactory(new PropertyValueFactory<>("beautician_name"));
		beauticianemailCol.setCellValueFactory(new PropertyValueFactory<>("beautician_email"));
		beauticianphoneCol.setCellValueFactory(new PropertyValueFactory<>("beautician_phone"));
	}
	
	//methodForBeautician
	public void loadData_beautician() {
		BeauticianList.clear();
		setBeauticianColumns();
		
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		String retrieveBeauticians = "SELECT * FROM beauticians";
		try {
			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(retrieveBeauticians);
			
			while(queryResult.next()) {
				BeauticianList.add(new Beautician(
					queryResult.getInt("beautician_id"),
					queryResult.getString("beautician_name"),
					queryResult.getString("beautician_email"),
					queryResult.getString("beautician_phone")));
				beauticianTable.setItems(BeauticianList);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//methodForBeautician
	public void addBeautician() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("AddBeautician.fxml"));
		try {
			loader.load();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
		Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        loadData_beautician();
	}
	
	//methodForBeautician
	public void retrieveBeauticianIDS() {
		deletebeauticianCB.getItems().clear();
		deletebeauticianCB.getSelectionModel().clearSelection();
		deletebeauticianCB.setValue(null);
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
	        
			deletebeauticianCB.getItems().addAll(id_array);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//methodForBeautician
	public void deleteBeautician() {
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		try {
			PreparedStatement addBeautician = connectDB.prepareStatement("DELETE FROM beauticians WHERE beautician_id = ?");
			
			addBeautician.setString(1, deletebeauticianCB.getValue());
			
			addBeautician.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		retrieveBeauticianIDS();
		loadData_beautician();
	}
	
	//methodForBeautician
	public void updateBeautician() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("UpdateBeautician.fxml"));
		try {
			loader.load();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
		Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        loadData_beautician();
	}
	
	//methodForCustomer
	public void setCustomerColumns() {
		customeridCol.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
		customernameCol.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
		customergenderCol.setCellValueFactory(new PropertyValueFactory<>("customer_gender"));
		customeraddressCol.setCellValueFactory(new PropertyValueFactory<>("customer_address"));
		customeremailCol.setCellValueFactory(new PropertyValueFactory<>("customer_email"));
		customerphoneCol.setCellValueFactory(new PropertyValueFactory<>("customer_phone"));
		customermemidCol.setCellValueFactory(new PropertyValueFactory<>("customer_memid"));
	}
	
	//methodForCustomer
	public void loadData_customer() {
		setCustomerColumns();
		
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		String retrieveCustomers = "SELECT * FROM customers";
		try {
			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(retrieveCustomers);
			
			while(queryResult.next()) {
				CustomerList.add(new Customer(
					queryResult.getInt("customer_id"),
					queryResult.getString("customer_name"),
					queryResult.getString("customer_gender"),
					queryResult.getString("customer_address"),
					queryResult.getString("customer_email"),
					queryResult.getString("customer_phone"),
					queryResult.getString("customer_memid"),
					queryResult.getInt("customer_detailone"),
					queryResult.getInt("customer_detailtwo"),
					queryResult.getString("customer_detailthree")));
				customerTable.setItems(CustomerList);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		retrieveBeauticianIDS();
	}
}
