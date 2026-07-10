package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EquipmentListingView {
    public static void show(Stage stage) {
        VBox root = new VBox();
        root.getChildren().add(Navigation.create(stage));

        Label title = new Label("Add Equipment Listing");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextField name = field("Equipment Name");

        ComboBox<String> category = new ComboBox<>();
        category.getItems().addAll(
                "Electronics Devices",
                "Computer & Laptop",
                "Camera & Photo",
                "Gaming Console"
        );
        category.setPromptText("Category");
        category.setMaxWidth(350);

        ComboBox<String> condition = new ComboBox<>();
        condition.getItems().addAll("Like New", "Good", "Used");
        condition.setPromptText("Condition");
        condition.setMaxWidth(350);

        TextField value = field("Estimated Value");

        Label preview = new Label("Daily rate and deposit will be calculated automatically.");

        Button previewBtn = new Button("Preview Price");
        previewBtn.setOnAction(e -> {
            try {
                double itemValue = Double.parseDouble(value.getText());

                preview.setText(
                        "Daily Rate: $"
                                + String.format("%.2f", itemValue * 0.05)
                                + " | Deposit: $"
                                + String.format("%.2f", itemValue * 0.20)
                );
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Enter a valid value.");
                alert.showAndWait();
            }
        });

        Button publish = new Button("Publish Product");
        publish.setStyle("-fx-background-color: #8C1D40; -fx-text-fill: white; -fx-font-weight: bold;");

        publish.setOnAction(e -> {
            if (name.getText().isEmpty()
                    || category.getValue() == null
                    || condition.getValue() == null
                    || value.getText().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please complete all fields.");
                alert.showAndWait();
                return;
            }

            try {
                Equipment item = new Equipment(
                        name.getText(),
                        category.getValue(),
                        condition.getValue(),
                        DataStore.currentUser,
                        Double.parseDouble(value.getText())
                );

                DataStore.equipmentList.add(item);
                DataStore.myListings.add(item);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Listing published.");
                alert.showAndWait();

                MyListingsView.show(stage);

            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Estimated value must be a number.");
                alert.showAndWait();
            }
        });

        VBox content = new VBox(12, title, name, category, condition, value, previewBtn, preview, publish);
        content.setPadding(new Insets(30));

        root.getChildren().add(content);

        stage.setScene(new Scene(root, 1000, 650));
    }

    private static TextField field(String prompt) {
        TextField field = new TextField();
        field.setPromptText(prompt);
        field.setMaxWidth(350);
        return field;
    }
}
