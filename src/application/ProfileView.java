package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProfileView {
    public static void show(Stage stage) {
        VBox root = new VBox();
        root.getChildren().add(Navigation.create(stage));

        Label title = new Label("Profile");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextField name = field("Name");
        TextField address = field("Address");
        TextField city = field("City");
        TextField email = field("Email");
        TextField phone = field("Phone Number");

        Button save = new Button("Save Profile");

        save.setOnAction(e -> {
            if (name.getText().isEmpty() || email.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please enter name and email.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Profile saved.");
                alert.showAndWait();
            }
        });

        VBox content = new VBox(12, title, name, address, city, email, phone, save);
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
