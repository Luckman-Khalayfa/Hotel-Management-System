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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class MealServicesView {
    private HotelData hotelData;

    public MealServicesView(HotelData hotelData) {
        this.hotelData = hotelData;
    }

    public void show() {
        Stage stage = new Stage();
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(30));
        layout.setStyle("-fx-background-color: linear-gradient(to bottom, #f8f9fa, #e9ecef);");
        layout.setAlignment(Pos.CENTER);

        // العنوان الرئيسي
        Label title = new Label("🍽️ Daily Meal Services");
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; " +
                "-fx-text-fill: " + hotelData.getPrimaryColor() + "; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 2);");

        // التاريخ الحالي
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy", Locale.ENGLISH);
        String formattedDate = currentDate.format(formatter);

        Label dateLabel = new Label("📅 " + formattedDate);
        dateLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; " +
                "-fx-text-fill: " + hotelData.getSecondaryColor() + "; " +
                "-fx-background-color: white; " +
                "-fx-padding: 10px 20px; " +
                "-fx-background-radius: 25px; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 1);");

        // حاوية الوجبات
        HBox mealsContainer = new HBox(25);
        mealsContainer.setAlignment(Pos.CENTER);

        // وجبة الإفطار
        VBox breakfastBox = createEnhancedMealBox("🌅 BREAKFAST",
                "07:00 AM - 10:00 AM",
                "• Fresh Eggs & Omelettes\n• Toast & Croissants\n• Coffee & Tea\n• Fresh Orange Juice\n• Cereals & Yogurt",
                "#ff6b6b");

        // وجبة الغداء
        VBox lunchBox = createEnhancedMealBox("🌞 LUNCH",
                "12:00 PM - 02:00 PM",
                "• Garden Fresh Salad\n• Grilled Chicken\n• Steamed Rice\n• Soup of the Day\n• Fresh Bread",
                "#4ecdc4");

        // وجبة العشاء
        VBox dinnerBox = createEnhancedMealBox("🌙 DINNER",
                "06:00 PM - 10:00 PM",
                "• Premium Steak\n• Pasta Selection\n• Gourmet Dessert\n• Fine Wine\n• Chef's Special",
                "#45b7d1");

        mealsContainer.getChildren().addAll(breakfastBox, lunchBox, dinnerBox);

        // معلومات إضافية
        VBox infoBox = new VBox(10);
        infoBox.setAlignment(Pos.CENTER);
        infoBox.setStyle("-fx-background-color: rgba(255,255,255,0.9); " +
                "-fx-padding: 20px; " +
                "-fx-background-radius: 15px; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 8, 0, 0, 2);");

        Label infoTitle = new Label("🏨 Hotel Dining Information");
        infoTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; " +
                "-fx-text-fill: " + hotelData.getDarkColor() + ";");

        Label infoText = new Label("All meals are served in our elegant dining hall\n" +
                "Room service available 24/7 with additional charges\n" +
                "Special dietary requirements can be accommodated");
        infoText.setStyle("-fx-font-size: 12px; -fx-text-fill: #666; -fx-text-alignment: center;");
        infoText.setWrapText(true);

        infoBox.getChildren().addAll(infoTitle, infoText);

        // زر العودة
        Button backBtn = new Button("← Back to Main Menu");
        backBtn.setStyle("-fx-background-color: " + hotelData.getPrimaryColor() + "; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 14px; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 12px 25px; " +
                "-fx-background-radius: 25px; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 2);");



        backBtn.setOnAction(e -> stage.close());

        layout.getChildren().addAll(title, dateLabel, mealsContainer, infoBox, backBtn);

        Scene scene = new Scene(layout, 900, 700);
        stage.setScene(scene);
        stage.setTitle("Hotel Meal Services");
        stage.show();
    }

    private VBox createEnhancedMealBox(String mealName, String time, String menu, String accentColor) {
        VBox box = new VBox(15);
        box.setAlignment(Pos.TOP_CENTER);
        box.setStyle("-fx-background-color: white; " +
                "-fx-padding: 25px; " +
                "-fx-background-radius: 20px; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 12, 0, 0, 3); " +
                "-fx-border-color: " + accentColor + "; " +
                "-fx-border-width: 3px 0 0 0;");
        box.setMaxWidth(250);
        box.setMinHeight(300);

        // اسم الوجبة
        Label mealLabel = new Label(mealName);
        mealLabel.setStyle("-fx-font-weight: bold; " +
                "-fx-font-size: 18px; " +
                "-fx-text-fill: " + accentColor + ";");

        // وقت الوجبة
        Label timeLabel = new Label("⏰ " + time);
        timeLabel.setStyle("-fx-font-size: 14px; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: " + hotelData.getDarkColor() + "; " +
                "-fx-background-color: #f8f9fa; " +
                "-fx-padding: 8px 15px; " +
                "-fx-background-radius: 15px;");

        // خط فاصل
        VBox separator = new VBox();
        separator.setStyle("-fx-background-color: " + accentColor + "; " +
                "-fx-pref-height: 2px; " +
                "-fx-max-height: 2px;");

        // قائمة الطعام
        Label menuLabel = new Label(menu);
        menuLabel.setStyle("-fx-font-size: 12px; " +
                "-fx-text-fill: #555; " +
                "-fx-line-spacing: 3px;");
        menuLabel.setWrapText(true);

        box.getChildren().addAll(mealLabel, timeLabel, separator, menuLabel);

        return box;
    }
}