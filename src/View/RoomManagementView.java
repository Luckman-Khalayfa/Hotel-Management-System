package View;

import Model.HotelData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RoomManagementView {
    private HotelData hotelData;

    public RoomManagementView(HotelData hotelData) {
        this.hotelData = hotelData;
    }

    public void show() {
        Stage stage = new Stage();
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: " + hotelData.getLightColor() + ";");
        layout.setAlignment(Pos.CENTER);

        Label title = new Label("Room Management");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: " + hotelData.getPrimaryColor() + ";");

        // إحصائيات الغرف
        int totalRooms = hotelData.getRooms().size();
        long occupiedRooms = hotelData.getOccupiedRooms().size();
        long availableRooms = totalRooms - occupiedRooms;

        Label totalLabel = new Label("Total Rooms: " + totalRooms);
        Label occupiedLabel = new Label("Occupied: " + occupiedRooms);
        Label availableLabel = new Label("Available: " + availableRooms);

        // أزرار الإجراءات
        Button checkInBtn = new Button("Check In");
        checkInBtn.setStyle("-fx-background-color: " + hotelData.getSecondaryColor() + "; -fx-text-fill: white;");
        checkInBtn.setOnAction(e -> new CheckInWindow(hotelData).show());

        Button checkOutBtn = new Button("Check Out");
        checkOutBtn.setStyle("-fx-background-color: " + hotelData.getAccentColor() + "; -fx-text-fill: white;");
        checkOutBtn.setOnAction(e -> new CheckOutWindow(hotelData).show());

        Button viewRoomsBtn = new Button("View All Rooms");
        viewRoomsBtn.setStyle("-fx-background-color: " + hotelData.getPrimaryColor() + "; -fx-text-fill: white;");
        viewRoomsBtn.setOnAction(e -> new AllRoomsView(hotelData).show());

        HBox buttonsBox = new HBox(10, checkInBtn, checkOutBtn, viewRoomsBtn);
        buttonsBox.setAlignment(Pos.CENTER);

        // زر العودة
        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: " + hotelData.getPrimaryColor() + "; -fx-text-fill: white;");
        backBtn.setOnAction(e -> stage.close());

        layout.getChildren().addAll(title, totalLabel, occupiedLabel, availableLabel, buttonsBox, backBtn);
        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Room Management");
        stage.show();
    }
}