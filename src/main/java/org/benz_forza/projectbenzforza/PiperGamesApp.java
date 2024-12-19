package org.benz_forza.projectbenzforza;

import javafx.application.Application;
import javafx.stage.Stage;
import org.benz_forza.projectbenzforza.views.StartView;

import java.io.IOException;

public class PiperGamesApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        StartView startView = new StartView();
        try {
            startView.start(stage);
        } catch (Exception e) {
            System.out.println("Error while starting StartView: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
