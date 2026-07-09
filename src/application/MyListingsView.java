package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyListingsView {
    public static void show(Stage stage) {
        VBox root = new VBox();
        root.getChildren().add(Navigation.create(stage));

        Label title = new Label("My Listings");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        VBox list = new VBox(10);

        if (DataStore.myListings.isEmpty()) {
            list.getChildren().add(new Label("You have no listings yet."));
        } else {
            for (Equipment item : DataStore.myListings) {
                Button details = new Button("View Details");
                details.setOnAction(e -> EquipmentDetailsView.show(stage, item));

                Button delete = new Button("Delete Listing");
                delete.setOnAction(e -> {
                    DataStore.equipmentList.remove(item);
                    DataStore.myListings.remove(item);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Listing deleted.");
                    alert.showAndWait();

                    show(stage);
                });

                HBox buttons = new HBox(10, details, delete);

                VBox card = new VBox(8, new Label(item.toString()), buttons);
                card.setPadding(new Insets(12));
                card.setStyle("-fx-border-color: lightgray;");

                list.getChildren().add(card);
            }
        }

        VBox content = new VBox(15, title, list);
        content.setPadding(new Insets(25));

        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);

        root.getChildren().add(scroll);

        stage.setScene(new Scene(root, 1000, 650));
    }
}
