package View;

import Model.HotelData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingsView {
    private HotelData hotelData;

    public SettingsView(HotelData hotelData) {
        this.hotelData = hotelData;
    }

    public void show() {
        Stage stage = new Stage();
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: " + hotelData.getLightColor() + ";");
        layout.setAlignment(Pos.CENTER);

        Label title = new Label("Change Password");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane form = new GridPane();
        form.setVgap(15);
        form.setHgap(10);
        form.setAlignment(Pos.CENTER);

        // حقول إدخال المعلومات
        TextField currentUsernameField = new TextField();
        currentUsernameField.setPromptText("Current Username");

        PasswordField currentPasswordField = new PasswordField();
        currentPasswordField.setPromptText("Current Password");

        PasswordField newPasswordField = new PasswordField();
        newPasswordField.setPromptText("New Password");

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm New Password");

        // إضافة الحقول إلى النموذج
        form.add(new Label("Current Username:"), 0, 0);
        form.add(currentUsernameField, 1, 0);

        form.add(new Label("Current Password:"), 0, 1);
        form.add(currentPasswordField, 1, 1);

        form.add(new Label("New Password:"), 0, 2);
        form.add(newPasswordField, 1, 2);

        form.add(new Label("Confirm New Password:"), 0, 3);
        form.add(confirmPasswordField, 1, 3);

        // زر تأكيد التغيير
        Button saveBtn = new Button("Change Password");
        saveBtn.setStyle("-fx-background-color: " + hotelData.getSecondaryColor() + "; -fx-text-fill: white; -fx-padding: 10px 20px;");
        saveBtn.setOnAction(e -> {
            if (validateAndChangePassword(currentUsernameField, currentPasswordField,
                    newPasswordField, confirmPasswordField)) {
                // إظهار رسالة نجاح
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Password changed successfully!");
                successAlert.showAndWait();

                stage.close();
            }
        });

        // زر العودة
        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: " + hotelData.getPrimaryColor() + "; -fx-text-fill: white; -fx-padding: 10px 20px;");
        backBtn.setOnAction(e -> stage.close());

        layout.getChildren().addAll(title, form, saveBtn, backBtn);
        Scene scene = new Scene(layout, 400, 350);
        stage.setScene(scene);
        stage.setTitle("Settings - Change Password");
        stage.show();
    }

    private boolean validateAndChangePassword(TextField currentUsername, PasswordField currentPassword,
                                              PasswordField newPassword, PasswordField confirmPassword) {
        // التحقق من أن جميع الحقول مملوءة
        if (currentUsername.getText().trim().isEmpty() ||
                currentPassword.getText().isEmpty() ||
                newPassword.getText().isEmpty() ||
                confirmPassword.getText().isEmpty()) {
            showError("Please fill all fields");
            return false;
        }

        // التحقق من صحة المعلومات الحالية
        if (!hotelData.validateLogin(currentUsername.getText().trim(), currentPassword.getText())) {
            showError("Current username or password is incorrect");
            return false;
        }

        // التحقق من أن كلمة السر الجديدة تطابق التأكيد
        if (!newPassword.getText().equals(confirmPassword.getText())) {
            showError("New password and confirmation do not match");
            return false;
        }

        // التحقق من أن كلمة السر الجديدة ليست فارغة أو قصيرة جداً
        if (newPassword.getText().length() < 4) {
            showError("New password must be at least 4 characters long");
            return false;
        }

        // التحقق من أن كلمة السر الجديدة مختلفة عن القديمة
        if (newPassword.getText().equals(currentPassword.getText())) {
            showError("New password must be different from current password");
            return false;
        }

        System.out.println("Changing password from: " + hotelData.getCurrentPassword() + " to: " + newPassword.getText());
        hotelData.changePassword(currentUsername.getText().trim(), newPassword.getText());
        System.out.println("Password changed successfully! New password: " + hotelData.getCurrentPassword());

        return true;
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}