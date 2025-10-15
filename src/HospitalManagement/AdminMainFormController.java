package HospitalManagement;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.mysql.cj.xdevapi.Statement;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class AdminMainFormController implements Initializable{
    @FXML
    private Button appointment_btn;

    @FXML
    private AnchorPane appointment_form;

    @FXML
    private TableView<?> appointment_tableview;

    @FXML
    private TableColumn<?, ?> appointments_Action;

    @FXML
    private TableColumn<?, ?> appointments_appointsmentsID;

    @FXML
    private TableColumn<?, ?> appointments_contact;

    @FXML
    private TableColumn<?, ?> appointments_date;

    @FXML
    private TableColumn<?, ?> appointments_dateDelete;

    @FXML
    private TableColumn<?, ?> appointments_dateModify;

    @FXML
    private TableColumn<?, ?> appointments_description;

    @FXML
    private TableColumn<?, ?> appointments_gender;

    @FXML
    private TableColumn<?, ?> appointments_name;

    @FXML
    private TableColumn<?, ?> appointments_status;

    @FXML
    private Label current_form;

    @FXML
    private BarChart<?, ?> dash_chart_DD;

    @FXML
    private AreaChart<?, ?> dash_chart_PD;

    @FXML
    private TableColumn<?, ?> dash_col_doctorID;

    @FXML
    private TableColumn<?, ?> dash_col_name;

    @FXML
    private TableColumn<?, ?> dash_col_specialized;

    @FXML
    private TableColumn<?, ?> dash_col_status;

    @FXML
    private Label dashboard_AD;

    @FXML
    private Label dashboard_AP;

    @FXML
    private Label dashboard_TP;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private TableView<?> dashboard_tableview;

    @FXML
    private Label date_time;

    @FXML
    private Button doctor_btn;

    @FXML
    private TableColumn<?, ?> doctors_col_action;

    @FXML
    private TableColumn<?, ?> doctors_col_address;

    @FXML
    private TableColumn<?, ?> doctors_col_contact;

    @FXML
    private TableColumn<?, ?> doctors_col_doctorID;

    @FXML
    private TableColumn<?, ?> doctors_col_email;

    @FXML
    private TableColumn<?, ?> doctors_col_gender;

    @FXML
    private TableColumn<?, ?> doctors_col_name;

    @FXML
    private TableColumn<?, ?> doctors_col_specialization;

    @FXML
    private TableColumn<?, ?> doctors_col_status;

    @FXML
    private AnchorPane doctors_form;

    @FXML
    private TableView<?> doctors_tableview;

    @FXML
    private Label nav_adminID;

    @FXML
    private Label nav_username;

    @FXML
    private Button patient_btn;

    @FXML
    private TableColumn<?, ?> patients_col_action;

    @FXML
    private TableColumn<?, ?> patients_col_contact;

    @FXML
    private TableColumn<?, ?> patients_col_date;

    @FXML
    private TableColumn<?, ?> patients_col_datedelete;

    @FXML
    private TableColumn<?, ?> patients_col_datemodify;

    @FXML
    private TableColumn<?, ?> patients_col_description;

    @FXML
    private TableColumn<?, ?> patients_col_gender;

    @FXML
    private TableColumn<?, ?> patients_col_name;

    @FXML
    private TableColumn<?, ?> patients_col_patientID;

    @FXML
    private TableColumn<?, ?> patients_col_status;

    @FXML
    private AnchorPane patients_form;

    @FXML
    private TableView<?> patients_tableview;

    @FXML
    private Label top_username;

     private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

     private AlertMessage alert = new AlertMessage();


     public void switchForm(ActionEvent event) {

        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointment_form.setVisible(false);
            // payment_form.setVisible(false);
            // profile_form.setVisible(false);

            // dashboardAD();
            // dashboardTP();
            // dashboardAP();
            // dashboardTA();
            // dashboardGetDoctorDisplayData();

            current_form.setText("Dashboard Form");
        } else if (event.getSource() == doctor_btn) {
            dashboard_form.setVisible(false);
            doctors_form.setVisible(true);
            patients_form.setVisible(false);
            appointment_form.setVisible(false);
            // payment_form.setVisible(false);
            // profile_form.setVisible(false);

            // TO DISPLAY IMMEDIATELY THE DATA OF DOCTORS IN TABLEVIEW
            // doctorDisplayData();
            // doctorActionButton();

            current_form.setText("Doctor's Form");
        } else if (event.getSource() == patient_btn) {
            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(true);
            appointment_form.setVisible(false);
            // payment_form.setVisible(false);
            // profile_form.setVisible(false);

            // TO DISPLAY IMMEDIATELY THE DATA OF PATIENTS IN TABLEVIEW
            // patientDisplayData();
            // patientActionButton();
            current_form.setText("Patient's Form");
        } else if (event.getSource() == appointment_btn) {
            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointment_form.setVisible(true);
            // payment_form.setVisible(false);
            // profile_form.setVisible(false);

            // TO DISPLAY IMMEDIATELY THE DATA OF APPOINTMENTS IN TABLEVIEW
            // appointmentDisplayData();

            current_form.setText("Appointment's Form");
        } 
        // else if (event.getSource() == payment_btn) {
        //     dashboard_form.setVisible(false);
        //     doctors_form.setVisible(false);
        //     patients_form.setVisible(false);
        //     appointment_form.setVisible(false);
        //     // payment_form.setVisible(true);
        //     // profile_form.setVisible(false);

        //     // paymentDisplayData();

        //     current_form.setText("Payment Form");
        // }
        //  else if (event.getSource() == profile_btn) {
        // //     dashboard_form.setVisible(false);
        // //     doctors_form.setVisible(false);
        //     patients_form.setVisible(false);
        //     appointment_form.setVisible(false);
        //     // payment_form.setVisible(false);
        //     // profile_form.setVisible(true);

        //     // profileStatusList();
        //     // profileDisplayInfo();
        //     // profileDisplayImages();

        //     current_form.setText("Profile Form");
        // }

    }
    public void runTime() {

        new Thread() {

            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                while (true) {
                    try {

                        Thread.sleep(1000); // 1000 ms = 1s

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Platform.runLater(() -> {
                        date_time.setText(format.format(new Date()));
                    });
                }
            }
        }.start();

    }
    
    public void displayAdminIDUsername() {

        String sql = "SELECT * FROM admin WHERE username = '"
                + Data.admin_username + "'";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                nav_adminID.setText(result.getString("admin_id"));
                String tempUsername = result.getString("username");
                tempUsername = tempUsername.substring(0, 1).toUpperCase() + tempUsername.substring(1); // TO SET THE FIRST LETTER TO UPPER CASE
                nav_username.setText(tempUsername);
                top_username.setText(tempUsername);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
       runTime();
       displayAdminIDUsername();
    }
    
}
