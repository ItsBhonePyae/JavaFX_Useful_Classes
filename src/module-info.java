/**
 * 
 */
/**
 * 
 */
module Library_Management_System_16G {
	
	
	requires java.sql;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.swing;
	requires javafx.web;
	requires javafx.media;
	requires mysql.connector.j;
	
	requires java.xml;	
	opens controller to javafx.graphics, javafx.fxml;
	opens model to javafx.base, javafx.fxml;
	opens view to javafx.graphics, javafx.fxml;
}

//--module-path "C:\Users\lenovo\Downloads\javafx-26_windows-x64_bin-sdk\javafx-sdk-26.0.2\lib" --add-modules javafx.controls --enable-native-access=javafx.graphics