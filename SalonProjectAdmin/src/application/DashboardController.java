package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
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
import models.Appointment;
import models.Beautician;
import models.Customer;
import models.CustomerTotalAppointment;
import models.Service;
import models.TotalAmountAppointment;

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
	@FXML
	private ChoiceBox<String> deletecustomerCB;
	
	ObservableList<Customer> CustomerList = FXCollections.observableArrayList();
	private ArrayList<String> retrieved_customerIDS = new ArrayList<String>();
	
	//serviceAnchorPane
	@FXML
	private TableView<Service> serviceTable;
	@FXML
	private TableColumn<Service, String> servicecodeCol;
	@FXML
	private TableColumn<Service, String> servicenameCol;
	@FXML
	private TableColumn<Service, String> servicepriceCol;
	@FXML
	private ChoiceBox<String> deleteserviceCB;
	
	ObservableList<Service> ServiceList = FXCollections.observableArrayList();
	private ArrayList<String> retrieved_serviceIDS = new ArrayList<String>();
	
	//appointmentAnchorPane
	@FXML
	private TableView<Appointment> appointmentTable;
	@FXML
	private TableColumn<Appointment, String> appointmentidCol;
	@FXML
	private TableColumn<Appointment, String> appointmentdateCol;
	@FXML
	private TableColumn<Appointment, String> appointmenttimeCol;
	@FXML
	private TableColumn<Appointment, String> appointmentcnameCol;
	@FXML
	private TableColumn<Appointment, String> appointmentbnameCol;
	@FXML
	private TableColumn<Appointment, String> appointmentsvCol;
	@FXML
	private ChoiceBox<String> deleteappointmentCB;
	
	ObservableList<Appointment> AppointmentList = FXCollections.observableArrayList();
	private ArrayList<String> retrieved_appointmentIDS = new ArrayList<String>();
	private ArrayList<String> retrieved_servicesAvailed = new ArrayList<String>();
	String svString;
	
	//overallAnchorPane - CustomerTotalAppointment
	@FXML
	private TableView<CustomerTotalAppointment> customertotalTable;
	@FXML
	private TableColumn<CustomerTotalAppointment, String> customeridCTCol;
	@FXML
	private TableColumn<CustomerTotalAppointment, String> customernameCTCol;
	@FXML
	private TableColumn<CustomerTotalAppointment, String> totalappointmentCTCol;
	
	ObservableList<CustomerTotalAppointment> CustomerTotalAppointmentList = FXCollections.observableArrayList();
	
	//overallAnchorPane - TotalAmountAppointment
	@FXML
	private TableView<TotalAmountAppointment> appointmenttotalTable;
	@FXML
	private TableColumn<TotalAmountAppointment, String> appointmentidTACol;
	@FXML
	private TableColumn<TotalAmountAppointment, String> customernameTACol;
	@FXML
	private TableColumn<TotalAmountAppointment, String> totalamountTACol;

	ObservableList<TotalAmountAppointment> TotalAmountAppointmentList = FXCollections.observableArrayList();
	
	//methodForBeautician
	public void setBeauticianColumns() {
		beauticianidCol.setCellValueFactory(new PropertyValueFactory<>("beautician_id"));
		beauticiannameCol.setCellValueFactory(new PropertyValueFactory<>("beautician_name"));
		beauticianemailCol.setCellValueFactory(new PropertyValueFactory<>("beautician_email"));
		beauticianphoneCol.setCellValueFactory(new PropertyValueFactory<>("beautician_phone"));
	}
	
	//methodForBeautician
	public void loadData_beautician() {
		retrieveBeauticianIDS();
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
        stage.setResizable(false);
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
			PreparedStatement deleteBeautician = connectDB.prepareStatement("DELETE FROM beauticians WHERE beautician_id = ?");
			
			deleteBeautician.setString(1, deletebeauticianCB.getValue());
			
			deleteBeautician.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
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
        stage.setResizable(false);
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
		retrieveCustomerIDS();
		CustomerList.clear();
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
	
	//methodForCustomer
	public void addCustomer() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("AddCustomer.fxml"));
		try {
			loader.load();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
		Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        loadData_customer();
	}
	
	//methodForCustomer
	public void retrieveCustomerIDS() {
		deletecustomerCB.getItems().clear();
		deletecustomerCB.getSelectionModel().clearSelection();
		deletecustomerCB.setValue(null);
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
	        
			deletecustomerCB.getItems().addAll(id_array);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//methodForCustomer
	public void deleteCustomer() {
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		try {
			PreparedStatement deleteCustomer = connectDB.prepareStatement("DELETE FROM customers WHERE customer_id = ?");
			
			deleteCustomer.setString(1, deletecustomerCB.getValue());
			
			deleteCustomer.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		loadData_customer();
	}
	
	//methodForCustomer
	public void updateCustomer() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("UpdateCustomer.fxml"));
		try {
			loader.load();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
		Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        loadData_customer();
	}
	
	//methodForService
	public void setServiceColumns() {
		servicecodeCol.setCellValueFactory(new PropertyValueFactory<>("service_code"));
		servicenameCol.setCellValueFactory(new PropertyValueFactory<>("service_name"));
		servicepriceCol.setCellValueFactory(new PropertyValueFactory<>("service_price"));
	}
		
	//methodForService
	public void loadData_service() {
		retrieveServiceIDS();
		ServiceList.clear();
		setServiceColumns();
			
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
			
		String retrieveServices = "SELECT * FROM services";
		try {
			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(retrieveServices);
				
			while(queryResult.next()) {
				ServiceList.add(new Service(
					queryResult.getInt("service_code"),
					queryResult.getString("service_name"),
					queryResult.getDouble("service_price")));
				serviceTable.setItems(ServiceList);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
		
		//methodForService
	public void addService() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("AddService.fxml"));
		try {
			loader.load();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
			
		Parent parent = loader.getRoot();
	    Stage stage = new Stage();
	    stage.setResizable(false);
	    stage.setScene(new Scene(parent));
	    stage.initStyle(StageStyle.UTILITY);
	    stage.initModality(Modality.APPLICATION_MODAL);
	    stage.showAndWait();
	        
	    loadData_service();
	}
		
		//methodForService
	public void retrieveServiceIDS() {
		deleteserviceCB.getItems().clear();
		deleteserviceCB.getSelectionModel().clearSelection();
		deleteserviceCB.setValue(null);
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
		        
			deleteserviceCB.getItems().addAll(id_array);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	//methodForService
	public void deleteService() {
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
			
		try {
			PreparedStatement deleteService = connectDB.prepareStatement("DELETE FROM services WHERE service_code = ?");
				
			deleteService.setString(1, deleteserviceCB.getValue());
				
			deleteService.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		loadData_service();
	}
		
		//methodForService
	public void updateService() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("UpdateService.fxml"));
		try {
			loader.load();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
			
		Parent parent = loader.getRoot();
	    Stage stage = new Stage();
	    stage.setResizable(false);
	    stage.setScene(new Scene(parent));
	    stage.initStyle(StageStyle.UTILITY);
	    stage.initModality(Modality.APPLICATION_MODAL);
	    stage.showAndWait();
	        
	    loadData_service();
	}
	
	//methodForAppointment
	public void setAppointmentColumns() {
		appointmentidCol.setCellValueFactory(new PropertyValueFactory<>("appointment_id"));
		appointmentdateCol.setCellValueFactory(new PropertyValueFactory<>("appointment_date"));
		appointmenttimeCol.setCellValueFactory(new PropertyValueFactory<>("appointment_time"));
		appointmentcnameCol.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
		appointmentbnameCol.setCellValueFactory(new PropertyValueFactory<>("beautician_name"));
		appointmentsvCol.setCellValueFactory(new PropertyValueFactory<>("services_availed"));
	}
	
	//methodForAppointment
	public void loadData_appointment() {
		retrieveAppointmentIDS();
		AppointmentList.clear();
		setAppointmentColumns();
		
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		String retrieveAppointment = "SELECT appointment_id, appointment_date, appointment_time, customer_name, beautician_name FROM appointments a JOIN customers c ON a.customer_id = c.customer_id JOIN beauticians b ON a.beautician_id = b.beautician_id";
		try {
			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(retrieveAppointment);
			
			while(queryResult.next()) {
				retrieveServicesAvailed(queryResult.getString(1));
				AppointmentList.add(new Appointment(
					queryResult.getInt("appointment_id"),
					queryResult.getString("appointment_date"),
					queryResult.getString("appointment_time"),
					queryResult.getString("customer_name"),
					queryResult.getString("beautician_name"),
					svString));
				appointmentTable.setItems(AppointmentList);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//methodForAppointment
	public void addAppointment() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("AddAppointment.fxml"));
		try {
			loader.load();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
		Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        loadData_appointment();
	}
	
	//methodForAppointment
	public void retrieveAppointmentIDS() {
		deleteappointmentCB.getItems().clear();
		deleteappointmentCB.getSelectionModel().clearSelection();
		deleteappointmentCB.setValue(null);
		retrieved_appointmentIDS.clear();
		
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		String retrieve_ids = "SELECT appointment_id FROM appointments a JOIN customers c ON a.customer_id = c.customer_id JOIN beauticians b ON a.beautician_id = b.beautician_id";
		
		try {
			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(retrieve_ids);
			
			while(queryResult.next()) {
				retrieved_appointmentIDS.add(queryResult.getString(1));
			}
			
			String[] id_array = new String[retrieved_appointmentIDS.size()];
			for (int i = 0; i < retrieved_appointmentIDS.size(); i++) {
	            id_array[i] = retrieved_appointmentIDS.get(i);
	        }
	        
			deleteappointmentCB.getItems().addAll(id_array);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//methodForAppointment
	public void deleteAppointment() {
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		try {
			PreparedStatement deleteAppointment = connectDB.prepareStatement("DELETE FROM appointments WHERE appointment_id = ?");
			
			deleteAppointment.setString(1, deleteappointmentCB.getValue());
			
			deleteAppointment.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		loadData_appointment();
	}
	
	//methodForAppointment
	public void updateAppointment() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("UpdateAppointment.fxml"));
		try {
			loader.load();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
		Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        loadData_appointment();
	}
	
	public void retrieveServicesAvailed(String x) {
		retrieved_servicesAvailed.clear();
		
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		try {
			PreparedStatement retrieve_services = connectDB.prepareStatement("SELECT s.service_name FROM appointments a JOIN service_duration sd ON a.appointment_id = sd.appointment_id JOIN services s ON sd.service_code = s.service_code WHERE sd.appointment_id = ? ORDER BY sd.appointment_id");

			retrieve_services.setString(1, x);
			ResultSet queryResult = retrieve_services.executeQuery();
			
			while(queryResult.next()) {
				retrieved_servicesAvailed.add(queryResult.getString(1));
			}
			
			String[] id_array = new String[retrieved_servicesAvailed.size()];
			for (int i = 0; i < retrieved_servicesAvailed.size(); i++) {
	            id_array[i] = retrieved_servicesAvailed.get(i);
	        }
	        
			svString = Arrays.toString(id_array);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//overallMethods
	public void setOverallColumns() {
		customeridCTCol.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
		customernameCTCol.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
		totalappointmentCTCol.setCellValueFactory(new PropertyValueFactory<>("total_appointments"));
		appointmentidTACol.setCellValueFactory(new PropertyValueFactory<>("appointment_id"));
		customernameTACol.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
		totalamountTACol.setCellValueFactory(new PropertyValueFactory<>("total_amount"));
	}
	
	//overallMethods
	public void loadData_overall() {
		CustomerTotalAppointmentList.clear();
		TotalAmountAppointmentList.clear();
		setOverallColumns();
		
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		String retrieveCustomerTotal = "SELECT a.customer_id, c.customer_name, count(*) AS total_appointments FROM appointments a JOIN customers c ON a.customer_id = c.customer_id GROUP BY c.customer_id;";
		try {
			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(retrieveCustomerTotal);
			
			while(queryResult.next()) {
				CustomerTotalAppointmentList.add(new CustomerTotalAppointment(
					queryResult.getInt("customer_id"),
					queryResult.getString("customer_name"),
					queryResult.getInt("total_appointments")));
				customertotalTable.setItems(CustomerTotalAppointmentList);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String retrieveAppointmentTotal = "SELECT sd.appointment_id, c.customer_name, sum(service_price) AS total_amount FROM service_duration sd JOIN appointments a ON sd.appointment_id = a.appointment_id JOIN customers c ON a.customer_id = c.customer_id JOIN services s ON s.service_code = sd.service_code GROUP BY sd.appointment_id;";
		try {
			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(retrieveAppointmentTotal);
			
			while(queryResult.next()) {
				TotalAmountAppointmentList.add(new TotalAmountAppointment(
					queryResult.getInt("appointment_id"),
					queryResult.getString("customer_name"),
					queryResult.getDouble("total_amount")));
				appointmenttotalTable.setItems(TotalAmountAppointmentList);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		retrieveBeauticianIDS();
		retrieveCustomerIDS();
		retrieveServiceIDS();
	}
}
