package View;

import Model.HotelData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView {
    private HotelData hotelData;

    public MainView(HotelData hotelData) {
        this.hotelData = hotelData;
    }

    public void show(Stage primaryStage) {

        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setStyle("-fx-background-color: " + hotelData.getLightColor() + ";");
        mainLayout.setAlignment(Pos.CENTER);

        Label title = new Label("Hotel Management System");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: " + hotelData.getPrimaryColor() + ";");

        // إنشاء أزرار القائمة الرئيسية
        Button guestsBtn = createMenuButton("GUESTS");
        Button roomsBtn = createMenuButton("ROOMS");
        Button mealsBtn = createMenuButton("MEALS");
        Button reportsBtn = createMenuButton("REPORTS");
        Button settingsBtn = createMenuButton("SETTINGS");

        // تعيين أحداث الأزرار
        guestsBtn.setOnAction(e -> new GuestManagementView(hotelData).show());
        roomsBtn.setOnAction(e -> new RoomManagementView(hotelData).show());
        mealsBtn.setOnAction(e -> new MealServicesView(hotelData).show());
        reportsBtn.setOnAction(e -> new ReportsView(hotelData).show());
        settingsBtn.setOnAction(e -> new SettingsView(hotelData).show());

        GridPane menuGrid = new GridPane();
        menuGrid.setHgap(20);
        menuGrid.setVgap(20);
        menuGrid.setAlignment(Pos.CENTER);
        menuGrid.add(guestsBtn, 0, 0);
        menuGrid.add(roomsBtn, 1, 0);
        menuGrid.add(mealsBtn, 2, 0);
        menuGrid.add(reportsBtn, 0, 1);
        menuGrid.add(settingsBtn, 1, 1);

        // شريط الحالة
        Label statusBar = new Label("System Ready | Rooms: " + hotelData.getRooms().size() +
                " | Guests: " + hotelData.getGuests().size());
        statusBar.setStyle("-fx-background-color: " + hotelData.getDarkColor() + "; -fx-text-fill: white; -fx-padding: 10px;");

        mainLayout.getChildren().addAll(title, menuGrid, statusBar);

        Scene scene = new Scene(mainLayout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hotel Management System");
        primaryStage.show();
    }

    private Button createMenuButton(String text) {
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: " + hotelData.getSecondaryColor() +
                "; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 15px 30px;");
        btn.setMinSize(150, 50);
        return btn;
    }
}