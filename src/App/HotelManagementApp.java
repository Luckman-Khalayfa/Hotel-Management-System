package App;

import Model.HotelData;
import View.LoginWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class HotelManagementApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        HotelData hotelData = new HotelData();
        new LoginWindow().show(primaryStage, hotelData);
    }
}
