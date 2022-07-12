module SalonProjectAdmin {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires java.sql;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
	opens models to javafx.fxml;
	
	exports models;
}
