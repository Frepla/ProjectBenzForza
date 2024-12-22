package org.benz_forza.projectbenzforza.views;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.benz_forza.projectbenzforza.entities.Staff;
import org.benz_forza.projectbenzforza.DAO.StaffDAO;

public class StaffView {

    private final StaffDAO staffDAO = new StaffDAO();

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

        Button editStaffButton = new Button("Edit Staff");
        editStaffButton.setPrefWidth(200);
        editStaffButton.setOnAction(e -> editStaffForm());

        Button backButton = new Button("Back to Menu");
        backButton.setPrefWidth(200);
        backButton.setOnAction(e -> staffStage.close());

        root.getChildren().addAll(titleLabel, viewAllStaffButton, addStaffButton, editStaffButton, backButton);

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

        TableColumn<Staff, Void> deleteColumn = new TableColumn<>("Actions");

        deleteColumn.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Staff, Void> call(TableColumn<Staff, Void> param) {
                return new TableCell<>() {
                    private final Button deleteButton = new Button("Delete");


                    {
                        deleteButton.setOnAction(event -> {
                            Staff selectedStaff = getTableView().getItems().get(getIndex());
                            if (confirmDeletion(selectedStaff)) {
                                staffDAO.delete(selectedStaff.getId());
                                getTableView().getItems().remove(selectedStaff);
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(deleteButton);
                        }
                    }
                };
            }
        });

        staffTable.getColumns().addAll(firstName, lastName, nickname, email, address, postalCode, city, country, deleteColumn);

        try {
            staffTable.getItems().addAll(staffDAO.findAll());
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

        Label validationMessage = new Label();
        validationMessage.setStyle("-fx-text-fill: red;");

        Button addStaff = new Button("Add Staff");

        addStaff.setOnAction(event -> {
            StringBuilder errors = new StringBuilder();

            if (firstName.getText().trim().isEmpty()) {
                errors.append("First Name is required.\n");
            }
            if (lastName.getText().trim().isEmpty()) {
                errors.append("Last Name is required.\n");
            }
            if (!email.getText().matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                errors.append("Invalid email format.\n");
            }
            if (!postalCode.getText().matches("\\d{5}")) {
                errors.append("Postal Code must be 5 digits.\n");
            }
            if (errors.length() > 0) {
                validationMessage.setText(errors.toString());
            } else {

                Staff staff = new Staff();
                staff.setFirstName(firstName.getText());
                staff.setLastName(lastName.getText());
                staff.setNickname(nickname.getText());
                staff.setEmail(email.getText());
                staff.setAddress(address.getText());
                staff.setPostalCode(postalCode.getText());
                staff.setCity(city.getText());
                staff.setCountry(country.getText());
                staffDAO.create(staff);
                displayAllStaff();
                stage.close();
            }
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> stage.close());

        HBox buttonBox = new HBox(10, addStaff, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(firstName, lastName, nickname, email, address, postalCode, city, country, validationMessage, buttonBox);
        Scene scene = new Scene(vbox, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Add New Staff");
        stage.show();
    }

    public void editStaffForm() {
        Stage stage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        TableView<Staff> staffTable = new TableView<>();

        try {
            staffTable.getItems().addAll(staffDAO.findAll());
        } catch (Exception e) {
            System.err.println("Error loading staff: " + e.getMessage());
        }

        TextField firstName = new TextField();
        TextField lastName = new TextField();
        TextField email = new TextField();
        Label validationMessage = new Label();
        validationMessage.setStyle("-fx-text-fill: red;");

        staffTable.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
            if (newSelection != null) {
                firstName.setText(newSelection.getFirstName());
                lastName.setText(newSelection.getLastName());
                email.setText(newSelection.getEmail());
            }
        });

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            Staff selectedStaff = staffTable.getSelectionModel().getSelectedItem();
            if (selectedStaff == null) {
                if (firstName.getText().trim().isEmpty()) {
                    validationMessage.setText("First Name is required.\n");
                } else {
                    selectedStaff.setFirstName(firstName.getText());
                    selectedStaff.setLastName(lastName.getText());
                    selectedStaff.setEmail(email.getText());
                    staffDAO.update(selectedStaff);
                    staffTable.refresh();
                    stage.close();
                }
            } else {
                validationMessage.setText("Select a Staff to edit");
            }
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> stage.close());

        HBox buttonBox = new HBox(10, saveButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(new Label("First Name:"), firstName, new Label("Last Name:"), lastName, new Label("Email:"), email, validationMessage, staffTable, buttonBox);
        Scene scene = new Scene(vbox, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Edit Staff");
        stage.show();
    }
    private boolean confirmDeletion(Staff staff) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("Are you sure you want to delete?");
        alert.setContentText("Name: " + staff.getFirstName() + " " + staff.getLastName());

        return alert.showAndWait().filter(response -> response == ButtonType.OK).isPresent();
    }
}
