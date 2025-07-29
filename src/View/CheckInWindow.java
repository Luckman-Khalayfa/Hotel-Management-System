package View;

import Model.Guest;
import Model.HotelData;
import Model.Room;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CheckInWindow {
    private HotelData hotelData;

    public CheckInWindow(HotelData hotelData) {
        this.hotelData = hotelData;
    }

    public void show() {
        Stage stage = new Stage();
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: " + hotelData.getLightColor() + ";");
        layout.setAlignment(Pos.CENTER);

        Label title = new Label("Guest Check-In");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        GridPane form = new GridPane();
        form.setVgap(10);
        form.setHgap(10);
        form.setAlignment(Pos.CENTER);

        // حقول الإدخال
        TextField nameField = new TextField();
        nameField.setPromptText("Full Name");

        TextField idField = new TextField();
        idField.setPromptText("ID Number");

        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number");

        TextField emailField = new TextField();
        emailField.setPromptText("Email Address");

        // قائمة الغرف المتاحة
        ComboBox<Room> roomBox = new ComboBox<>();
        roomBox.getItems().addAll(hotelData.getAvailableRooms());
        roomBox.setPromptText("Select Room");

        // إضافة الحقول إلى النموذج
        form.add(new Label("Name:"), 0, 0);
        form.add(nameField, 1, 0);
        form.add(new Label("ID:"), 0, 1);
        form.add(idField, 1, 1);
        form.add(new Label("Phone:"), 0, 2);
        form.add(phoneField, 1, 2);
        form.add(new Label("Email:"), 0, 3);
        form.add(emailField, 1, 3);
        form.add(new Label("Room:"), 0, 4);
        form.add(roomBox, 1, 4);

        // زر الإرسال
        Button submitBtn = new Button("Complete Check-In");
        submitBtn.setStyle("-fx-background-color: " + hotelData.getSecondaryColor() + "; -fx-text-fill: white;");
        submitBtn.setOnAction(e -> {
            if (validateForm(nameField, idField,phoneField,emailField ,roomBox)) {
                Room selectedRoom = roomBox.getValue();
                Guest guest = new Guest(
                        nameField.getText(),
                        idField.getText(),
                        phoneField.getText(),
                        emailField.getText(),
                        selectedRoom.getFloor(),
                        selectedRoom.getNumber(),
                        "Checked In"
                );
                hotelData.checkInGuest(guest, selectedRoom.getNumber());
                stage.close();
            }
        });

        // زر العودة
        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: " + hotelData.getPrimaryColor() + "; -fx-text-fill: white;");
        backBtn.setOnAction(e -> stage.close());

        layout.getChildren().addAll(title, form, submitBtn, backBtn);
        Scene scene = new Scene(layout, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Guest Check-In");
        stage.show();
    }

    private boolean validateForm(TextField name, TextField id, TextField phone, TextField email, ComboBox<Room> room) {
        // التحقق من الحقول الفارغة
        if (name.getText().isEmpty() || id.getText().isEmpty() ||
                phone.getText().isEmpty() || email.getText().isEmpty() || room.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all required fields");
            alert.showAndWait();
            return false;
        }

        // التحقق من رقم الهوية (9 أرقام فقط)
        String idText = id.getText().trim();
        if (!idText.matches("\\d{9}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText(null);
            alert.setContentText("ID must be exactly 9 digits");
            alert.showAndWait();
            return false;
        }

        // التحقق من رقم الهاتف (10 أرقام فقط)
        String phoneText = phone.getText().trim();
        if (!phoneText.matches("\\d{10}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText(null);
            alert.setContentText("Phone number must be exactly 10 digits");
            alert.showAndWait();
            return false;
        }

        // التحقق من الإيميل (يجب أن ينتهي بـ @gmail.com)
        String emailText = email.getText().trim().toLowerCase();
        if (!emailText.endsWith("@gmail.com") || emailText.length() <= 10) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText(null);
            alert.setContentText("Email must end with @gmail.com");
            alert.showAndWait();
            return false;
        }

        return true;
    }
}
