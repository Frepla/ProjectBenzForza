package org.benz_forza.projectbenzforza.views;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.benz_forza.projectbenzforza.entities.Staff;
import org.benz_forza.projectbenzforza.services.StaffService;

public class StaffView {

    private StaffService staffService = new StaffService();

    public void displayAllStaff() {
        Stage stage = new Stage();
        VBox vbox = new VBox();
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

        vbox.getChildren().add(staffTable);
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();
    }

    public void addStaffForm() {
        Stage stage = new Stage();
        VBox vbox = new VBox();

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

        vbox.getChildren().addAll(firstName, lastName, nickname, email, address, postalCode, city, country, addStaff);
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();
    }
}
