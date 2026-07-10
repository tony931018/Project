package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EquipmentDetailsView {
    public static void show(Stage stage, Equipment item) {
        VBox root = new VBox();
        root.getChildren().add(Navigation.create(stage));

        Label title = new Label(item.getName());
        title.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");

        ComboBox<Integer> days = new ComboBox<>();
        days.getItems().addAll(1, 2, 3, 5, 7, 14);
        days.setValue(1);

        Label total = new Label();
        updateTotal(total, item, days.getValue());

        days.setOnAction(e -> updateTotal(total, item, days.getValue()));

        Button rent = new Button("Rent Now");
        rent.setStyle("-fx-background-color: #8C1D40; -fx-text-fill: white; -fx-font-weight: bold;");

        rent.setOnAction(e -> {
            if (!item.isAvailable()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("This item is already rented.");
                alert.showAndWait();
                return;
            }

            item.rent();
            Rental rental = new Rental(item, DataStore.currentUser, days.getValue());
            DataStore.rentals.add(rental);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Rental confirmed.");
            alert.showAndWait();

            MyRentalsView.show(stage);
        });

        Button back = new Button("Back to Browse");
        back.setOnAction(e -> BrowseView.show(stage));

        VBox content = new VBox(
                12,
                title,
                new Label("Owner: " + item.getOwner()),
                new Label("Category: " + item.getCategory()),
                new Label("Condition: " + item.getCondition()),
                new Label("Estimated Value: $" + String.format("%.2f", item.getValue())),
                new Label("Daily Rate: $" + String.format("%.2f", item.getDailyRate())),
                new Label("Deposit: $" + String.format("%.2f", item.getDeposit())),
                new Label("Status: " + item.getStatus()),
                new Label("Rental Duration:"),
                days,
                total,
                rent,
                back
        );

        content.setPadding(new Insets(30));
        root.getChildren().add(content);

        stage.setScene(new Scene(root, 1000, 650));
    }

    private static void updateTotal(Label total, Equipment item, int days) {
        double price = item.getDailyRate() * days + item.getDeposit();
        total.setText("Total Cost: $" + String.format("%.2f", price));
        total.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
    }
}
