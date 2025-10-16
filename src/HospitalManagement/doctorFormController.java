package HospitalManagement;

import java.net.URL;
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
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class doctorFormController implements Initializable {

    @FXML
    private TextField appointment_address;

    @FXML
    private TextField appointment_appointID;

    @FXML
    private Button appointment_btn;

    @FXML
    private Button appointment_clearbtn;

    @FXML
    private Button appointment_deletebtn;

    @FXML
    private TextField appointment_description;

    @FXML
    private TextField appointment_diagnosis;

    @FXML
    private ComboBox<?> appointment_gender;

    @FXML
    private Button appointment_insertbtn;

    @FXML
    private TextField appointment_mobile;

    @FXML
    private TextField appointment_name;

    @FXML
    private ComboBox<?> appointment_status;

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
    private Label dashboard_TA;

    @FXML
    private Label dashboard_TP;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_mainForm;

    @FXML
    private TableView<?> dashboard_tableview;

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
    private ComboBox<?> patients_patientGender;

    @FXML
    private TextField patients_patientID;

    @FXML
    private TextField patients_patientName;

    @FXML
    private Button profile_btn;

    @FXML
    private Label top_username;
    private Connection connect;
    private PreparedStatement prepare;
    private java.sql.Statement statement;
    private ResultSet result;
    private final AlertMessage alert = new AlertMessage();

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
//            Integer appointmentID, String name, String gender,
//            Long mobileNumber, String description, String diagnosis, String treatment, String address,
//            Date date, Date dateModify, Date dateDelete, String status, Date schedule

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

        AppointmentData appData = appointments_tableView.getSelectionModel().getSelectedItem();
        int num = appointments_tableView.getSelectionModel().getSelectedIndex();

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

//        CHECK IF THE FIELD(S) ARE EMPTY
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        displayAdminIDNumberName();
        runTime();

        appointmentShowData();
        appointmentGenderList();
        appointmentStatusList();
        appointmentAppointmentID();
        
    }
    
}
