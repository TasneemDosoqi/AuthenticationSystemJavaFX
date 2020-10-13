package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Label errorDashboardLabel;
    @FXML
    private TextField userID;
    @FXML
    private TextField username;
    @FXML
    private TextField userPassword;
    @FXML
    private TextField userAge;
    @FXML
    private TextField userEmail;
    @FXML
    private TextField userWorkTitle;
    @FXML
    private Label IDLabel;
    @FXML
    private Label NameLabel;
    @FXML
    private Label PasswordLabel;
    @FXML
    private Label EmailLabel;
    @FXML
    private Label AgeLabel;
    @FXML
    private Label workTitleLabel;

    int index;

    @FXML
    ListView<String> usersListView = new ListView<String>();

    public void setCancelButton(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void setDeleteButton(ActionEvent event) {
        String usernamedeleted = User.localUsers.get(index).name;
        User.localUsers.remove(index);
        usersListView.getItems().remove(index);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(usernamedeleted + " Deleted Successfully ");
        alert.setContentText(null);
        alert.setTitle(null);
        alert.show();
        errorDashboardLabel.setText("");

    }

    public void setUpdateButton(ActionEvent event) {

        if (checkFormValidation()==false){
            return;
        }

        if (User.localUsers.get(index).userID.equals(userID.getText())) {

            User.localUsers.get(index).name = username.getText() ;
            User.localUsers.get(index).userEmail = userEmail.getText();
            User.localUsers.get(index).password = userPassword.getText();
            User.localUsers.get(index).age = userAge.getText();
            User.localUsers.get(index).WorkTitle = userWorkTitle.getText();


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(User.localUsers.get(index).name + " Updated Successfully ");
            alert.setContentText(" the user new information is : \n ID : " + User.localUsers.get(index).userID
                    + "\nname : " + User.localUsers.get(index).name
                    + "\nemail : " + User.localUsers.get(index).userEmail
                    + "\nage : " + User.localUsers.get(index).age
                    + "\nwork title : " + User.localUsers.get(index).WorkTitle
            );

            alert.setTitle(null);
            alert.show();

            updateUserListView();

            errorDashboardLabel.setText(" ");


            return;
        }

        errorDashboardLabel.setText("You can NOT update user ID !");

    }

    private void updateUserListView() {
        usersListView.getItems().clear();
        addListItem();
    }

    public void setAddButton(ActionEvent event) {

        if (userID.getText().isBlank() == true || userEmail.getText().isBlank() == true || username.getText().isBlank() == true || userPassword.getText().isBlank() == true || userAge.getText().isBlank() == true || userWorkTitle.getText().isBlank() == true) {
            errorDashboardLabel.setText("Please fill all the information !");
            return;
        }
        if(checkFormValidation()==false){
            return;
        }

        for (int i = 0; i < User.localUsers.size(); i++) {
            if (userID.getText().equals(User.localUsers.get(i).userID)) {
                errorDashboardLabel.setText("User ID already exist !");
                return;
            }
        }

        errorDashboardLabel.setText("");

        String id = userID.getText();
        String email = userEmail.getText();
        String name = username.getText();
        String password = userPassword.getText();
        String age = userAge.getText();
        String workTitle = userWorkTitle.getText();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(name + " Added Successfully ");
        alert.setContentText(" the user new information is : \n ID : " + id
                + "\nname : " + name
                + "\nemail : " + email
                + "\nage : " + age
                + "\nwork title : " + workTitle
        );
        alert.setTitle(null);
        alert.show();

        User newUser = new User(id, email, name, password, age, workTitle);
        User.localUsers.add(newUser);
        usersListView.getItems().add(name);
        System.out.println("added");

    }

    public boolean checkFormValidation(){
        boolean Validation = true;
        if (!(userID.getText().length() == 3)  ){
            Validation = false;
            errorDashboardLabel.setText("The ID must be three digit !");
            return Validation;
        }

        if (!userEmail.getText().contains("@qsystem.com")){
            Validation = false;
            errorDashboardLabel.setText("The email must contain @qsystem.com !");
            return Validation;
        }


        try {
            int UserValidAge  = Integer.parseInt(userAge.getText());
            System.out.println(UserValidAge);

            if ( UserValidAge > 60 || UserValidAge < 18){
                Validation = false;
                errorDashboardLabel.setText("The age must be between 18 ~ 60 !");
                return Validation;
            }
        }catch (Exception e){
            errorDashboardLabel.setText("The age must be digits !");
            return false;
        }

        return Validation;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListItem();

    }

    private void addListItem() {
        for (int i = 0; i < User.localUsers.size(); i++) {
            usersListView.getItems().add(User.localUsers.get(i).name);

        }
        usersListView.getSelectionModel().selectedItemProperty().addListener(event -> {
            index = usersListView.getSelectionModel().getSelectedIndex();
            IDLabel.setText(User.localUsers.get(index).userID);
            NameLabel.setText(User.localUsers.get(index).name);
            EmailLabel.setText(User.localUsers.get(index).userEmail);
            AgeLabel.setText(User.localUsers.get(index).age);
            PasswordLabel.setText(User.localUsers.get(index).password);
            workTitleLabel.setText(User.localUsers.get(index).WorkTitle);
            errorDashboardLabel.setText("");


        });
    }
}
