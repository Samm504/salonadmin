package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController{
	
	@FXML
	private Button loginButton;
	@FXML
	private Label loginMessage;
	@FXML
	private TextField usrTextField;
	@FXML
	private PasswordField passTextField;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void loginButtonOnAction(ActionEvent e) throws IOException{
		if(usrTextField.getText().isBlank() == false && passTextField.getText().isBlank() == false) {
			validateLogin(e);
		}else if(usrTextField.getText().isBlank() == false && passTextField.getText().isBlank() == true){
			loginMessage.setText("Please enter password!");
		}else if(usrTextField.getText().isBlank() == true && passTextField.getText().isBlank() == false){
			loginMessage.setText("Please enter username!");
		}else{
			loginMessage.setText("Please enter username and pass!");
		}
	}
	
	public void validateLogin(ActionEvent event) {
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		String verifyLogin = "SELECT count(1) FROM useraccounts WHERE username = '" + usrTextField.getText() + "' AND password = '" + passTextField.getText() +"'";
		
		try {
			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(verifyLogin);
			
			while(queryResult.next()) {
				if(queryResult.getInt(1) == 1) {
					loginMessage.setText("Success.");
					switchToDashboard(event);
				}else {
					loginMessage.setText("Invalid login. Please try again.");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void switchToDashboard(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}
}
