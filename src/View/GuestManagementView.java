package View;

import Model.Guest;
import Model.HotelData;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GuestManagementView {
    private HotelData hotelData;

    public GuestManagementView(HotelData hotelData) {
        this.hotelData = hotelData;
    }

    public void show() {
        Stage stage = new Stage();
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: " + hotelData.getLightColor() + ";");

        Label title = new Label("Guest Management");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: " + hotelData.getPrimaryColor() + ";");

        // زر إضافة ضيف جديد
        Button addGuestBtn = new Button("Add New Guest");
        addGuestBtn.setStyle("-fx-background-color: " + hotelData.getSecondaryColor() + "; -fx-text-fill: white;");
        addGuestBtn.setOnAction(e -> new CheckInWindow(hotelData).show());

        // جدول الضيوف
        TableView<Guest> table = new TableView<>();
        table.setItems(FXCollections.observableArrayList(hotelData.getGuests()));
        table.setStyle("-fx-background-color: white;");

        // أعمدة الجدول
        TableColumn<Guest, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Guest, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Guest, String> roomCol = new TableColumn<>("Room");
        roomCol.setCellValueFactory(new PropertyValueFactory<>("room"));

        TableColumn<Guest, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        table.getColumns().addAll(nameCol, idCol, roomCol, statusCol);

        // زر العودة
        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: " + hotelData.getPrimaryColor() + "; -fx-text-fill: white;");
        backBtn.setOnAction(e -> stage.close());

        HBox buttonsBox = new HBox(10, addGuestBtn, backBtn);
        layout.getChildren().addAll(title, buttonsBox, table);

        Scene scene = new Scene(layout, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Guest Management");
        stage.show();
    }
}
