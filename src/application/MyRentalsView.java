package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyRentalsView {
    public static void show(Stage stage) {
        VBox root = new VBox();
        root.getChildren().add(Navigation.create(stage));

        Label title = new Label("My Rentals");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        VBox list = new VBox(10);

        boolean found = false;

        for (Rental rental : DataStore.rentals) {
            if (rental.getRenter().equals(DataStore.currentUser)) {
                found = true;

                Button returnBtn = new Button("Return Item");

                returnBtn.setOnAction(e -> {
                    rental.getEquipment().returnItem();
                    DataStore.rentals.remove(rental);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Item returned.");
                    alert.showAndWait();

                    show(stage);
                });

                VBox card = new VBox(8, new Label(rental.toString()), returnBtn);
                card.setPadding(new Insets(12));
                card.setStyle("-fx-border-color: lightgray;");

                list.getChildren().add(card);
            }
        }

        if (!found) {
            list.getChildren().add(new Label("You have no rented items."));
        }

        VBox content = new VBox(15, title, list);
        content.setPadding(new Insets(25));

        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);

        root.getChildren().add(scroll);

        stage.setScene(new Scene(root, 1000, 650));
    }
}
