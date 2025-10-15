package HospitalManagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

 
public class HospitalManagementSystem extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/HospitalManagement/fx.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setMinWidth(406);
        stage.setMinHeight(530);
        
        stage.setTitle("Hospital Management System");
        
        stage.setScene(scene);
        stage.show();
    }

     
    
}
