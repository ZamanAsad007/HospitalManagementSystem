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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AdminMainFormController implements Initializable {
    @FXML
    private Button appointment_btn;

    @FXML
    private TableView<appointmentData> appointment_tableview;

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
    private AnchorPane appointments_form;

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
    private Button doctor_btn;

    @FXML
    private Button doctor_logoutbtn;

    @FXML
    private TableColumn<doctorData, String> doctors_col_action;

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
    private TableView<doctorData> doctors_tableview;

    @FXML
    private Label nav_adminID;

    @FXML
    private Label nav_username;

    @FXML
    private Button patient_btn;

    @FXML
    private TableColumn<PatientData, String> patients_col_action;

    @FXML
    private TableColumn<PatientData, String> patients_col_contact;

    @FXML
    private TableColumn<PatientData, String> patients_col_date;

    @FXML
    private TableColumn<PatientData, String> patients_col_datedelete;

    @FXML
    private TableColumn<PatientData, String> patients_col_datemodify;

    @FXML
    private TableColumn<PatientData, String> patients_col_description;

    @FXML
    private TableColumn<PatientData, String> patients_col_gender;

    @FXML
    private TableColumn<PatientData, String> patients_col_name;

    @FXML
    private TableColumn<PatientData, String> patients_col_patientID;

    @FXML
    private TableColumn<PatientData, String> patients_col_status;

    @FXML
    private AnchorPane patients_form;

    @FXML
    private TableView<PatientData> patients_tableview;

    @FXML
    private Button payment_btn;

    @FXML
    private AnchorPane payment_form;

    @FXML
    private TextField profile_address;

    @FXML
    private AnchorPane profile_form;

    @FXML
    private AnchorPane profile_circleImage;

    @FXML
    private TextField profile_doctorID;

    @FXML
    private TextField profile_email;

    @FXML
    private ComboBox<?> profile_gender;

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
    private ComboBox<?> profile_specialization;

    @FXML
    private ComboBox<?> profile_status;

    @FXML
    private Button profile_updateBtn;

    @FXML
    private Button profilesettings_btn;

    @FXML
    private Label top_username;
    @FXML
    void logoutBtn(ActionEvent event) {

    }

    @FXML
    void profileUpdateBtn(ActionEvent event) {

    }


    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private AlertMessage alert = new AlertMessage();

    public ObservableList<doctorData> doctorGetData() {
        ObservableList<doctorData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM doctor";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            doctorData dData;
            while (result.next()) {
//                DoctorData(Integer id, String doctorID, String password, String fullName,
//            String email, String gender, Long mobileNumber, String specialized, String address,
//            String image, Date date, Date dateModify, Date dateDelete, String status)
                dData = new doctorData(result.getInt("id"), result.getString("doctor_id"),
                        result.getString("password"), result.getString("full_name"),
                        result.getString("email"), result.getString("gender"),
                        result.getLong("mobile_number"), result.getString("specialized"),
                        result.getString("address"), result.getString("image"),
                        result.getDate("date"), result.getDate("modify_date"),
                        result.getDate("delete_date"), result.getString("status"));

                listData.add(dData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    public void doctorActionButton() {

        connect = Database.connectDB();
        doctorListData = doctorGetData();

        Callback<TableColumn<doctorData, String>, TableCell<doctorData, String>> cellFactory = (TableColumn<doctorData, String> param) -> {
            final TableCell<doctorData, String> cell = new TableCell<doctorData, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Edit");
                        Button removeButton = new Button("Delete");

                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent event) -> {
                            try {

                                doctorData pData = doctors_tableview.getSelectionModel().getSelectedItem();
                                int num = doctors_tableview.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.errorMessage("Please select item first");
                                    return;
                                }

                                Data.temp_doctorID = pData.getDoctorID();
                                Data.temp_doctorName = pData.getFullName();
                                Data.temp_doctorEmail = pData.getEmail();
                                Data.temp_doctorPassword = pData.getPassword();
                                Data.temp_doctorSpecialized = pData.getSpecialized();
                                Data.temp_doctorGender = pData.getGender();
                                Data.temp_doctorMobileNumber = String.valueOf(pData.getMobileNumber());
                                Data.temp_doctorAddress = pData.getAddress();
                                Data.temp_doctorStatus = pData.getStatus();
                                Data.temp_doctorImagePath = pData.getImage();

                                // NOW LETS CREATE FXML FOR EDIT PATIENT FORM
                                Parent root = FXMLLoader.load(getClass().getResource("editDoctorForm.fxml"));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                            doctorData pData = doctors_tableview.getSelectionModel().getSelectedItem();
                            int num = doctors_tableview.getSelectionModel().getSelectedIndex();

                            if ((num - 1) < -1) {
                                alert.errorMessage("Please select item first");
                                return;
                            }

                            String deleteData = "UPDATE doctor SET delete_date = ? WHERE doctor_id = '"
                                    + pData.getDoctorID() + "'";

                            try {
                                if (alert.confirmationMessage("Are you sure you want to delete Doctor ID: " + pData.getDoctorID() + "?")) {
                                    prepare = connect.prepareStatement(deleteData);
                                    Date date = new Date();
                                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                                    prepare.setString(1, String.valueOf(sqlDate));
                                    prepare.executeUpdate();

                                    doctorGetData();
                                    alert.successMessage("Deleted Successfully!");

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox manageBtn = new HBox(editButton, removeButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };
            doctorDisplayData();
            return cell;
        };

        doctors_col_action.setCellFactory(cellFactory);
        doctors_tableview.setItems(doctorListData);

    }

     private ObservableList<doctorData> doctorListData;

    public void doctorDisplayData() {
        doctorListData = doctorGetData();

        doctors_col_doctorID.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        doctors_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        doctors_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        doctors_col_contact.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        doctors_col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        doctors_col_specialization.setCellValueFactory(new PropertyValueFactory<>("specialized"));
        doctors_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        doctors_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        doctors_tableview.setItems(doctorListData);

    }

    public ObservableList<PatientData> patientGetData() {

        ObservableList<PatientData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM patient";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            PatientData pData;

            while (result.next()) {
//                PatientsData(Integer id, Integer patientID, String password, String fullName, Long mobileNumber
//            , String address, String image, String description, String diagnosis, String treatment
//            , String doctor, String specialized, Date date, Date dateModify
//            , Date dateDelete, String status)
                pData = new PatientData(result.getInt("id"), result.getInt("patient_id"),
                        result.getString("password"), result.getString("full_name"),
                        result.getLong("mobile_number"), result.getString("gender"),
                        result.getString("address"),
                        result.getString("image"), result.getString("discription"),
                        result.getString("diagnosis"),
                        result.getString("treatment"), result.getString("doctor"),
                        result.getString("specialized"), result.getDate("date"),
                        result.getDate("date_modify"), result.getDate("date_delete"),
                        result.getString("status"));

                listData.add(pData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<PatientData> patientListData;

    public void patientDisplayData() {
        patientListData = patientGetData();

        patients_col_patientID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        patients_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        patients_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        patients_col_contact.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        patients_col_description.setCellValueFactory(new PropertyValueFactory<>("discription"));
        patients_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        patients_col_datemodify.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
        patients_col_datedelete.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
        patients_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        patients_tableview.setItems(patientListData);
    }

    public void patientActionButton() {

        connect = Database.connectDB();
        patientListData = patientGetData();

        Callback<TableColumn<PatientData, String>, TableCell<PatientData, String>> cellFactory = (TableColumn<PatientData, String> param) -> {
            final TableCell<PatientData, String> cell = new TableCell<PatientData, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Edit");
                        Button removeButton = new Button("Delete");

                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent event) -> {
                            try {

                                PatientData pData = patients_tableview.getSelectionModel().getSelectedItem();
                                int num = patients_tableview.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.errorMessage("Please select item first");
                                    return;
                                }

                                Data.temp_PatientID = pData.getPatientID();
                                Data.temp_address = pData.getAddress();
                                Data.temp_name = pData.getFullName();
                                Data.temp_gender = pData.getGender();
                                Data.temp_number = pData.getMobileNumber();
                                Data.temp_status = pData.getStatus();

                                // NOW LETS CREATE FXML FOR EDIT PATIENT FORM
                                Parent root = FXMLLoader.load(getClass().getResource("editPatientForm.fxml"));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                            PatientData pData = patients_tableview.getSelectionModel().getSelectedItem();
                            int num = patients_tableview.getSelectionModel().getSelectedIndex();

                            if ((num - 1) < -1) {
                                alert.errorMessage("Please select item first");
                                return;
                            }

                            String deleteData = "UPDATE patient SET date_delete = ? WHERE patient_id = '"
                                    + pData.getPatientID() + "'";

                            try {
                                if (alert.confirmationMessage("Are you sure you want to delete Patient ID: " + pData.getPatientID() + "?")) {
                                    prepare = connect.prepareStatement(deleteData);
                                    Date date = new Date();
                                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                                    prepare.setString(1, String.valueOf(sqlDate));
                                    prepare.executeUpdate();

                                    doctorGetData();
                                    alert.successMessage("Deleted Successfully!");

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox manageBtn = new HBox(editButton, removeButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };
            doctorDisplayData();
            return cell;
        };

        patients_col_action.setCellFactory(cellFactory);
        patients_tableview.setItems(patientListData);

    }

    public ObservableList<appointmentData> appointmentGetData() {

        ObservableList<appointmentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointment";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            appointmentData aData;
            while (result.next()) {
//            AppointmentData(Integer id, Integer appointmentID, String name, String gender,
//            Long mobileNumber, String description, String diagnosis, String treatment, String address,
//            Date date, Date dateModify, Date dateDelete, String status, Date schedule)
                aData = new appointmentData(result.getInt("id"), result.getInt("appointment_id"),
                        result.getString("name"), result.getString("gender"), result.getLong("mobile_number"),
                        result.getString("description"), result.getString("diagnosis"),
                        result.getString("treatment"), result.getString("address"),
                        result.getString("doctor"), result.getString("specialized"),
                        result.getDate("date"), result.getDate("date_modify"),
                        result.getDate("date_delete"), result.getString("status"),
                        result.getDate("schedule"));
                listData.add(aData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<appointmentData> appointmentListData;

    public void appointmentDisplayData() {
        appointmentListData = appointmentGetData();

        appointments_appointsmentsID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        appointments_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        appointments_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        appointments_contact.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        appointments_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointments_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        appointments_dateModify.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
        appointments_dateDelete.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
        appointments_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        appointment_tableview.setItems(appointmentListData);

    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == dashboard_btn) {
            dashboard_mainForm.setVisible(true);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            payment_form.setVisible(false);
            profile_form.setVisible(false);

            // dashboardAD();
            // dashboardTP();
            // dashboardAP();
            // dashboardTA();
            // dashboardGetDoctorDisplayData();

            current_form.setText("Dashboard Form");
        } else if (event.getSource() == doctor_btn) {
            dashboard_mainForm.setVisible(false);
            doctors_form.setVisible(true);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            payment_form.setVisible(false);
            profile_form.setVisible(false);

            // TO DISPLAY IMMEDIATELY THE DATA OF DOCTORS IN TABLEVIEW
            // doctorDisplayData();
            // doctorActionButton();

            current_form.setText("Doctor's Form");
        } else if (event.getSource() == patient_btn) {
            dashboard_mainForm.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(true);
            appointments_form.setVisible(false);
            payment_form.setVisible(false);
            profile_form.setVisible(false);

            // TO DISPLAY IMMEDIATELY THE DATA OF PATIENTS IN TABLEVIEW
            // patientDisplayData();
            // patientActionButton();
            current_form.setText("Patient's Form");
        } else if (event.getSource() == appointment_btn) {
            dashboard_mainForm.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(true);
            payment_form.setVisible(false);
            profile_form.setVisible(false);

            // TO DISPLAY IMMEDIATELY THE DATA OF APPOINTMENTS IN TABLEVIEW
            // appointmentDisplayData();

            current_form.setText("Appointment's Form");
        }
        else if (event.getSource() == payment_btn) {
        dashboard_mainForm.setVisible(false);
        doctors_form.setVisible(false);
        patients_form.setVisible(false);
        appointments_form.setVisible(false);
        payment_form.setVisible(true);
        profile_form.setVisible(false);

        // paymentDisplayData();

        current_form.setText("Payment Form");
        }
        else if (event.getSource() == profilesettings_btn) {
        dashboard_mainForm.setVisible(false);
        doctors_form.setVisible(false);
        patients_form.setVisible(false);
        appointments_form.setVisible(false);
        payment_form.setVisible(false);
        profile_form.setVisible(true);

        // profileStatusList();
        // profileDisplayInfo();
        // profileDisplayImages();

        current_form.setText("Profile Form");
        }

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
                tempUsername = tempUsername.substring(0, 1).toUpperCase() + tempUsername.substring(1); // TO SET THE
                                                                                                       // FIRST LETTER
                                                                                                       // TO UPPER CASE
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

        doctorDisplayData();
        doctorActionButton();

        patientDisplayData();
        patientActionButton();
        
    }

}
