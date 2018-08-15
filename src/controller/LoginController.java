package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.DBConnect;
import service.DialogWindow;
import service.LoginCounter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LoginController {

    public static int id_user;

    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_lastname;
    @FXML
    private TextField tf_login;
    @FXML
    private TextField tf_password;
    @FXML
    private PasswordField pf_password;
    @FXML
    private CheckBox cb_approve;
    @FXML
    private Button btn_register;
    @FXML
    private TextField tf_login_name;
    @FXML
    private TextField tf_login_password;
    @FXML
    private Button btn_login;
    @FXML
    void approveAction(MouseEvent event) {
        if (cb_approve.isSelected()) {
            btn_register.setDisable(false);
        } else {
            btn_register.setDisable(true);
        }
    }

    private  String password;
    DBConnect db;
    PreparedStatement ps;


    @FXML
    void loginAction(MouseEvent event) throws SQLException, IOException {
        DBConnect db = new DBConnect();
        Connection conn = db.getConn();
        //dostęp do aktualnego okna
        Stage currentStage = (Stage) btn_login.getScene().getWindow();
        password = tf_login_password.getText();
        password = pf_password.getText();

        //przygotowanie zapytania do logowania
        PreparedStatement ps = conn.prepareStatement("SELECT id_uzytkownicy FROM uzytkownicy WHERE login = ? AND haslo = ?");
        ps.setString(1, tf_login_name.getText());
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            id_user = rs.getInt("id_uzytkownicy");
            final boolean b = LoginCounter.counter == 3;
            Parent root = FXMLLoader.load(getClass().getResource("/view/userView.fxml"));
            Scene scene = new Scene(root);
            Stage userStage = new Stage();
            userStage.setScene(scene);
            userStage.setTitle("Panel użytkownika");
            userStage.show();
            currentStage.close();
        }else{
            LoginCounter.countAction();
            DialogWindow dw = new DialogWindow(Alert.AlertType.WARNING,
                        "Błąd!",
                        "Błąd logowania",
                        "Podałeś błądny login lub hasło");
            tf_login_password.clear();
            pf_password.clear();
            }
        }

    @FXML
    void registerAction(MouseEvent event) throws SQLException {
        if (tf_name.getText().equals("") ||
                tf_lastname.getText().equals("") ||
                tf_login.getText().equals("") ||
                tf_password.getText().equals("")) {
            DialogWindow dw = new DialogWindow(Alert.AlertType.ERROR,
                    "Błąd",
                    "Błądne dane",
                    "Nie wszystkie pola zostały uzupełnione!");
        } else {
            Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDialog.setTitle("Potwierdzenie założenia konta");
            confirmDialog.setHeaderText("Czy na pewno chcesz założyć konto?");
            confirmDialog.setContentText("Jeśli chcesz założyć konto wybierz OK, jeśli nie anuluj!");

            Optional<ButtonType> decision = confirmDialog.showAndWait();
            if (decision.get() == ButtonType.OK) {

                db = new DBConnect();
                Connection conn = db.getConn();
                ps = conn.prepareStatement("INSERT INTO uzytkownicy (imie, nazwisko, login, haslo) " +
                        "VALUES (?, ?, ?, ?)");
                ps.setString(1, tf_name.getText());
                ps.setString(2, tf_lastname.getText());
                ps.setString(3, tf_login.getText());
                ps.setString(4, tf_password.getText());
                ps.executeUpdate();
                System.out.println("dodało");
                tf_name.clear();
                tf_lastname.clear();
                tf_login.clear();
                tf_password.clear();
                cb_approve.setSelected(false);
                btn_register.setDisable(true);
            }else {
                tf_name.clear();
                tf_lastname.clear();
                tf_login.clear();
                tf_password.clear();
                cb_approve.setSelected(false);
                btn_register.setDisable(true);
            }
        }
    }
}
