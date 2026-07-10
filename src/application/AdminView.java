package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminView {
    public static void show(Stage stage) {
        VBox root = new VBox();
        root.getChildren().add(Navigation.create(stage, "Admin"));

        Label title = new Label("Admin Dashboard");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        HBox stats = new HBox(
                15,
                box("Users: " + DataStore.users.size()),
                box("Active Listings: " + DataStore.equipmentList.size()),
                box("Current Rentals: " + DataStore.rentals.size()),
                box("Revenue: $" + String.format("%.2f", DataStore.totalRevenue()))
        );

        TextArea area = new TextArea("Click a management button to view details.");
        area.setEditable(false);
        area.setPrefHeight(280);

        Button users = new Button("View Users");
        users.setOnAction(e -> {
            String text = "Users:\n\n";

            for (User user : DataStore.users) {
                text += "- " + user + "\n";
            }

            area.setText(text);
        });

        Button listings = new Button("View Listings");
        listings.setOnAction(e -> {
            String text = "Listings:\n\n";

            for (Equipment item : DataStore.equipmentList) {
                text += "- " + item + "\n";
            }

            area.setText(text);
        });

        Button rentals = new Button("View Rentals");
        rentals.setOnAction(e -> {
            String text = "Rentals:\n\n";

            if (DataStore.rentals.isEmpty()) {
                text += "No current rentals.";
            }

            for (Rental rental : DataStore.rentals) {
                text += "- " + rental + "\n";
            }

            area.setText(text);
        });

        Button reports = new Button("View Reports");
        reports.setOnAction(e -> {
            String text = "Reported Issues:\n\n";

            if (DataStore.reports.isEmpty()) {
                text += "No reports.";
            }

            for (String report : DataStore.reports) {
                text += "- " + report + "\n";
            }

            area.setText(text);
        });

        HBox buttons = new HBox(15, users, listings, rentals, reports);

        VBox content = new VBox(20, title, stats, buttons, area);
        content.setPadding(new Insets(25));

        root.getChildren().add(content);

        stage.setScene(new Scene(root, 1000, 650));
    }

    private static Label box(String text) {
        Label label = new Label(text);
        label.setPadding(new Insets(15));
        label.setStyle("-fx-background-color: lightgray; -fx-font-weight: bold;");
        return label;
    }
}
