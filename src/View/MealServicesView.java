package View;

import Model.HotelData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MealServicesView {
    private HotelData hotelData;

    public MealServicesView(HotelData hotelData) {
        this.hotelData = hotelData;
    }

    public void show() {
        Stage stage = new Stage();
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: " + hotelData.getLightColor() + ";");
        layout.setAlignment(Pos.CENTER);

        Label title = new Label("Meal Services");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // وجبة الإفطار
        VBox breakfastBox = createMealBox("Breakfast", "07:00 AM - 10:00 AM",
                "Eggs, Toast, Coffee, Juice");

        // وجبة الغداء
        VBox lunchBox = createMealBox("Lunch", "12:00 PM - 02:00 PM",
                "Salad, Chicken, Rice, Soup");

        // وجبة العشاء
        VBox dinnerBox = createMealBox("Dinner", "06:00 PM - 10:00 PM",
                "Steak, Pasta, Dessert, Wine");

        layout.getChildren().addAll(title, breakfastBox, lunchBox, dinnerBox);

        // زر العودة
        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: " + hotelData.getPrimaryColor() + "; -fx-text-fill: white;");
        backBtn.setOnAction(e -> stage.close());

        layout.getChildren().add(backBtn);
        Scene scene = new Scene(layout, 400, 500);
        stage.setScene(scene);
        stage.setTitle("Meal Services");
        stage.show();
    }

    private VBox createMealBox(String meal, String time, String menu) {
        VBox box = new VBox(5);
        box.setStyle("-fx-background-color: white; -fx-padding: 15px; -fx-border-radius: 5px;");
        box.setAlignment(Pos.CENTER_LEFT);

        Label mealLabel = new Label(meal);
        mealLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

        Label timeLabel = new Label("Time: " + time);
        Label menuLabel = new Label("Menu: " + menu);
        menuLabel.setWrapText(true);

        box.getChildren().addAll(mealLabel, timeLabel, new Separator(), menuLabel);
        return box;
    }
}
