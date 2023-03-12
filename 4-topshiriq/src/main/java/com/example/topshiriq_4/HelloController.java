package com.example.topshiriq_4;


import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public TextField name;
    public TextField surname;
    public DatePicker date;
    public RadioButton male;
    public RadioButton female;
    public PasswordField password;
    public PasswordField checkpassword;
    public TextField email;
    public Button submit;
    public Label error;
    LocalDate now= LocalDate.now();
    RadioButton gender;
    String selectgender;
    public boolean countbelgi(String username) {
        if (username.lastIndexOf('@')==(-1)) {
            return false;
        }else{
            return true;
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void submit() throws IOException, ClassNotFoundException {
        ToggleGroup radioGroup = new ToggleGroup();
        male.setToggleGroup(radioGroup);
        female.setToggleGroup(radioGroup);
        gender = (RadioButton) radioGroup.getSelectedToggle();
        if(gender!=null){
            selectgender=gender.getText();
        }
        if (name.getText().equals("") || surname.getText().equals("")  || gender==null ||  date.getValue() == null || password.getText().equals("") || checkpassword.getText().equals("") || email.getText().equals("")) {
            error.setTextFill(Paint.valueOf("red"));
            error.setText("Ma'lumotlar to'liq to'ldirilmadi !!!");
        }
        else if(!date.getValue().isBefore(now)){
            error.setTextFill(Paint.valueOf("red"));
            error.setText("Date birth ma'lumotlari nato'g'ri !!!");
        } else if(!password.getText().equals(checkpassword.getText())) {
            error.setTextFill(Paint.valueOf("red"));
            error.setText("Passsword va Confirm password ma'lumotlari bir biriga mos emas !!!");
        }else if(!countbelgi(email.getText())){
            error.setTextFill(Paint.valueOf("red"));
            error.setText("Email ma'lumotlari nato'g'ri!!!");
        } else{
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/register", "root", "15042002");
                PreparedStatement pst = conn.prepareStatement("insert into user (name,surname,date,gender,password,email) values(?,?,?,?,?,?)");
                pst.setString(1, name.getText());
                pst.setString(2, surname.getText());
                pst.setDate(3, Date.valueOf(date.getValue()));
                pst.setString(4,selectgender);
                pst.setString(5,password.getText());
                pst.setString(6,email.getText());
                if (pst.executeUpdate() == 1) {
                    error.setTextFill(Paint.valueOf("green"));
                    error.setText("Ma'lumotlar qo'shildi !!!");
                }
                pst.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println();
            }
        }
    }
}

