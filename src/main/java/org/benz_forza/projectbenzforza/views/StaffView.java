package org.benz_forza.projectbenzforza.views;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.benz_forza.projectbenzforza.entities.Staff;
import org.benz_forza.projectbenzforza.DAO.StaffDAO;



//Johan
public class StaffView {

    private final StaffDAO staffDAO = new StaffDAO();

    public void start(Stage staffStage) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.DARKVIOLET, new CornerRadii(5), new Insets(5))));

        Label titleLabel = new Label("Staff Management");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: black; -fx-effect: dropshadow(three-pass-box,White,5,0,0,0);");

        Button viewAllStaffButton = new Button("View All Staff");
        viewAllStaffButton.setPrefSize(150,50);
        viewAllStaffButton.setOnAction(e -> displayAllStaff());

        Button addStaffButton = new Button("Add New Staff");
        addStaffButton.setPrefSize(150,50);
        addStaffButton.setOnAction(e -> addStaffForm());

        Button editStaffButton = new Button("Edit Staff");
        editStaffButton.setPrefSize(150,50);
        editStaffButton.setOnAction(e -> editStaffForm());

        Button backButton = new Button("Back to Menu");
        backButton.setPrefSize(150,50);
        backButton.setOnAction(e -> staffStage.close());

        root.getChildren().addAll(titleLabel, viewAllStaffButton, addStaffButton, editStaffButton, backButton);

        Scene scene = new Scene(root, 800, 800);
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
        Scene scene = new Scene(vbox, 800, 800);
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
        Scene scene = new Scene(vbox, 800, 800);
        stage.setScene(scene);
        stage.setTitle("Add New Staff");
        stage.show();
    }

public void editStaffForm() {
    Stage stage = new Stage();
    VBox vbox = new VBox(10);
    vbox.setPadding(new Insets(10));

    TableView<Staff> staffTable = new TableView<>();

    TableColumn<Staff, String> firstNameColumn = new TableColumn<>("First Name");
    firstNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFirstName()));

    TableColumn<Staff, String> lastNameColumn = new TableColumn<>("Last Name");
    lastNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLastName()));

    TableColumn<Staff, String> nicknameColumn = new TableColumn<>("Nickname");
    nicknameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNickname()));

    TableColumn<Staff, String> emailColumn = new TableColumn<>("Email");
    emailColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));

    TableColumn<Staff, String> addressColumn = new TableColumn<>("Address");
    addressColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAddress()));

    TableColumn<Staff, String> postalCodeColumn = new TableColumn<>("Postal Code");
    postalCodeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPostalCode()));

    TableColumn<Staff, String> cityColumn = new TableColumn<>("City");
    cityColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCity()));

    TableColumn<Staff, String> countryColumn = new TableColumn<>("Country");
    countryColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCountry()));

    staffTable.getColumns().addAll(
            firstNameColumn,
            lastNameColumn,
            nicknameColumn,
            emailColumn,
            addressColumn,
            postalCodeColumn,
            cityColumn,
            countryColumn
    );

    try {
        staffTable.getItems().addAll(staffDAO.findAll());
    } catch (Exception e) {
        System.err.println("Error loading staff: " + e.getMessage());
    }

    TextField firstName = new TextField();
    TextField lastName = new TextField();
    TextField nickname = new TextField();
    TextField email = new TextField();
    TextField address = new TextField();
    TextField postalCode = new TextField();
    TextField city = new TextField();
    TextField country = new TextField();

    Label validationMessage = new Label();
    validationMessage.setStyle("-fx-text-fill: red;");

    staffTable.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
        if (newSelection != null) {
            firstName.setText(newSelection.getFirstName());
            lastName.setText(newSelection.getLastName());
            nickname.setText(newSelection.getNickname());
            email.setText(newSelection.getEmail());
            address.setText(newSelection.getAddress());
            postalCode.setText(newSelection.getPostalCode());
            city.setText(newSelection.getCity());
            country.setText(newSelection.getCountry());
        }
    });

    Button saveButton = new Button("Save");
    saveButton.setOnAction(event -> {
        Staff selectedStaff = staffTable.getSelectionModel().getSelectedItem();
        if (selectedStaff == null) {
            validationMessage.setText("Select a Staff to edit");
            return;
        }

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
            selectedStaff.setFirstName(firstName.getText());
            selectedStaff.setLastName(lastName.getText());
            selectedStaff.setNickname(nickname.getText());
            selectedStaff.setEmail(email.getText());
            selectedStaff.setAddress(address.getText());
            selectedStaff.setPostalCode(postalCode.getText());
            selectedStaff.setCity(city.getText());
            selectedStaff.setCountry(country.getText());

            staffDAO.update(selectedStaff);
            staffTable.refresh();
            stage.close();
        }
    });

    Button cancelButton = new Button("Cancel");
    cancelButton.setOnAction(event -> stage.close());

    HBox buttonBox = new HBox(10, saveButton, cancelButton);
    buttonBox.setAlignment(Pos.CENTER);

    vbox.getChildren().addAll(
            new Label("First Name:"), firstName,
            new Label("Last Name:"), lastName,
            new Label("Nickname:"), nickname,
            new Label("Email:"), email,
            new Label("Address:"), address,
            new Label("Postal Code:"), postalCode,
            new Label("City:"), city,
            new Label("Country:"), country,
            validationMessage,
            staffTable,
            buttonBox
    );

    Scene scene = new Scene(vbox, 800, 800);
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
