package HospitalManagement;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.mysql.cj.xdevapi.Statement;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class doctorFormController implements Initializable {

    @FXML
    private TextField appointment_address;

    @FXML
    private TextField appointment_appointID;

    @FXML
    private Button appointment_btn;

    @FXML
    private Button doctor_logoutbtn;

    @FXML
    private Button appointment_clearbtn;

    @FXML
    private Button appointment_deletebtn;

    @FXML
    private TextField appointment_description;

    @FXML
    private TextField appointment_diagnosis;

    @FXML
    private ComboBox<String> appointment_gender;

    @FXML
    private Button appointment_insertbtn;

    @FXML
    private TextField appointment_mobile;

    @FXML
    private TextField appointment_name;

    @FXML
    private ComboBox<String> appointment_status;

    @FXML
    private TableView<appointmentData> appointment_tableview;

    @FXML
    private TextField appointment_treatment;
    @FXML
    private DatePicker appointment_schedule;

    @FXML
    private Button appointment_updatebtn;

    @FXML
    private TableColumn<appointmentData, String> appointments_col_Action;

    @FXML
    private TableColumn<appointmentData, String> appointments_col_appointsmentsID;

    @FXML
    private TableColumn<appointmentData, String> appointments_col_contact;

    @FXML
    private TableColumn<appointmentData, String> appointments_col_date;

    @FXML
    private TableColumn<appointmentData, String> appointments_col_dateDelete;

    @FXML
    private TableColumn<appointmentData, String> appointments_col_dateModify;

    @FXML
    private TableColumn<appointmentData, String> appointments_col_description;

    @FXML
    private TableColumn<appointmentData, String> appointments_col_gender;

    @FXML
    private TableColumn<appointmentData, String> appointments_col_name;

    @FXML
    private TableColumn<appointmentData, String> appointments_col_status;

    @FXML
    private AnchorPane appointments_form;

    @FXML
    private Label current_form;

     @FXML
    private BarChart<appointmentData, String> dash_chart_NA;

    @FXML
    private AreaChart<appointmentData, String> dash_chart_NP;

    @FXML
    private TableColumn<appointmentData, String> dash_col_appointID;

    @FXML
    private TableColumn<appointmentData, String> dash_col_name;

    @FXML
    private TableColumn<appointmentData, String> dash_col_description;
    @FXML
    private TableColumn<appointmentData, String> dash_col_appointDate;

    @FXML
    private TableColumn<appointmentData, String> dash_col_status;

    @FXML
    private Label dashboard_AP;

    @FXML
    private Label dashboard_IP;

    @FXML
    private Label dashboard_TA;

    @FXML
    private Label dashboard_TP;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_mainForm;

    @FXML
    private TableView<appointmentData> dashboard_tableview;

    @FXML
    private Label date_time;

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
    private AnchorPane profile_form;

    @FXML
    private TableView<?> doctors_tableview;

    @FXML
    private Label nav_adminID;

    @FXML
    private Label nav_username;

    @FXML
    private Button patient_btn;

    @FXML
    private Label patients_PA_dateCreated;

    @FXML
    private Label patients_PA_password;

    @FXML
    private Label patients_PA_patientID;

    @FXML
    private Button patients_PI_addbtn;

    @FXML
    private Label patients_PI_address;

    @FXML
    private Label patients_PI_mobileNumber;

    @FXML
    private Button patients_confirmbtn;

    @FXML
    private Label patients_PI_patientGender;

    @FXML
    private Label patients_PI_patientName;

    @FXML
    private Button patients_PI_recordbtn;

    @FXML
    private TextField patients_address;

    @FXML
    private AnchorPane patients_form;

    @FXML
    private TextField patients_mobile;

    @FXML
    private TextField patients_password;

    @FXML
    private ComboBox<String> patients_patientGender;

    @FXML
    private TextField patients_patientID;

    @FXML
    private TextField patients_patientName;

    @FXML
    private Button profile_btn;
     @FXML
    private TextField profile_address;

    

    @FXML
    private AnchorPane profile_circleImage;

    @FXML
    private TextField profile_doctorID;

    @FXML
    private TextField profile_email;

    @FXML
    private ComboBox<String> profile_gender;

    @FXML
    private Button profile_importBtn;

    @FXML
    private Label profile_label_dateCreated;

    @FXML
    private Label profile_label_doctorID;

    @FXML
    private Label profile_label_email;

    @FXML
    private Label profile_label_name;

    @FXML
    private TextField profile_mobile;

    @FXML
    private TextField profile_name;

    @FXML
    private ComboBox<String> profile_specialization;

    @FXML
    private ComboBox<String> profile_status;

    @FXML
    private Button profile_updateBtn;

    @FXML
    private Label top_username;
    private Connection connect;
    private PreparedStatement prepare;
    private java.sql.Statement statement;
    private ResultSet result;
    private final AlertMessage alert = new AlertMessage();

    
    public void dashbboardDisplayIP() {
        String sql = "SELECT COUNT(id) FROM patient WHERE status = 'Inactive' AND doctor = '"
                + Data.doctor_id + "'";
        connect = Database.connectDB();
        int getIP = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                getIP = result.getInt("COUNT(id)");
            }
            dashboard_IP.setText(String.valueOf(getIP));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void dashbboardDisplayTP() {
        String sql = "SELECT COUNT(id) FROM patient WHERE doctor = '"
                + Data.doctor_id + "'";
        connect = Database.connectDB();
        int getTP = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                getTP = result.getInt("COUNT(id)");
            }
            dashboard_TP.setText(String.valueOf(getTP));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void dashbboardDisplayAP() {
        String sql = "SELECT COUNT(id) FROM patient WHERE status = 'Active' AND doctor = '"
                + Data.doctor_id + "'";
        connect = Database.connectDB();
        int getAP = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                getAP = result.getInt("COUNT(id)");
            }
            dashboard_TP.setText(String.valueOf(getAP));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void dashbboardDisplayTA() {
        String sql = "SELECT COUNT(id) FROM appointment WHERE status = 'Active' AND doctor = '"
                + Data.doctor_id + "'";
        connect = Database.connectDB();
        int getTA = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                getTA = result.getInt("COUNT(id)");
            }
            dashboard_TA.setText(String.valueOf(getTA));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ObservableList<appointmentData> dashboardAppointmentTableView() {

        ObservableList<appointmentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointment WHERE doctor = '"
                + Data.doctor_id + "'";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            appointmentData aData;
            while (result.next()) {
                aData = new appointmentData(result.getInt("appointment_id"),
                        result.getString("name"), result.getString("description"),
                        result.getDate("date"), result.getString("status"));

                listData.add(aData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

      private ObservableList<appointmentData> dashboardGetData;

    public void dashboardDisplayData() {
        dashboardGetData = dashboardAppointmentTableView();

        dash_col_appointID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        dash_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        dash_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        dash_col_appointDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        dash_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        dashboard_tableview.setItems(dashboardGetData);
    }

    public void dashboardNOP() {

        dash_chart_NP.getData().clear();

        String sql = "SELECT date, COUNT(id) FROM patient WHERE doctor = '"
                + Data.doctor_id + "' GROUP BY TIMESTAMP(date) ASC LIMIT 8";
        connect = Database.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series<>();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dash_chart_NP.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardNOA() {

        dash_chart_NA.getData().clear();

        String sql = "SELECT date, COUNT(id) FROM appointment WHERE doctor = '"
                + Data.doctor_id + "' GROUP BY TIMESTAMP(date) ASC LIMIT 7";
        connect = Database.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series<>();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dash_chart_NA.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void switchForm(ActionEvent event) {
        if (event.getSource() == dashboard_btn) {
            dashboard_mainForm.setVisible(true);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);
        } else if (event.getSource() == patient_btn) {
            dashboard_mainForm.setVisible(false);
            patients_form.setVisible(true);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);
        } else if (event.getSource() == appointment_btn) {
            dashboard_mainForm.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(true);
            profile_form.setVisible(false);
        } else if (event.getSource() == profile_btn) {
            dashboard_mainForm.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(true);
        }
    }

    public void patientConfirmBtn() {

        // CHECK IF SOME OR ALL FIELDS ARE EMPTY
        if (patients_patientID.getText().isEmpty()
                || patients_patientName.getText().isEmpty()
                || patients_patientGender.getSelectionModel().getSelectedItem() == null
                || patients_mobile.getText().isEmpty()
                || patients_password.getText().isEmpty()
                || patients_address.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            // TO DISPLAY THE DATA FROM PERSONAL ACCOUNT
            patients_PA_patientID.setText(patients_patientID.getText());
            patients_PA_password.setText(patients_password.getText());
            patients_PA_dateCreated.setText(String.valueOf(sqlDate));

            // TO DISPLAY THE DATA FROM PERSONAL INFORMATION
            patients_PI_patientName.setText(patients_patientName.getText());
            patients_PI_patientGender.setText(patients_patientGender.getSelectionModel().getSelectedItem());
            patients_PI_mobileNumber.setText(patients_mobile.getText());
            patients_PI_address.setText(patients_address.getText());
        }

    }

    public void patientAddBtn() {

        if (patients_PA_patientID.getText().isEmpty()
                || patients_PA_password.getText().isEmpty()
                || patients_PA_dateCreated.getText().isEmpty()
                || patients_PI_patientName.getText().isEmpty()
                || patients_PI_patientGender.getText().isEmpty()
                || patients_PI_mobileNumber.getText().isEmpty()
                || patients_PI_address.getText().isEmpty()) {
            alert.errorMessage("Something wenr wrong");
        } else {

            Database.connectDB();
            try {
                String doctorName = "";
                String doctorSpecialized = "";

                String getDoctor = "SELECT * FROM doctor WHERE doctor_id = '"
                        + nav_adminID.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(getDoctor);

                if (result.next()) {
                    doctorName = result.getString("full_name");
                    doctorSpecialized = result.getString("specialized");
                }
                // CHECK IF THE PATIENT ID THAT THE DOCTORS WANT TO INSERT/ADD IS EXISTING
                // ALREADY
                String checkPatientID = "SELECT * FROM patient WHERE patient_id = '"
                        + patients_PA_patientID.getText() + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(checkPatientID);
                if (result.next()) {
                    alert.errorMessage(patients_PA_patientID.getText() + " is already exist");
                } else {

                    String insertData = "INSERT INTO patient (patient_id, password, full_name, gender, mobile_number, "
                            + "address, doctor, specialized, date, status) "
                            + "VALUES(?,?,?,?,?,?,?,?,?,?)";
                    prepare = connect.prepareStatement(insertData);

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(1, patients_PA_patientID.getText());
                    prepare.setString(2, patients_PA_password.getText());
                    prepare.setString(3, patients_PI_patientName.getText());
                    prepare.setString(4, patients_PI_patientGender.getText()); // ✅ make sure gender comes here
                    prepare.setString(5, patients_PI_mobileNumber.getText());
                    prepare.setString(6, patients_PI_address.getText());
                    prepare.setString(7, nav_adminID.getText());
                    prepare.setString(8, doctorSpecialized);
                    prepare.setString(9, "" + sqlDate);
                    prepare.setString(10, "Confirm");

                    prepare.executeUpdate();

                    alert.successMessage("Added successfully!");
                    // TO CLEAR ALL FIELDS AND SOME LABELS
                    patientClearFields();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            // NOW, LETS TRY
        }
    }

    public void patientRecordBtn() {
        try {
            // LINK THE NAME OF YOUR FXML FOR RECORD PAGE
            Parent root = FXMLLoader.load(getClass().getResource("recordPage.fxml"));
            Stage stage = new Stage();

            stage.setTitle("Hospital Management System | Record of Patients");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void patientClearFields() {
        patients_patientID.clear();
        patients_patientName.clear();
        patients_patientGender.getSelectionModel().clearSelection();
        patients_mobile.clear();
        patients_password.clear();
        patients_address.clear();

        patients_PA_patientID.setText("");
        patients_PA_password.setText("");
        patients_PA_dateCreated.setText("");

        patients_PI_patientName.setText("");
        patients_PI_patientGender.setText("");
        patients_PI_mobileNumber.setText("");
        patients_PI_address.setText("");
    }

    private void patientGenderList() {

        List<String> listG = new ArrayList<>();

        for (String data : Data.gender) {
            listG.add(data);
        }
        ObservableList listData = FXCollections.observableList(listG);

        patients_patientGender.setItems(listData);

    }

    public ObservableList<appointmentData> appointmentGetData() {

        ObservableList<appointmentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointment WHERE date_delete IS NULL and doctor = '"
                + Data.doctor_id + "'";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            appointmentData appData;

            while (result.next()) {
                // Integer appointmentID, String name, String gender,
                // Long mobileNumber, String description, String diagnosis, String treatment,
                // String address,
                // Date date, Date dateModify, Date dateDelete, String status, Date schedule

                appData = new appointmentData(result.getInt("appointment_id"),
                        result.getString("name"), result.getString("gender"),
                        result.getLong("mobile_number"), result.getString("description"),
                        result.getString("diagnosis"), result.getString("treatment"),
                        result.getString("address"), result.getDate("date"),
                        result.getDate("date_modify"), result.getDate("date_delete"),
                        result.getString("status"), result.getDate("schedule"));
                // STORE ALL DATA
                listData.add(appData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<appointmentData> appoinmentListData;

    public void appointmentGenderList() {
        List<String> listG = new ArrayList<>();

        for (String data : Data.gender) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        appointment_gender.setItems(listData);

    }

    private Integer appointmentID;

    public void appointmentGetAppointmentID() {
        String sql = "SELECT MAX(appointment_id) FROM appointment";
        connect = Database.connectDB();

        int tempAppID = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                tempAppID = result.getInt("MAX(appointment_id)");
            }
            if (tempAppID == 0) {
                tempAppID += 1;
            } else {
                tempAppID += 1;
            }
            appointmentID = tempAppID;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void appointmentStatusList() {
        List<String> listS = new ArrayList<>();

        for (String data : Data.status) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        appointment_status.setItems(listData);

    }

    public void appointmentAppointmentID() {
        appointmentGetAppointmentID();

        appointment_appointID.setText("" + appointmentID);
        appointment_appointID.setDisable(true);

    }

    public void appointmentShowData() {
        appoinmentListData = appointmentGetData();

        appointments_col_appointsmentsID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        appointments_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        appointments_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        appointments_col_contact.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        appointments_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointments_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        appointments_col_dateModify.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
        appointments_col_dateDelete.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
        appointments_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        appointment_tableview.setItems(appoinmentListData);
    }

    public void displayAdminIDNumberName() {

        String name = Data.doctor_name;
        name = name.substring(0, 1).toUpperCase() + name.substring(1);

        nav_username.setText(name);
        nav_adminID.setText(Data.doctor_id);
        top_username.setText(name);

    }

    public void runTime() {
        new Thread() {
            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                while (true) {
                    try {
                        Thread.sleep(1000);
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

    public void appointmentSelect() {

        appointmentData appData = appointment_tableview.getSelectionModel().getSelectedItem();
        int num = appointment_tableview.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        appointment_appointID.setText("" + appData.getAppointmentID());
        appointment_name.setText(appData.getName());
        appointment_gender.getSelectionModel().select(appData.getGender());
        appointment_mobile.setText("" + appData.getMobileNumber());
        appointment_description.setText(appData.getDescription());
        appointment_diagnosis.setText(appData.getDiagnosis());
        appointment_treatment.setText(appData.getTreatment());
        appointment_address.setText(appData.getAddress());
        appointment_status.getSelectionModel().select(appData.getStatus());

    }

    public void appointmentInsertBtn() {

        // CHECK IF THE FIELD(S) ARE EMPTY
        if (appointment_appointID.getText().isEmpty()
                || appointment_name.getText().isEmpty()
                || appointment_gender.getSelectionModel().getSelectedItem() == null
                || appointment_mobile.getText().isEmpty()
                || appointment_description.getText().isEmpty()
                || appointment_address.getText().isEmpty()
                || appointment_status.getSelectionModel().getSelectedItem() == null
                || appointment_schedule.getValue() == null) {
            alert.errorMessage("Please fill the blank fields");
        } else {
            String checkAppointmentID = "SELECT * FROM appointment WHERE appointment_id = "
                    + appointment_appointID.getText();
            connect = Database.connectDB();
            try {
                statement = connect.createStatement();
                result = statement.executeQuery(checkAppointmentID);

                if (result.next()) {
                    alert.errorMessage(appointment_appointID.getText() + " was already taken");
                } else {
                    String getSpecialized = "";
                    String getDoctorData = "SELECT * FROM doctor WHERE doctor_id = '"
                            + Data.doctor_id + "'";

                    statement = connect.createStatement();
                    result = statement.executeQuery(getDoctorData);

                    if (result.next()) {
                        getSpecialized = result.getString("specialized");
                    }

                    String insertData = "INSERT INTO appointment (appointment_id, name, gender"
                            + ", description, diagnosis, treatment, mobile_number"
                            + ", address, date, status, doctor, specialized, schedule) "
                            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    prepare = connect.prepareStatement(insertData);

                    prepare.setString(1, appointment_appointID.getText());
                    prepare.setString(2, appointment_name.getText());
                    prepare.setString(3, (String) appointment_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(4, appointment_description.getText());
                    prepare.setString(5, appointment_diagnosis.getText());
                    prepare.setString(6, appointment_treatment.getText());
                    prepare.setString(7, appointment_mobile.getText());
                    prepare.setString(8, appointment_address.getText());

                    java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

                    prepare.setString(9, "" + sqlDate);
                    prepare.setString(10, (String) appointment_status.getSelectionModel().getSelectedItem());
                    prepare.setString(11, Data.doctor_id);
                    prepare.setString(12, getSpecialized);
                    prepare.setString(13, "" + appointment_schedule.getValue());

                    prepare.executeUpdate();

                    appointmentShowData();
                    appointmentAppointmentID();
                    appointmentClearBtn();
                    alert.successMessage("Successully added!");

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void appointmentUpdateBtn() {

        if (appointment_appointID.getText().isEmpty()
                || appointment_name.getText().isEmpty()
                || appointment_gender.getSelectionModel().getSelectedItem() == null
                || appointment_mobile.getText().isEmpty()
                || appointment_description.getText().isEmpty()
                || appointment_address.getText().isEmpty()
                || appointment_status.getSelectionModel().getSelectedItem() == null
                || appointment_schedule.getValue() == null) {
            alert.errorMessage("Please fill the blank fields");
        } else {
            // TO GET THE DATE TODAY
            java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

            String updateData = "UPDATE appointment SET name = '"
                    + appointment_name.getText() + "', gender = '"
                    + appointment_gender.getSelectionModel().getSelectedItem() + "', mobile_number = '"
                    + appointment_mobile.getText() + "', description = '"
                    + appointment_description.getText() + "', address = '"
                    + appointment_address.getText() + "', status = '"
                    + appointment_status.getSelectionModel().getSelectedItem() + "', schedule = '"
                    + appointment_schedule.getValue() + "', date_modify = '"
                    + sqlDate + "' WHERE appointment_id = '"
                    + appointment_appointID.getText() + "'";

            connect = Database.connectDB();

            try {
                if (alert.confirmationMessage("Are you sure you want to UPDATE Appointment ID: "
                        + appointment_appointID.getText() + "?")) {
                    prepare = connect.prepareStatement(updateData);
                    prepare.executeUpdate();

                    appointmentShowData();
                    appointmentAppointmentID();
                    appointmentClearBtn();
                    alert.successMessage("Successully Updated!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void appointmentDeleteBtn() {

        if (appointment_appointID.getText().isEmpty()) {
            alert.errorMessage("Please select the item first");
        } else {

            String updateData = "UPDATE appointment SET date_delete = ? WHERE appointment_id = '"
                    + appointment_appointID.getText() + "'";

            connect = Database.connectDB();

            try {
                java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

                if (alert.confirmationMessage("Are you sure you want to DELETE Appointment ID: "
                        + appointment_appointID.getText() + "?")) {
                    prepare = connect.prepareStatement(updateData);

                    prepare.setString(1, String.valueOf(sqlDate));
                    prepare.executeUpdate();

                    appointmentShowData();
                    appointmentAppointmentID();
                    appointmentClearBtn();

                    alert.successMessage("Successully Updated!");
                } else {
                    alert.errorMessage("Cancelled.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    // TO CLEAR ALL FIELDS
    public void appointmentClearBtn() {
        appointment_appointID.clear();
        appointment_name.clear();
        appointment_gender.getSelectionModel().clearSelection();
        appointment_mobile.clear();
        appointment_description.clear();
        appointment_treatment.clear();
        appointment_diagnosis.clear();
        appointment_address.clear();
        appointment_status.getSelectionModel().clearSelection();
        appointment_schedule.setValue(null);
    }

    public void profileLabels() {
        String selectData = "SELECT * FROM doctor WHERE doctor_id = '"
                + Data.doctor_id + "'";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if (result.next()) {
                profile_label_doctorID.setText(result.getString("doctor_id"));
                profile_label_name.setText(result.getString("full_name"));
                profile_label_email.setText(result.getString("email"));
                profile_label_dateCreated.setText(result.getString("date"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // public void profileChangeProfile() {

    //     FileChooser open = new FileChooser();
    //     open.getExtensionFilters().add(new ExtensionFilter("Open Image", "*png", "*jpg", "*jpeg"));

    //     File file = open.showOpenDialog(profile_importBtn.getScene().getWindow());

    //     if (file != null) {
    //         Data.path = file.getAbsolutePath();

    //         image = new Image(file.toURI().toString(), 128, 103, false, true);
    //         profile_circleImage.setFill(new ImagePattern(image));
    //     }

    // }
    public void profileUpdateBtn() {

        connect = Database.connectDB();

        if (profile_doctorID.getText().isEmpty()
                || profile_name.getText().isEmpty()
                || profile_email.getText().isEmpty()
                || profile_gender.getSelectionModel().getSelectedItem() == null
                || profile_mobile.getText().isEmpty()
                || profile_address.getText().isEmpty()
                || profile_specialization.getSelectionModel().getSelectedItem() == null
                || profile_status.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            // CHECK IF THE PATH IS NULL 
            if (Data.path == null || "".equals(Data.path)) {
                String updateData = "UPDATE doctor SET full_name = ?, email = ?"
                        + ", gender = ?, mobile_number = ?, address = ?, specialized = ?, status = ?, modify_date = ?"
                        + " WHERE doctor_id = '"
                        + Data.doctor_id + "'";
                try {
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, profile_name.getText());
                    prepare.setString(2, profile_email.getText());
                    prepare.setString(3, profile_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(4, profile_mobile.getText());
                    prepare.setString(5, profile_address.getText());
                    prepare.setString(6, profile_specialization.getSelectionModel().getSelectedItem());
                    prepare.setString(7, profile_status.getSelectionModel().getSelectedItem());
                    prepare.setString(8, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert.successMessage("Updated Successfully!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                String updateData = "UPDATE doctor SET full_name = ?, email = ?"
                        + ", gender = ?, mobile_number = ?, address = ?, image = ?, specialized = ?, status = ?, modify_date = ?"
                        + " WHERE doctor_id = '"
                        + Data.doctor_id + "'";
                try {
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, profile_name.getText());
                    prepare.setString(2, profile_email.getText());
                    prepare.setString(3, profile_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(4, profile_mobile.getText());
                    prepare.setString(5, profile_address.getText());
                    String path = Data.path;
                    path = path.replace("\\", "\\\\");
                    Path transfer = Paths.get(path);

                    // LINK YOUR DIRECTORY FOLDER
                    Path copy = Paths.get("C:\\Users\\WINDOWS 10\\Documents\\NetBeansProjects\\HospitalManagementSystem\\src\\Directory\\"
                            + Data.doctor_id + ".jpg");

                    try {
                        // TO PUT THE IMAGE FILE TO YOUR DIRECTORY FOLDER
                        Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    prepare.setString(6, copy.toAbsolutePath().toString());
                    prepare.setString(7, profile_specialization.getSelectionModel().getSelectedItem());
                    prepare.setString(8, profile_status.getSelectionModel().getSelectedItem());
                    prepare.setString(9, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert.successMessage("Updated Successfully!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void profileFields() {
        String selectData = "SELECT * FROM doctor WHERE doctor_id = '"
                + Data.doctor_id + "'";

        connect = Database.connectDB();
        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if (result.next()) {
                profile_doctorID.setText(result.getString("doctor_id"));
                profile_name.setText(result.getString("full_name"));
                profile_email.setText(result.getString("email"));
                profile_gender.getSelectionModel().select(result.getString("gender"));
                profile_mobile.setText(result.getString("mobile_number"));
                profile_address.setText(result.getString("address"));
                profile_specialization.getSelectionModel().select(result.getString("specialized"));
                profile_status.getSelectionModel().select(result.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void profileGenderList() {

        List<String> listG = new ArrayList<>();

        for (String data : Data.gender) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        profile_gender.setItems(listData);

    }

    private String[] specialization = {"Allergist", "Dermatologist", "Ophthalmologist", "Gynecologist", "Cardiologist"};

    public void profileSpecializedList() {

        List<String> listS = new ArrayList<>();

        for (String data : specialization) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        profile_specialization.setItems(listData);
    }

    public void profileStatusList() {

        List<String> listS = new ArrayList<>();

        for (String data : Data.status) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        profile_status.setItems(listData);
    }
    
    public void logoutBtn() {

        try {
            if (alert.confirmationMessage("Are you sure you want to logout?")) {
                Data.doctor_id = "";
                Data.doctor_name = "";
                Parent root = FXMLLoader.load(getClass().getResource("DoctorPage.fxml"));
                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.show();

                // TO HIDE YOUR MAIN FORM
                doctor_logoutbtn.getScene().getWindow().hide();

                Data.doctor_id = "";
                Data.doctor_name = "";
                Data.temp_PatientID = 0;
                Data.temp_name = "";
                Data.temp_gender = "";
                Data.temp_number = Long.parseLong("0");
                Data.temp_address = "";
                Data.temp_status = "";
                Data.temp_date = "";
                Data.temp_path = "";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        displayAdminIDNumberName();
        runTime();

        dashbboardDisplayIP();
        dashbboardDisplayTP();
        dashbboardDisplayAP();
        dashbboardDisplayTA();
        dashboardDisplayData();
        dashboardNOP();
        dashboardNOA();

        appointmentShowData();
        appointmentGenderList();
        appointmentStatusList();
        appointmentAppointmentID();

        patientGenderList();

        profileLabels();
        profileFields(); // TO DISPLAY ALL DETAILS TO THE FIELDS
        profileGenderList();
        profileSpecializedList();
        profileStatusList();

    }

}
