package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MyRentalsView {
    public static void show(Stage stage) {
        VBox root = new VBox();
        root.getChildren().add(Navigation.create(stage, "My Rentals"));

        Label title = new Label("My Rentals");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label message = new Label("");
        message.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");

        VBox list = new VBox(10);

        refreshRentals(stage, list, message);

        VBox content = new VBox(15, title, message, list);
        content.setPadding(new Insets(25));

        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);

        root.getChildren().add(scroll);

        stage.setScene(new Scene(root, 1000, 650));
    }

    private static void refreshRentals(Stage stage, VBox list, Label message) {
        list.getChildren().clear();

        boolean found = false;

        ArrayList<Rental> copyList = new ArrayList<>(DataStore.rentals);

        for (Rental rental : copyList) {
            if (rental.getRenter().equals(DataStore.currentUser)) {
                found = true;

                Label rentalInfo = new Label(rental.toString());
                rentalInfo.setStyle("-fx-font-weight: bold;");

                ComboBox<String> issueBox = new ComboBox<>();
                issueBox.getItems().addAll(
                        "Item is broken",
                        "Item does not match description",
                        "Item is missing accessories",
                        "Item is unsafe",
                        "Owner problem",
                        "Return problem",
                        "Other"
                );
                issueBox.setPromptText("Choose issue type");
                issueBox.setMaxWidth(260);

                TextField issueDescription = new TextField();
                issueDescription.setPromptText("Describe issue if needed");
                issueDescription.setMaxWidth(350);

                Button reportBtn = new Button("Submit Report");
                reportBtn.setOnAction(e -> {
                    if (issueBox.getValue() == null) {
                        message.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                        message.setText("Please choose an issue type first.");
                        return;
                    }

                    String issue = issueBox.getValue();

                    if (issue.equals("Other")) {
                        if (issueDescription.getText().isEmpty()) {
                            message.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                            message.setText("Please describe the issue.");
                            return;
                        }

                        issue = issueDescription.getText();
                    }

                    DataStore.reports.add(
                            "User: " + DataStore.currentUser
                                    + " | Listing: " + rental.getEquipment().getName()
                                    + " | Issue: " + issue
                                    + " | Status: Pending"
                    );

                    message.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                    message.setText("Report submitted for " + rental.getEquipment().getName() + ".");

                    issueBox.setValue(null);
                    issueDescription.clear();
                });

                Button returnBtn = new Button("Return Item");
                returnBtn.setOnAction(e -> {
                    rental.getEquipment().returnItem();
                    DataStore.rentals.remove(rental);

                    message.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                    message.setText("Item returned.");

                    refreshRentals(stage, list, message);
                });

                VBox reportBox = new VBox(
                        6,
                        new Label("Report this rented item:"),
                        issueBox,
                        issueDescription,
                        reportBtn
                );

                VBox card = new VBox(
                        10,
                        rentalInfo,
                        reportBox,
                        returnBtn
                );

                card.setPadding(new Insets(12));
                card.setStyle("-fx-border-color: lightgray; -fx-border-radius: 8;");

                list.getChildren().add(card);
            }
        }

        if (!found) {
            list.getChildren().add(new Label("You have no rented items."));
        }
    }
}
