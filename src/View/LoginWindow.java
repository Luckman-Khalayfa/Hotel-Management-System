package View;

import Model.HotelData;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginWindow {

    public void show(Stage primaryStage, HotelData hotelData) {
        Stage loginStage = new Stage();
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #f5f5f5;");

        Label title = new Label("Hotel Management Login");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginBtn = new Button("Login");
        loginBtn.setOnAction(e -> {
            String enteredUsername = usernameField.getText();
            String enteredPassword = passwordField.getText();

            // استخدام البيانات المحفوظة في الملف بدلاً من القيم الثابتة
            if (hotelData.validateLogin(enteredUsername, enteredPassword)) {
                loginStage.close();
                // افتح الواجهة الرئيسية
                MainView mainView = new MainView(hotelData);
                mainView.show(primaryStage);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText(null);
                alert.setContentText("Invalid username or password");
                alert.showAndWait();
            }
        });

        layout.getChildren().addAll(title, usernameField, passwordField, loginBtn);
        Scene scene = new Scene(layout, 300, 200);
        loginStage.setScene(scene);
        loginStage.setTitle("Login");
        loginStage.show();
    }
}