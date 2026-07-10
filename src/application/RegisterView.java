package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterView {
    public static void show(Stage stage) {
        Label title = new Label("Create Account");
        title.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");

        Label message = new Label("");
        message.setStyle("-fx-font-weight: bold;");

        TextField name = field("Name");
        TextField email = field("Email");

        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        password.setMaxWidth(260);

        RadioButton renter = new RadioButton("Renter");
        RadioButton owner = new RadioButton("Owner");

        ToggleGroup group = new ToggleGroup();
        renter.setToggleGroup(group);
        owner.setToggleGroup(group);
        renter.setSelected(true);

        HBox roles = new HBox(20, renter, owner);
        roles.setAlignment(Pos.CENTER);

        Button signUp = new Button("Sign Up");
        signUp.setPrefWidth(260);
        signUp.setStyle("-fx-background-color: #FFC627; -fx-font-weight: bold;");

        signUp.setOnAction(e -> {
            if (name.getText().isEmpty() || email.getText().isEmpty() || password.getText().isEmpty()) {
                message.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                message.setText("Please complete all fields.");
                return;
            }

            if (DataStore.emailExists(email.getText())) {
                message.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                message.setText("This email is already registered.");
                return;
            }

            String role;

            if (owner.isSelected()) {
                role = "Owner";
            } else {
                role = "Renter";
            }

            User newUser = new User(name.getText(), email.getText(), password.getText(), role);
            DataStore.users.add(newUser);

            message.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
            message.setText("Account created successfully. Please go back and log in.");

            name.clear();
            email.clear();
            password.clear();
            renter.setSelected(true);
        });

        Hyperlink login = new Hyperlink("Already have an account? Log In");
        login.setOnAction(e -> LoginView.show(stage));

        VBox root = new VBox(
                12,
                title,
                name,
                email,
                password,
                roles,
                signUp,
                login,
                message
        );

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));

        stage.setScene(new Scene(root, 1000, 650));
    }

    private static TextField field(String prompt) {
        TextField field = new TextField();
        field.setPromptText(prompt);
        field.setMaxWidth(260);
        return field;
    }
}
