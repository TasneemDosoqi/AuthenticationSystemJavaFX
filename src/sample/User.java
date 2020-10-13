package sample;

import java.util.ArrayList;
public class User {

    String userID;
    String userEmail;
    String name;
    String password;
    String age;
    String WorkTitle;
    public static ArrayList<User> localUsers = new ArrayList<>();

    public User(String userID, String userEmail, String name, String password, String age, String workTitle) {
        this.userID = userID;
        this.name = name;
        this.userEmail = userEmail;
        this.password = password;
        this.age = age;
        WorkTitle = workTitle;
    }

}
