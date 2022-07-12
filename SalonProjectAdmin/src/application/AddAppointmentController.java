package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Service;
import models.ServiceWithSelect;


public class AddAppointmentController implements Initializable{
	@FXML
	private TextField customer_name;
	@FXML
	private TextField beautician_name;
	@FXML
	private ChoiceBox<String> customer_id;
	@FXML
	private ChoiceBox<String> beautician_id;
	@FXML
	private ChoiceBox<String> timePicker;
	@FXML
	private Button addcustomerBtn;
	@FXML
	private Button addBtn;
	@FXML
	private Button saveserviceBtn;
	@FXML
	private DatePicker datePicker;
	@FXML
	private ListView<String> availableservicesListView;
	@FXML
	private ListView<String> selectedservicesListView;
	
	//customerIDS
	private ArrayList<String> retrieved_customerIDS = new ArrayList<String>();
	//beauticianIDS
	private ArrayList<String> retrieved_beauticianIDS = new ArrayList<String>();
	//dateVar
	private LocalDate selectedDate;
	private String formattedDate;
	private ArrayList<String> retrieved_time = new ArrayList<String>();
	
//	//servicesListViewAvailable
//	private ObservableList<String> available_services = FXCollections.observableArrayList();
//	private ObservableList<String> selected_services = FXCollections.observableArrayList();
//	private ArrayList<String> finalized_services = new ArrayList<String>();
//	private String selectedItem;
	
	//serviceTableView
	@FXML
	private TableView<ServiceWithSelect> serviceTable;
	@FXML
	private TableColumn<ServiceWithSelect, String> servicecodeCol;
	@FXML
	private TableColumn<ServiceWithSelect, String> servicenameCol;
	@FXML
	private TableColumn<ServiceWithSelect, String> servicepriceCol;
	@FXML
	private TableColumn<ServiceWithSelect, String> serviceselectCol;
	
	ObservableList<ServiceWithSelect> ServiceList = FXCollections.observableArrayList();
	private ArrayList<String> retrieved_serviceCodes = new ArrayList<String>();
	
//	public void openSelectService() {
//		
//		FXMLLoader loader = new FXMLLoader();
//		loader.setLocation(getClass().getResource("SelectServices.fxml"));
//		try {
//			loader.load();
//		}catch(IOException ex) {
//			ex.printStackTrace();
//		}
//		
//		Parent parent = loader.getRoot();
//        Stage stage = new Stage();
//        stage.setResizable(false);
//        stage.setScene(new Scene(parent));
//        stage.initStyle(StageStyle.UTILITY);
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.showAndWait();
//        System.out.println(finalized_services);
//	}
	
	public void openAddCustomer() {
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
        
        //afterAddgetIDS
        retrieveCustomerIDS();
	}
	
