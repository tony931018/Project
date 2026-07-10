package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProfileView {
    public static void show(Stage stage) {
        VBox root = new VBox();
        root.getChildren().add(Navigation.create(stage, "Profile"));

        Label title = new Label("Profile");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label message = new Label("");
        message.setStyle("-fx-font-weight: bold;");

        TextField name = field("Name");
        TextField address = field("Address");
        TextField city = field("City");
        TextField email = field("Email");
        TextField phone = field("Phone Number");

        name.setText(DataStore.profileName);
        address.setText(DataStore.profileAddress);
        city.setText(DataStore.profileCity);
        email.setText(DataStore.profileEmail);
        phone.setText(DataStore.profilePhone);

        Button save = new Button("Save Profile");
        save.setStyle("-fx-background-color: #FFC627; -fx-font-weight: bold;");

        save.setOnAction(e -> {
            if (name.getText().isEmpty()
                    || address.getText().isEmpty()
                    || city.getText().isEmpty()
                    || email.getText().isEmpty()
                    || phone.getText().isEmpty()) {

                message.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                message.setText("Please complete all profile fields before adding listings.");
                return;
            }

            DataStore.profileName = name.getText();
            DataStore.profileAddress = address.getText();
            DataStore.profileCity = city.getText();
            DataStore.profileEmail = email.getText();
            DataStore.profilePhone = phone.getText();
            DataStore.profileCompleted = true;

            message.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
            message.setText("Profile completed. You can now add listings.");
        });

        VBox content = new VBox(
                12,
                title,
                message,
                name,
                address,
                city,
                email,
                phone,
                save
        );

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
