package View;

import Model.HotelData;
import Model.Room;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AllRoomsView {
    private HotelData hotelData;

    public AllRoomsView(HotelData hotelData) {
        this.hotelData = hotelData;
    }

    public void show() {
        Stage stage = new Stage();
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: " + hotelData.getLightColor() + ";");

        Label title = new Label("All Rooms");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // فلتر الغرف
        ComboBox<String> filterCombo = new ComboBox<>();
        filterCombo.getItems().addAll("All Rooms", "Occupied Rooms", "Available Rooms");
        filterCombo.setValue("All Rooms");

        // جدول الغرف
        TableView<Room> table = new TableView<>();
        table.setItems(FXCollections.observableArrayList(hotelData.getRooms()));

        TableColumn<Room, Integer> numberCol = new TableColumn<>("Room Number");
        numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<Room, Integer> floorCol = new TableColumn<>("Floor");
        floorCol.setCellValueFactory(new PropertyValueFactory<>("floor"));

        TableColumn<Room, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Room, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Room, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(cellData -> {
            if (cellData.getValue().isOccupied()) {
                return new javafx.beans.property.SimpleStringProperty("Occupied");
            } else {
                return new javafx.beans.property.SimpleStringProperty("Available");
            }
        });

        table.getColumns().addAll(numberCol, floorCol, typeCol, priceCol, statusCol);

        // تغيير الفلتر
        filterCombo.setOnAction(e -> {
            String filter = filterCombo.getValue();
            if (filter.equals("All Rooms")) {
                table.setItems(FXCollections.observableArrayList(hotelData.getRooms()));
            } else if (filter.equals("Occupied Rooms")) {
                table.setItems(FXCollections.observableArrayList(hotelData.getOccupiedRooms()));
            } else if (filter.equals("Available Rooms")) {
                table.setItems(FXCollections.observableArrayList(hotelData.getAvailableRooms()));
            }
        });

        // زر العودة
        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: " + hotelData.getPrimaryColor() + "; -fx-text-fill: white;");
        backBtn.setOnAction(e -> stage.close());

        layout.getChildren().addAll(title, filterCombo, table, backBtn);
        Scene scene = new Scene(layout, 600, 400);
        stage.setScene(scene);
        stage.setTitle("All Rooms");
        stage.show();
    }
}
