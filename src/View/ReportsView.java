package View;

import Model.HotelData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReportsView {
    private HotelData hotelData;

    public ReportsView(HotelData hotelData) {
        this.hotelData = hotelData;
    }

    public void show() {
        Stage stage = new Stage();
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: " + hotelData.getLightColor() + ";");
        layout.setAlignment(Pos.CENTER);

        Label title = new Label("Reports");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);

        // بطاقات التقارير
        Button financialBtn = createReportButton("Financial Report");
        Button occupancyBtn = createReportButton("Occupancy Report");
        Button guestBtn = createReportButton("Guest Report");
        Button serviceBtn = createReportButton("Service Report");

        grid.add(financialBtn, 0, 0);
        grid.add(occupancyBtn, 1, 0);
        grid.add(guestBtn, 0, 1);
        grid.add(serviceBtn, 1, 1);

        // زر العودة
        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: " + hotelData.getPrimaryColor() + "; -fx-text-fill: white;");
        backBtn.setOnAction(e -> stage.close());

        layout.getChildren().addAll(title, grid, backBtn);
        Scene scene = new Scene(layout, 500, 400);
        stage.setScene(scene);
        stage.setTitle("Reports");
        stage.show();
    }

    private Button createReportButton(String text) {
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: " + hotelData.getSecondaryColor() + "; -fx-text-fill: white;");
        btn.setMinSize(200, 100);
        return btn;
    }
}
