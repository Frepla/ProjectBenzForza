package org.benz_forza.projectbenzforza.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.benz_forza.projectbenzforza.entities.Staff;
import org.benz_forza.projectbenzforza.DAO.StaffDAO;

import java.util.List;
//JESPER
public class StartView {
    public void start(Stage stage) throws Exception {
        VBox root = new VBox(30);
        root.setPadding(new Insets(20));

        Label title = new Label("Select a staff member");
        title.setFont(Font.font("Arial",20));
        title.setAlignment(Pos.CENTER);

        // Här lägger vi in listan med Staff. testade bara med PLayer.
        ListView<String> staffListView = new ListView<>();
        StaffDAO staffDAO = new StaffDAO();
        List<Staff> allStaff = StaffDAO.findAll();
        ObservableList<String> observableStaff = FXCollections.observableArrayList();

        for (Staff staff : allStaff) {
            String playerDetails = staff.getFirstName() + " " + staff.getLastName();
            observableStaff.add(playerDetails);
        }
        staffListView.setItems(observableStaff);

        Button enterButton = new Button("Enter");
        enterButton.setOnAction(e -> {
            stage.close();
            Stage menuStage = new Stage();
            MenuView menuView = new MenuView();
            try {
                menuView.start(menuStage);
            } catch (Exception ex) {
                System.out.println("Error while trying to load page: " + ex.getMessage());
            }
        });

        root.getChildren().addAll(title,staffListView, enterButton);

        Scene scene = new Scene(root,800,800);
        stage.setScene(scene);
        stage.setTitle("Start Page");
        stage.show();


    }

}


