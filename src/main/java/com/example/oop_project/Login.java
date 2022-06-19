package com.example.oop_project;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Login extends Application {
    @Override
    public void start(Stage revStage) {
        Text name_lbl = new Text("User Name");
        Text pass_lbl = new Text("Password");
        Text res_lbl = new Text("");

        TextField uname_tf = new TextField();
        PasswordField pass = new PasswordField();

        Button login_btn = new Button("Login");

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(800, 400);
        gridPane.setVgap(20);
        gridPane.setHgap(40);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(name_lbl,1,1);
        gridPane.add(pass_lbl,1,2);
        gridPane.add(login_btn,1,3);
        gridPane.add(uname_tf, 2,1);
        gridPane.add(pass,2,2);
        gridPane.add(res_lbl,2,3);

        revStage.setTitle("Crostage");

        Scene myScene = new Scene(gridPane);

        revStage.setScene(myScene);
        revStage.show();

        login_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String username = uname_tf.getText();
                String password = pass.getText();

                try
                {
                    //Step One - Register the driver
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    //Step Two - Creating the connection
                    Connection con = DriverManager.getConnection("jdbc:mysql://crostage.co.ke:3306/crostage_db","crostage_admin","D@v1dK1ng");

                    System.out.println("Connected");


                    Statement stm = con.createStatement();

                    String sql = "SELECT password FROM users WHERE username = '"+username+"'";

//                    stm.executeUpdate("SELECT password FROM users WHERE username = '"+username+"'");

                    //Step five - Closing the connection
                    con.close();

                }
                catch(Exception ee){
                    System.out.println(ee);
                    System.out.println("Connection error");
                }
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}

class SecondGui extends Application {

    static Stage secondGuiStage = new Stage();

    @Override
    public void start(Stage secondStage) {
        Text greeting_lbl=new Text("Hello, I am the Second GUI");
        Button close_btn = new Button("Close");


        close_btn.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Stage stage = (Stage) close_btn.getScene().getWindow();
                stage.close();
            }
        }));


        GridPane secondGridPane = new GridPane();
        secondGridPane.setMinSize(400,200);
        secondGridPane.setVgap(10);
        secondGridPane.setHgap(20);
        secondGridPane.setAlignment(Pos.CENTER);

        secondGridPane.add(greeting_lbl, 1, 1);
        secondGridPane.add(close_btn, 1, 2);
        secondStage.setTitle("Second GUI");

        Scene secondScene = new Scene (secondGridPane);
        secondStage.setScene(secondScene);
        secondStage.show();
    }


}

