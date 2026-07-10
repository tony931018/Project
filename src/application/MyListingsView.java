package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MyListingsView {
    public static void show(Stage stage) {
        VBox root = new VBox();
        root.getChildren().add(Navigation.create(stage, "My Listings"));

        Label title = new Label("My Listings");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label message = new Label("");
        message.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");

        VBox list = new VBox(10);

        refreshListings(stage, list, message);

        VBox content = new VBox(15, title, message, list);
        content.setPadding(new Insets(25));

        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);

        root.getChildren().add(scroll);

        stage.setScene(new Scene(root, 1000, 650));
    }

    private static void refreshListings(Stage stage, VBox list, Label message) {
        list.getChildren().clear();

        if (DataStore.myListings.isEmpty()) {
            list.getChildren().add(new Label("You have no listings yet."));
            return;
        }

        ArrayList<Equipment> copyList = new ArrayList<>(DataStore.myListings);

        for (Equipment item : copyList) {
            Button details = new Button("View Details");

            
            details.setOnAction(e -> EquipmentDetailsView.showOwnerDetails(stage, item));

            Button delete = new Button("Delete Listing");
            delete.setOnAction(e -> {
                DataStore.equipmentList.remove(item);
                DataStore.myListings.remove(item);

                message.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                message.setText("Listing deleted.");

                refreshListings(stage, list, message);
            });

            HBox buttons = new HBox(10, details, delete);

            VBox card = new VBox(8, new Label(item.toString()), buttons);
            card.setPadding(new Insets(12));
            card.setStyle("-fx-border-color: lightgray; -fx-border-radius: 8;");

            list.getChildren().add(card);
        }
    }
}