	public void retrieveCustomerIDS() {
		customer_id.getItems().clear();
		customer_id.getSelectionModel().clearSelection();
		customer_id.setValue(null);
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
	        
			customer_id.getItems().addAll(id_array);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void retrieveBeauticianIDS() {
		beautician_id.getItems().clear();
		beautician_id.getSelectionModel().clearSelection();
		beautician_id.setValue(null);
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
	        
			beautician_id.getItems().addAll(id_array);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setCustomerName() {
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		String retrieveCustomerName = "SELECT customer_name FROM customers WHERE customer_id = " + customer_id.getValue();
		
		try {
			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(retrieveCustomerName);
			
			while(queryResult.next()) {
				customer_name.setText(queryResult.getString(1));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setBeauticianName() {
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		String retrieveBeauticianName = "SELECT beautician_name FROM beauticians WHERE beautician_id = " + beautician_id.getValue();
		
		try {
			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(retrieveBeauticianName);
			
			while(queryResult.next()) {
				beautician_name.setText(queryResult.getString(1));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deriveAppointmentTime() {
		selectedDate = datePicker.getValue();
		formattedDate = selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		timePicker.getItems().clear();
		timePicker.getSelectionModel().clearSelection();
		timePicker.setValue(null);
		
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		try {
			PreparedStatement deriveTime = connectDB.prepareStatement("SELECT appointment_time FROM appointment_time WHERE appointment_time NOT IN (SELECT appointment_time FROM appointments WHERE appointment_date = ? AND beautician_id = ?)");
			
			deriveTime.setString(1, formattedDate);
			deriveTime.setString(2, beautician_id.getValue());
			
			ResultSet queryResult = deriveTime.executeQuery();
			
			retrieved_time.clear();
			while(queryResult.next()) {
				retrieved_time.add(queryResult.getString(1));
			}
			
			String[] time_array = new String[retrieved_time.size()];
			for (int i = 0; i < retrieved_time.size(); i++) {
				time_array[i] = retrieved_time.get(i);
	        }
	        
			timePicker.getItems().addAll(time_array);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//	public void retrieveAvailableServices() {
//		available_services.clear();
//		
//		DatabaseConnection connectNow = new DatabaseConnection();
//		Connection connectDB = connectNow.getConnection();
//			
//		String retrieveServices = "SELECT service_name FROM services";
//		try {
//			Statement statement = connectDB.createStatement();
//			ResultSet queryResult = statement.executeQuery(retrieveServices);
//				
//			while(queryResult.next()) {
//				available_services.add(queryResult.getString(1));
//			}
//			availableservicesListView.setItems(available_services);
//			selectedservicesListView.setItems(selected_services);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void selectServiceListen() {
//		selectedItem = availableservicesListView.getSelectionModel().getSelectedItem();
//		selected_services.add(selectedItem);
//		available_services.remove(selectedItem);
//		availableservicesListView.setItems(available_services);
//		selectedservicesListView.setItems(selected_services);
//	}
//	
//	public void saveServices() {
//		for (int i = 0; i < selected_services.size(); i++) {
//			finalized_services.add(selected_services.get(i));
//        }
//		
//		Stage stage = (Stage) saveserviceBtn.getScene().getWindow();
//		stage.close();
//	}
	
	public void addAppointment() {
		int appointment_id = 1;
		selectServices();
		
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		//insertAppointmentFirst
		try {
			PreparedStatement addAppointment = connectDB.prepareStatement("INSERT INTO appointments(appointment_date, appointment_time, customer_id, beautician_id) VALUES (?,?,?,?)");
			
			addAppointment.setString(1, formattedDate);
			addAppointment.setString(2, timePicker.getValue());
			addAppointment.setString(3, customer_id.getValue());
			addAppointment.setString(4, beautician_id.getValue());
			
			addAppointment.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//getAppointmentID
		try {
			PreparedStatement retrieve_appointmentid = connectDB.prepareStatement("SELECT LAST_INSERT_ID()");
			
			ResultSet queryResult = retrieve_appointmentid.executeQuery();
			while(queryResult.next()) {
				appointment_id = queryResult.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//insertServiceDurations
		try {
			PreparedStatement addServiceDuration = connectDB.prepareStatement("INSERT INTO service_duration(service_code, appointment_id, service_duration) VALUES (?,?,?)");
			addServiceDuration.setInt(2, appointment_id);
			addServiceDuration.setString(3, "20 mins");
			for (int i = 0; i < retrieved_serviceCodes.size(); i++) {
				addServiceDuration.setInt(1, Integer.parseInt(retrieved_serviceCodes.get(i)));
				addServiceDuration.executeUpdate();
	        }
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Stage stage = (Stage) addBtn.getScene().getWindow();
		stage.close();
	}
	
	public void setServiceColumns() {
		servicecodeCol.setCellValueFactory(new PropertyValueFactory<>("service_code"));
		servicenameCol.setCellValueFactory(new PropertyValueFactory<>("service_name"));
		servicepriceCol.setCellValueFactory(new PropertyValueFactory<>("service_price"));
		serviceselectCol.setCellValueFactory(new PropertyValueFactory<>("service_select"));
	}
	
	public void loadData_services() {
		ServiceList.clear();
		setServiceColumns();
			
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
			
		String retrieveServices = "SELECT * FROM services";
		try {
			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(retrieveServices);
				
			while(queryResult.next()) {
				ServiceList.add(new ServiceWithSelect(
					queryResult.getInt("service_code"),
					queryResult.getString("service_name"),
					queryResult.getDouble("service_price")));
				serviceTable.setItems(ServiceList);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void selectServices() {
		for(ServiceWithSelect service: ServiceList) {
			if(service.getService_select().isSelected()) {
				retrieved_serviceCodes.add(Integer.toString(service.getService_code()));
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		retrieveCustomerIDS();
		retrieveBeauticianIDS();
		loadData_services();
		
		customer_id.setOnAction(new EventHandler() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				setCustomerName();
			}
			
		});
		
		beautician_id.setOnAction(new EventHandler() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				setBeauticianName();
			}
			
		});
		
		datePicker.setOnAction(new EventHandler() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				deriveAppointmentTime();
			}
			
		});
	}
}
