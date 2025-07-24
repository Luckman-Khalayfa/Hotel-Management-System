package View;

import Model.HotelData;
import Model.Room;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CheckOutWindow {
    private HotelData hotelData;

    public CheckOutWindow(HotelData hotelData) {
        this.hotelData = hotelData;
    }

    public void show() {
        Stage stage = new Stage();
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: " + hotelData.getLightColor() + ";");
        layout.setAlignment(Pos.CENTER);

        Label title = new Label("Guest Check-Out");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // قائمة الغرف المشغولة
        ComboBox<Room> roomBox = new ComboBox<>();
        roomBox.getItems().addAll(hotelData.getOccupiedRooms());
        roomBox.setPromptText("Select Room");

        // زر تأكيد المغادرة
        Button submitBtn = new Button("Complete Check-Out");
        submitBtn.setStyle("-fx-background-color: " + hotelData.getAccentColor() + "; -fx-text-fill: white;");
        submitBtn.setOnAction(e -> {
            Room selectedRoom = roomBox.getValue();
            if (selectedRoom != null) {
                hotelData.checkOutGuest(selectedRoom.getNumber());
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please select a room");
                alert.showAndWait();
            }
        });

        // زر العودة
        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: " + hotelData.getPrimaryColor() + "; -fx-text-fill: white;");
        backBtn.setOnAction(e -> stage.close());

        layout.getChildren().addAll(title, roomBox, submitBtn, backBtn);
        Scene scene = new Scene(layout, 300, 250);
        stage.setScene(scene);
        stage.setTitle("Guest Check-Out");
        stage.show();
    }
}
