package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.net.URL;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Label errorLoginLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    boolean correctUsername =false;
    boolean correctPassword =false;


    public void setLoginButton(ActionEvent event){
        if( usernameField.getText().isBlank() == false && passwordField.getText().isBlank() == false){
            ValidateLogin();

        }else {
            errorLoginLabel.setText("Please fill all the information !");

        }

    }

    private void ValidateLogin() {
        int index = -1 ;
         correctUsername =false;
         correctPassword =false;
        for (int i =0 ; i < User.localUsers.size(); i++){

            if (User.localUsers.get(i).userEmail.equals(usernameField.getText()) ){
                correctUsername = true;
                System.out.println(User.localUsers.get(i).userEmail);
                System.out.println(usernameField.getText());

            }
            if (User.localUsers.get(i).password.equals(passwordField.getText())){
                System.out.println(User.localUsers.get(i).password);
                System.out.println(passwordField.getText());
                correctPassword = true;
                index = i;

            }
        }

        if (correctUsername == true && correctPassword ==true){

            passwordField.setText("");
            usernameField.setText("");

            if (User.localUsers.get(index).userID.equals("000") ){

                openAdminDashboard();
                errorLoginLabel.setText("");

            }else if( ! User.localUsers.get(index).userID.equals("000")) {
                openUserDashboard();
                errorLoginLabel.setText("");

            }


        }else if(correctUsername == false && correctPassword ==false){
            errorLoginLabel.setText("Invalid login information !");

        }

    }

    private void openUserDashboard() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("userDashboard.fxml"));
            Stage adminStage = new Stage();
            adminStage.initStyle(StageStyle.UNDECORATED);
            adminStage.setScene(new Scene(root, 1000, 700));
            adminStage.show();

        }catch (Exception e){
            System.out.println(e.fillInStackTrace());

        }
    }

    private void openAdminDashboard() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            Stage adminStage = new Stage();
            adminStage.initStyle(StageStyle.UNDECORATED);
            adminStage.setScene(new Scene(root, 1000, 700));
            adminStage.show();

        }catch (Exception e){

        }
    }

    public void setCancelButton(ActionEvent event){
        Stage stage =(Stage) cancelButton.getScene().getWindow();
        stage.close();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
