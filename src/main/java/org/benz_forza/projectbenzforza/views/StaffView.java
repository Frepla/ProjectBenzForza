package org.benz_forza.projectbenzforza.views;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.benz_forza.projectbenzforza.entities.Staff;
import org.benz_forza.projectbenzforza.services.StaffService;

public class StaffView {

    private final StaffService staffService = new StaffService();

    public void start(Stage staffStage) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Staff Management");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button viewAllStaffButton = new Button("View All Staff");
        viewAllStaffButton.setPrefWidth(200);
        viewAllStaffButton.setOnAction(e -> displayAllStaff());

        Button addStaffButton = new Button("Add New Staff");
        addStaffButton.setPrefWidth(200);
        addStaffButton.setOnAction(e -> addStaffForm());

        Button backButton = new Button("Back to Menu");
        backButton.setPrefWidth(200);
        backButton.setOnAction(e -> staffStage.close());

        root.getChildren().addAll(titleLabel, viewAllStaffButton, addStaffButton, backButton);

        Scene scene = new Scene(root, 400, 300);
        staffStage.setScene(scene);
        staffStage.setTitle("Staff Management");
        staffStage.show();
    }

    public void displayAllStaff() {
        Stage stage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        TableView<Staff> staffTable = new TableView<>();

        TableColumn<Staff, String> firstName = new TableColumn<>("First Name");
        TableColumn<Staff, String> lastName = new TableColumn<>("Last Name");
        TableColumn<Staff, String> nickname = new TableColumn<>("Nickname");
        TableColumn<Staff, String> email = new TableColumn<>("Email");
        TableColumn<Staff, String> address = new TableColumn<>("Address");
        TableColumn<Staff, String> postalCode = new TableColumn<>("Postal Code");
        TableColumn<Staff, String> city = new TableColumn<>("City");
        TableColumn<Staff, String> country = new TableColumn<>("Country");

        firstName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFirstName()));
        lastName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLastName()));
        nickname.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNickname()));
        email.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        address.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAddress()));
        postalCode.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPostalCode()));
        city.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCity()));
        country.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCountry()));

        staffTable.getColumns().addAll(firstName, lastName, nickname, email, address, postalCode, city, country);

        try {
            staffTable.getItems().addAll(staffService.getAllStaff());
        } catch (Exception e) {
            System.err.println("Error loading staff: " + e.getMessage());
        }

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> stage.close());

        vbox.getChildren().addAll(staffTable, backButton);
        Scene scene = new Scene(vbox, 800, 600);
        stage.setScene(scene);
        stage.setTitle("All Staff");
        stage.show();
    }

    public void addStaffForm() {
        Stage stage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        TextField firstName = new TextField();
        firstName.setPromptText("First Name");

        TextField lastName = new TextField();
        lastName.setPromptText("Last Name");

        TextField nickname = new TextField();
        nickname.setPromptText("Nickname");

        TextField email = new TextField();
        email.setPromptText("Email");

        TextField address = new TextField();
        address.setPromptText("Address");

        TextField postalCode = new TextField();
        postalCode.setPromptText("Postal Code");

        TextField city = new TextField();
        city.setPromptText("City");

        TextField country = new TextField();
        country.setPromptText("Country");

        Button addStaff = new Button("Add Staff");

        addStaff.setOnAction(event -> {
            Staff staff = new Staff();
            staff.setFirstName(firstName.getText());
            staff.setLastName(lastName.getText());
            staff.setNickname(nickname.getText());
            staff.setEmail(email.getText());
            staff.setAddress(address.getText());
            staff.setPostalCode(postalCode.getText());
            staff.setCity(city.getText());
            staff.setCountry(country.getText());
            staffService.addStaff(staff);
            displayAllStaff();
            stage.close();
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> stage.close());

        HBox buttonBox = new HBox(10, addStaff, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(firstName, lastName, nickname, email, address, postalCode, city, country, buttonBox);
        Scene scene = new Scene(vbox, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Add New Staff");
        stage.show();
    }
}
