package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.*;
import java.io.FileInputStream;
import java.io.InputStream;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        //primaryStage.setTitle("Q System");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
        usersList();


    }

    private void usersList() {
        User user1 = new User("111","user1@qsystem.com","Ahmed Osama","user1","25","Content creator");
        User user2 = new User("112","user2@qsystem.com","Sara Kalid","user2","27","Receptionist");
        User user3 = new User("113","user3@qsystem.com","Asma Hosam","user3","33","Account manager");
        User user4 = new User("114","user4@qsystem.com","Hind Sameh","user4","40","Project manager");
        User user5 = new User("115","user5@qsystem.com","Ali Majed","user5","26","developer");
        User admin = new User("000","admin@qsystem.com","Admin","admin","30","Admin");

        User.localUsers.add(user1);
        User.localUsers.add(user2);
        User.localUsers.add(user3);
        User.localUsers.add(user4);
        User.localUsers.add(user5);
        User.localUsers.add(admin);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
