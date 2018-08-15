package controller;

import javafx.beans.binding.StringExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.ArchiveTrip;
import model.Badges;
import service.DBConnect;
import service.DialogWindow;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {

    DBConnect db;
    PreparedStatement ps;
    ObservableList<String> chain_names = FXCollections.observableArrayList();
    ObservableList<String> range_names = FXCollections.observableArrayList();
    ObservableList<String> peak1_names = FXCollections.observableArrayList();
    ObservableList<String> peak2_names = FXCollections.observableArrayList();
    ObservableList<String> peak3_names = FXCollections.observableArrayList();
    ObservableList<String> peak4_names = FXCollections.observableArrayList();
    ObservableList<String> change_range_names = FXCollections.observableArrayList();
    ObservableList<String> change_peak1_names = FXCollections.observableArrayList();
    ObservableList<String> change_peak2_names = FXCollections.observableArrayList();
    ObservableList<String> change_peak3_names = FXCollections.observableArrayList();
    ObservableList<String> change_peak4_names = FXCollections.observableArrayList();

    @FXML
    private DatePicker dp_trip_date;
    @FXML
    private ComboBox<String> cb_chain;
    @FXML
    private ComboBox<String> cb_range;
    @FXML
    private ComboBox<String> cb_peak_1;
    @FXML
    private ComboBox<String> cb_peak_2;
    @FXML
    private ComboBox<String> cb_peak_3;
    @FXML
    private ComboBox<String> cb_peak_4;
    @FXML
    private ComboBox<String> cb_change_chain;
    @FXML
    private ComboBox<String> cb_change_range;
    @FXML
    private ComboBox<String> cb_change_peak_1;
    @FXML
    private ComboBox<String> cb_change_peak_2;
    @FXML
    private ComboBox<String> cb_change_peak_3;
    @FXML
    private ComboBox<String> cb_change_peak_4;
    @FXML
    private Button btn_clear;
    @FXML
    private Button btn_save;

    @FXML
    private MenuItem m_logout;
    @FXML
    private MenuItem m_info;
    @FXML
    private MenuItem m_author;

    ObservableList<ArchiveTrip> archiveList = FXCollections.observableArrayList();
    @FXML
    private TableView<ArchiveTrip> tbl_archive;
    @FXML
    private TableColumn<ArchiveTrip, String> col_archive_date;
    @FXML
    private TableColumn<ArchiveTrip, String> col_archive_range;
    @FXML
    private TableColumn<ArchiveTrip, String> col_archive_peak;
    @FXML
    private TableColumn<ArchiveTrip, String> col_archive_height;

    ObservableList<Badges> badgeList = FXCollections.observableArrayList();
    @FXML
    private TableView<Badges> tbl_badge;
    @FXML
    private TableColumn<Badges, String> col_badge_range;
    @FXML
    private TableColumn<Badges, String> col_badge_peak;
    @FXML
    private TableColumn<Badges, String> col_badge_degrees;

    @FXML
    private Button btn_delete;

    private StringExpression tf_login;

    @FXML
    void deleteAction(MouseEvent event) throws SQLException {
        if (tbl_archive.getSelectionModel().getSelectedItem().getData_wycieczki() == null) {
            DialogWindow dw = new DialogWindow(Alert.AlertType.ERROR,
                    "Błąd!",
                    "Brak wyboru jakiejkolwiek warości z tabeli",
                    "Musisz zaznaczyć, którą wycieczkę chcesz usunąć");
        } else {
            db = new DBConnect();
            Connection conn = db.getConn();
            ps = conn.prepareStatement("DELETE FROM osiagniecia WHERE data_wycieczki = ?");
            ps.setString(1, tbl_archive.getSelectionModel().getSelectedItem().getData_wycieczki());
            ps.executeUpdate();
            globalArchiveSelect();
        }
    }

    @FXML
    void chooseDateAction(MouseEvent event) {
        dp_trip_date.isShowWeekNumbers();
        cb_chain.setDisable(false);
    }
    @FXML
    void chooseChainAction(MouseEvent event) {
        cb_chain.isCacheShape();
        cb_range.setDisable(false);
    }
    @FXML
    void chooseRangeAction(MouseEvent event) throws SQLException {
        cb_range.isCacheShape();
        cb_peak_1.setDisable(false);
        cb_range.setItems(null);
        range_names.clear();
        DBConnect db = new DBConnect();
        Connection conn = db.getConn();
        ps = conn.prepareStatement("SELECT nazwa_pasma FROM pasma_gorskie WHERE lancuchy_gorskie_id_lancuchy_gorskie = (SELECT id_lancuchy_gorskie FROM lancuchy_gorskie WHERE nazwa = ?)");
        ps.setString(1, cb_chain.getValue());
        ResultSet result_range = ps.executeQuery();
        while (result_range.next()) {
            range_names.add(result_range.getString("nazwa_pasma"));
        }
        cb_range.setItems(range_names);
    }

    @FXML
    void choosePeak1Action(MouseEvent event) throws SQLException {
        cb_peak_1.isCacheShape();
        cb_peak_2.setDisable(false);
        cb_change_chain.setDisable(false);
        cb_peak_1.setItems(null);
        peak1_names.clear();
        DBConnect db = new DBConnect();
        Connection conn = db.getConn();
        ps = conn.prepareStatement("SELECT nazwa_szczytu FROM pasma_szczyty WHERE pasma_gorskie_id_pasma_gorskie = (SELECT id_pasma_gorskie FROM pasma_gorskie WHERE nazwa_pasma = ?)");
        ps.setString(1, cb_range.getValue());
        ResultSet result_peak = ps.executeQuery();
        while (result_peak.next()) {
            peak1_names.add(result_peak.getString("nazwa_szczytu"));
        }
        cb_peak_1.setItems(peak1_names);
    }
    @FXML
    void choosePeak2Action(MouseEvent event) throws SQLException {
        cb_peak_2.isCacheShape();
        cb_peak_3.setVisible(true);
        cb_peak_2.setItems(null);
        peak2_names.clear();
        DBConnect db = new DBConnect();
        Connection conn = db.getConn();
        ps = conn.prepareStatement("SELECT nazwa_szczytu FROM pasma_szczyty WHERE pasma_gorskie_id_pasma_gorskie = (SELECT id_pasma_gorskie FROM pasma_gorskie WHERE nazwa_pasma = ?)");
        ps.setString(1, cb_range.getValue());
        ResultSet result_peak = ps.executeQuery();
        while (result_peak.next()) {
            peak2_names.add(result_peak.getString("nazwa_szczytu"));
        }
        cb_peak_2.setItems(peak2_names);
        peak2_names.remove(cb_peak_1.getValue());

    }
    @FXML
    void choosePeak3Action(MouseEvent event) throws SQLException {
        cb_peak_3.isCacheShape();
        cb_peak_4.setVisible(true);
        cb_peak_3.setItems(null);
        peak3_names.clear();
        DBConnect db = new DBConnect();
        Connection conn = db.getConn();
        ps = conn.prepareStatement("SELECT nazwa_szczytu FROM pasma_szczyty WHERE pasma_gorskie_id_pasma_gorskie = (SELECT id_pasma_gorskie FROM pasma_gorskie WHERE nazwa_pasma = ?)");
        ps.setString(1, cb_range.getValue());
        ResultSet result_peak = ps.executeQuery();
        while (result_peak.next()) {
            peak3_names.add(result_peak.getString("nazwa_szczytu"));
        }
        cb_peak_3.setItems(peak3_names);
        peak3_names.remove(cb_peak_1.getValue());
        peak3_names.remove(cb_peak_2.getValue());
    }
    @FXML
    void choosePeak4Action(MouseEvent event) throws SQLException {
        cb_peak_4.isCacheShape();
        cb_peak_4.setItems(null);
        peak4_names.clear();
        DBConnect db = new DBConnect();
        Connection conn = db.getConn();
        ps = conn.prepareStatement("SELECT nazwa_szczytu FROM pasma_szczyty WHERE pasma_gorskie_id_pasma_gorskie = (SELECT id_pasma_gorskie FROM pasma_gorskie WHERE nazwa_pasma = ?)");
        ps.setString(1, cb_range.getValue());
        ResultSet result_peak = ps.executeQuery();
        while (result_peak.next()) {
            peak4_names.add(result_peak.getString("nazwa_szczytu"));
        }
        cb_peak_4.setItems(peak4_names);
        peak4_names.remove(cb_peak_1.getValue());
        peak4_names.remove(cb_peak_2.getValue());
        peak4_names.remove(cb_peak_3.getValue());
    }
    @FXML
    void changeChainAction(MouseEvent event) {
        cb_change_chain.isCacheShape();
        cb_change_range.setDisable(false);
    }
    @FXML
    void changeRangeAction(MouseEvent event) throws SQLException {
        cb_change_range.isCacheShape();
        cb_change_peak_1.setDisable(false);
        cb_change_range.setItems(null);
        change_range_names.clear();
        DBConnect db = new DBConnect();
        Connection conn = db.getConn();
        ps = conn.prepareStatement("SELECT nazwa_pasma FROM pasma_gorskie WHERE lancuchy_gorskie_id_lancuchy_gorskie = (SELECT id_lancuchy_gorskie FROM lancuchy_gorskie WHERE nazwa = ?)");
        ps.setString(1, cb_change_chain.getValue());
        ResultSet result_range = ps.executeQuery();
        while (result_range.next()) {
            change_range_names.add(result_range.getString("nazwa_pasma"));
        }
        cb_change_range.setItems(change_range_names);
        }
    @FXML
    void changePeak1Action(MouseEvent event) throws SQLException {
        cb_change_peak_1.isCacheShape();
        cb_change_peak_2.setDisable(false);
        cb_change_peak_1.setItems(null);
        change_peak1_names.clear();
        DBConnect db = new DBConnect();
        Connection conn = db.getConn();
        ps = conn.prepareStatement("SELECT nazwa_szczytu FROM pasma_szczyty WHERE pasma_gorskie_id_pasma_gorskie = (SELECT id_pasma_gorskie FROM pasma_gorskie WHERE nazwa_pasma = ?)");
        ps.setString(1, cb_change_range.getValue());
        ResultSet result_peak = ps.executeQuery();
        while (result_peak.next()) {
            change_peak1_names.add(result_peak.getString("nazwa_szczytu"));
        }
        cb_change_peak_1.setItems(change_peak1_names);
        change_peak1_names.remove(cb_peak_1.getValue());
        change_peak1_names.remove(cb_peak_2.getValue());
        change_peak1_names.remove(cb_peak_3.getValue());
        change_peak1_names.remove(cb_peak_4.getValue());
    }
    @FXML
    void changePeak2Action(MouseEvent event) throws SQLException {
        cb_change_peak_2.isCacheShape();
        cb_change_peak_3.setVisible(true);
        cb_change_peak_2.setItems(null);
        change_peak2_names.clear();
        DBConnect db = new DBConnect();
        Connection conn = db.getConn();
        ps = conn.prepareStatement("SELECT nazwa_szczytu FROM pasma_szczyty WHERE pasma_gorskie_id_pasma_gorskie = (SELECT id_pasma_gorskie FROM pasma_gorskie WHERE nazwa_pasma = ?)");
        ps.setString(1, cb_change_range.getValue());
        ResultSet result_peak = ps.executeQuery();
        while (result_peak.next()) {
            change_peak2_names.add(result_peak.getString("nazwa_szczytu"));
        }
        cb_change_peak_2.setItems(change_peak2_names);
        change_peak2_names.remove(cb_change_peak_1.getValue());
        change_peak2_names.remove(cb_peak_1.getValue());
        change_peak2_names.remove(cb_peak_2.getValue());
        change_peak2_names.remove(cb_peak_3.getValue());
        change_peak2_names.remove(cb_peak_4.getValue());
    }
    @FXML
    void changePeak3Action(MouseEvent event) throws SQLException {
        cb_change_peak_3.isCacheShape();
        cb_change_peak_4.setVisible(true);
        cb_change_peak_3.setItems(null);
        change_peak3_names.clear();
        DBConnect db = new DBConnect();
        Connection conn = db.getConn();
        ps = conn.prepareStatement("SELECT nazwa_szczytu FROM pasma_szczyty WHERE pasma_gorskie_id_pasma_gorskie = (SELECT id_pasma_gorskie FROM pasma_gorskie WHERE nazwa_pasma = ?)");
        ps.setString(1, cb_change_range.getValue());
        ResultSet result_peak = ps.executeQuery();
        while (result_peak.next()) {
            change_peak3_names.add(result_peak.getString("nazwa_szczytu"));
        }
        cb_change_peak_3.setItems(change_peak3_names);
        change_peak3_names.remove(cb_change_peak_1.getValue());
        change_peak3_names.remove(cb_change_peak_2.getValue());
        change_peak3_names.remove(cb_peak_1.getValue());
        change_peak3_names.remove(cb_peak_2.getValue());
        change_peak3_names.remove(cb_peak_3.getValue());
        change_peak3_names.remove(cb_peak_4.getValue());
    }
    @FXML
    void changePeak4Action(MouseEvent event) throws SQLException {
        cb_change_peak_4.isCacheShape();
        cb_change_peak_4.setItems(null);
        change_peak4_names.clear();
        DBConnect db = new DBConnect();
        Connection conn = db.getConn();
        ps = conn.prepareStatement("SELECT nazwa_szczytu FROM pasma_szczyty WHERE pasma_gorskie_id_pasma_gorskie = (SELECT id_pasma_gorskie FROM pasma_gorskie WHERE nazwa_pasma = ?)");
        ps.setString(1, cb_change_range.getValue());
        ResultSet result_peak = ps.executeQuery();
        while (result_peak.next()) {
            change_peak4_names.add(result_peak.getString("nazwa_szczytu"));
        }
        cb_change_peak_4.setItems(change_peak4_names);
        change_peak4_names.remove(cb_change_peak_1.getValue());
        change_peak4_names.remove(cb_change_peak_2.getValue());
        change_peak4_names.remove(cb_change_peak_3.getValue());
        change_peak4_names.remove(cb_peak_1.getValue());
        change_peak4_names.remove(cb_peak_2.getValue());
        change_peak4_names.remove(cb_peak_3.getValue());
        change_peak4_names.remove(cb_peak_4.getValue());
    }

    @FXML
    void clearAction(MouseEvent event) {
        dp_trip_date.getEditor().clear();
        cb_chain.getSelectionModel().clearSelection();
        cb_chain.setDisable(true);
        cb_range.getSelectionModel().clearSelection();
        cb_range.setDisable(true);
        cb_peak_1.getSelectionModel().clearSelection();
        cb_peak_1.setDisable(true);
        cb_peak_2.getSelectionModel().clearSelection();
        cb_peak_2.setDisable(true);
        cb_peak_3.getSelectionModel().clearSelection();
        cb_peak_3.setVisible(false);
        cb_peak_4.getSelectionModel().clearSelection();
        cb_peak_4.setVisible(false);
        cb_change_chain.getSelectionModel().clearSelection();
        cb_change_chain.setDisable(true);
        cb_change_range.getSelectionModel().clearSelection();
        cb_change_range.setDisable(true);
        cb_change_peak_1.getSelectionModel().clearSelection();
        cb_change_peak_1.setDisable(true);
        cb_change_peak_2.getSelectionModel().clearSelection();
        cb_change_peak_2.setDisable(true);
        cb_change_peak_3.getSelectionModel().clearSelection();
        cb_change_peak_3.setVisible(false);
        cb_change_peak_4.getSelectionModel().clearSelection();
        cb_change_peak_4.setVisible(false);
    }

    @FXML
    void saveAction(MouseEvent event) throws SQLException {
        if (cb_peak_1.getValue() == null) {
            DialogWindow dw = new DialogWindow(Alert.AlertType.ERROR,
                    "Błąd!",
                    "Brak wyboru jakiejkolwiek warości z listy 'Wybierz szczyt górski'",
                    "Musisz wybrać choć jeden szczyt, który zdobyłeć, aby móc zapisać wycieczkę");

        } else {
            db = new DBConnect();
            Connection conn = db.getConn();
            ps = conn.prepareStatement("INSERT INTO osiagniecia (data_wycieczki, uzytkownicy_id_uzytkownicy, pasma_szczyty_id_pasma_szczyty) " +
                    "values (?, ?, (SELECT id_pasma_szczyty FROM pasma_szczyty WHERE nazwa_szczytu = ?))");
            ps.setString(1, String.valueOf(dp_trip_date.getValue()));
            ps.setInt(2, LoginController.id_user);
            ps.setString(3, cb_peak_1.getValue());
            ps.executeUpdate();

            if (cb_peak_2.getValue() == null) {
            } else {
                conn = db.getConn();
                ps = conn.prepareStatement("INSERT INTO osiagniecia (data_wycieczki, uzytkownicy_id_uzytkownicy, pasma_szczyty_id_pasma_szczyty) " +
                        "values (?, ?, (SELECT id_pasma_szczyty FROM pasma_szczyty WHERE nazwa_szczytu = ?))");
                ps.setString(1, String.valueOf(dp_trip_date.getValue()));
                ps.setInt(2, LoginController.id_user);
                ps.setString(3, cb_peak_2.getValue());
                ps.executeUpdate();
            }
            if (cb_peak_3.getValue() == null) {
            } else {
                conn = db.getConn();
                ps = conn.prepareStatement("INSERT INTO osiagniecia (data_wycieczki, uzytkownicy_id_uzytkownicy, pasma_szczyty_id_pasma_szczyty) " +
                        "values (?, ?, (SELECT id_pasma_szczyty FROM pasma_szczyty WHERE nazwa_szczytu = ?))");
                ps.setString(1, String.valueOf(dp_trip_date.getValue()));
                ps.setInt(2, LoginController.id_user);
                ps.setString(3, cb_peak_3.getValue());
                ps.executeUpdate();
            }
            if (cb_peak_4.getValue() == null) {
            } else {
                conn = db.getConn();
                ps = conn.prepareStatement("INSERT INTO osiagniecia (data_wycieczki, uzytkownicy_id_uzytkownicy, pasma_szczyty_id_pasma_szczyty) " +
                        "values (?, ?, (SELECT id_pasma_szczyty FROM pasma_szczyty WHERE nazwa_szczytu = ?))");
                ps.setString(1, String.valueOf(dp_trip_date.getValue()));
                ps.setInt(2, LoginController.id_user);
                ps.setString(3, cb_peak_4.getValue());
                ps.executeUpdate();
            }
            if (cb_change_peak_1.getValue() == null) {
            } else {
                conn = db.getConn();
                ps = conn.prepareStatement("INSERT INTO osiagniecia (data_wycieczki, uzytkownicy_id_uzytkownicy, pasma_szczyty_id_pasma_szczyty) " +
                        "values (?, ?, (SELECT id_pasma_szczyty FROM pasma_szczyty WHERE nazwa_szczytu = ?))");
                ps.setString(1, String.valueOf(dp_trip_date.getValue()));
                ps.setInt(2, LoginController.id_user);
                ps.setString(3, cb_change_peak_1.getValue());
                ps.executeUpdate();
            }
            if (cb_change_peak_2.getValue() == null) {
            } else {
                conn = db.getConn();
                ps = conn.prepareStatement("INSERT INTO osiagniecia (data_wycieczki, uzytkownicy_id_uzytkownicy, pasma_szczyty_id_pasma_szczyty) " +
                        "values (?, ?, (SELECT id_pasma_szczyty FROM pasma_szczyty WHERE nazwa_szczytu = ?))");
                ps.setString(1, String.valueOf(dp_trip_date.getValue()));
                ps.setInt(2, LoginController.id_user);
                ps.setString(3, cb_change_peak_2.getValue());
                ps.executeUpdate();
            }
            if (cb_change_peak_3.getValue() == null) {
            } else {
                conn = db.getConn();
                ps = conn.prepareStatement("INSERT INTO osiagniecia (data_wycieczki, uzytkownicy_id_uzytkownicy, pasma_szczyty_id_pasma_szczyty) " +
                        "values (?, ?, (SELECT id_pasma_szczyty FROM pasma_szczyty WHERE nazwa_szczytu = ?))");
                ps.setString(1, String.valueOf(dp_trip_date.getValue()));
                ps.setInt(2, LoginController.id_user);
                ps.setString(3, cb_change_peak_3.getValue());
                ps.executeUpdate();
            }
            if (cb_change_peak_4.getValue() == null) {
            } else {
                conn = db.getConn();
                ps = conn.prepareStatement("INSERT INTO osiagniecia (data_wycieczki, uzytkownicy_id_uzytkownicy, pasma_szczyty_id_pasma_szczyty) " +
                        "values (?, ?, (SELECT id_pasma_szczyty FROM pasma_szczyty WHERE nazwa_szczytu = ?))");
                ps.setString(1, String.valueOf(dp_trip_date.getValue()));
                ps.setInt(2, LoginController.id_user);
                ps.setString(3, cb_change_peak_4.getValue());
                ps.executeUpdate();
            }
            DialogWindow dw = new DialogWindow(Alert.AlertType.INFORMATION,
                    "Powiadomienie!",
                    "Zapisano pomyślnie",
                    "Twoja wycieczka została zapisana, możesz śledzić swoje osiągnięcia w zakładce 'Historia'");
        }
        dp_trip_date.getEditor().clear();
        cb_chain.getSelectionModel().clearSelection();
        cb_chain.setDisable(true);
        cb_range.getSelectionModel().clearSelection();
        cb_range.setDisable(true);
        cb_peak_1.getSelectionModel().clearSelection();
        cb_peak_1.setDisable(true);
        cb_peak_2.getSelectionModel().clearSelection();
        cb_peak_2.setDisable(true);
        cb_peak_3.getSelectionModel().clearSelection();
        cb_peak_3.setVisible(false);
        cb_peak_4.getSelectionModel().clearSelection();
        cb_peak_4.setVisible(false);
        cb_change_chain.getSelectionModel().clearSelection();
        cb_change_chain.setDisable(true);
        cb_change_range.getSelectionModel().clearSelection();
        cb_change_range.setDisable(true);
        cb_change_peak_1.getSelectionModel().clearSelection();
        cb_change_peak_1.setDisable(true);
        cb_change_peak_2.getSelectionModel().clearSelection();
        cb_change_peak_2.setDisable(true);
        cb_change_peak_3.getSelectionModel().clearSelection();
        cb_change_peak_3.setVisible(false);
        cb_change_peak_4.getSelectionModel().clearSelection();
        cb_change_peak_4.setVisible(false);
    }

    @FXML
    void menuAuthor(ActionEvent event) {
        DialogWindow dw = new DialogWindow(Alert.AlertType.INFORMATION,
                "O autorze",
                "Autor aplikacji",
                "Wolę górski wiatr od miastowych spalin, a wysiłek na szlaku od leżenia na plaży. " +
                        "Potrafię zachwycić się szumem potoku, zapachem lasu i potęgą gór. " +
                        "\nDo zobaczenia na szlaku!");
    }

    @FXML
    void menuInfo(ActionEvent event) {
        DialogWindow dw = new DialogWindow(Alert.AlertType.INFORMATION,
                "Instrukcja",
                "Instrukcja korzystania z aplikacji",
                "1. Wybierz datę wycieczki\n2. Wybierz łancuch górski, w który się udałeś\n3. Wybierz pasmo górskie, którym podróżowałeś" +
                        "\n4. Wybierz szczyty górskie, które zdobyłeś\n5. Na koniec zapisz wycieczkę\n6. W zakładce Historia znajdziesz Twoje " +
                        "wprowadzone wyprawy\n7. W zakładce Odznaki znajdziesz Twoje zdobyte odznaki");
    }

    @FXML
    void menuLogout(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) btn_save.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginView.fxml"));
        Scene scene = new Scene(root);
        Stage userStage = new Stage();
        userStage.setScene(scene);
        userStage.setTitle("Panel logowania");
        userStage.show();
        currentStage.close();
    }

    public void whereHaveYouBeen() throws SQLException {
        db = new DBConnect();
        Connection conn = db.getConn();
        //uzupełnienie combobox lancuchy
        ps = conn.prepareStatement("SELECT nazwa FROM lancuchy_gorskie");
        ResultSet result_chain = ps.executeQuery();
        while (result_chain.next()) {
            chain_names.add(result_chain.getString("nazwa"));
        }
        cb_chain.setItems(chain_names);
        cb_change_chain.setItems(chain_names);
    }

    private void globalArchiveSelect() throws SQLException {
        archiveList.clear();
        db = new DBConnect();
        Connection conn = db.getConn();
        ps = conn.prepareStatement("SELECT data_wycieczki, nazwa_pasma, nazwa_szczytu, wysokosc FROM pasma_szczyty "+
                "JOIN osiagniecia ON pasma_szczyty_id_pasma_szczyty = id_pasma_szczyty "+
                "JOIN pasma_gorskie ON pasma_gorskie_id_pasma_gorskie = id_pasma_gorskie "+
                "JOIN uzytkownicy on osiagniecia.uzytkownicy_id_uzytkownicy = uzytkownicy.id_uzytkownicy "+
                "WHERE uzytkownicy_id_uzytkownicy = ?");
        ps.setInt(1, LoginController.id_user);
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            ArchiveTrip archiveTrip = new ArchiveTrip(
                    result.getString("data_wycieczki"),
                    result.getString("nazwa_pasma"),
                    result.getString("nazwa_szczytu"),
                    result.getString("wysokosc"));
            archiveList.add(archiveTrip);
        }
        //wypełnienie zawartości TabeleView
        col_archive_date.setCellValueFactory(new PropertyValueFactory<ArchiveTrip, String>("data_wycieczki"));
        col_archive_range.setCellValueFactory(new PropertyValueFactory<ArchiveTrip, String>("nazwa_pasma"));
        col_archive_peak.setCellValueFactory(new PropertyValueFactory<ArchiveTrip, String>("nazwa_szczytu"));
        col_archive_height.setCellValueFactory(new PropertyValueFactory<ArchiveTrip, String>("wysokosc"));
        tbl_archive.setItems(archiveList);
    }

    private void globalBadgeSelect() throws SQLException {
//        badgeList.clear();
//        db = new DBConnect();
//        Connection conn = db.getConn();
//        ps = conn.prepareStatement("SELECT * FROM zdobyte_odznaki "+
//                "JOIN uzytkownicy on osiagniecia.uzytkownicy_id_uzytkownicy = uzytkownicy.id_uzytkownicy "+
//                "WHERE uzytkownicy_id_uzytkownicy = ?");
//
//        ps.setInt(1, LoginController.id_user);
//        ResultSet result = ps.executeQuery();
//        while (result.next()) {
//            Badges badges = new Badges(
//                    result.getString("nazwa_pasma"),
//                    result.getString("liczba_szczytow"),
//                    result.getString("stopnie_odznak"));
//            badgeList.add(badges);
//        }
//        //wypełnienie zawartości TabeleView
//        col_badge_range.setCellValueFactory(new PropertyValueFactory<Badges, String>("nazwa_pasma"));
//        col_badge_peak.setCellValueFactory(new PropertyValueFactory<Badges, String>("liczba_szczytow"));
//        col_badge_degrees.setCellValueFactory(new PropertyValueFactory<Badges, String>("stopnie_odznak"));
//        tbl_badge.setItems(badgeList);
    }

    public void initialize() throws SQLException {
        globalArchiveSelect();
        whereHaveYouBeen();
        globalBadgeSelect();
    }
}
