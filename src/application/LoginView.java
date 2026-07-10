package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {
    public static void show(Stage stage) {
        Label title = new Label("SunDevil Gear Share System");
        title.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");

        Label message = new Label("Please register an account before logging in.");
        message.setStyle("-fx-text-fill: #8C1D40; -fx-font-weight: bold;");

        TextField email = new TextField();
        email.setPromptText("Email");
        email.setMaxWidth(260);

        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        password.setMaxWidth(260);

        Button login = new Button("Log In");
        login.setPrefWidth(260);
        login.setStyle("-fx-background-color: #FFC627; -fx-font-weight: bold;");

        login.setOnAction(e -> {
            if (email.getText().isEmpty() || password.getText().isEmpty()) {
                message.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                message.setText("Please enter email and password.");
                return;
            }

            User user = DataStore.findUser(email.getText(), password.getText());

            if (user == null) {
                message.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                message.setText("Account not found. Please register first.");
                return;
            }

            DataStore.currentUser = user.getName();
            DataStore.currentUserEmail = user.getEmail();

            message.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
            message.setText("Login successful.");

            BrowseView.show(stage);
        });

        Hyperlink register = new Hyperlink("Create Account");
        register.setOnAction(e -> RegisterView.show(stage));

        VBox root = new VBox(
                12,
                title,
                new Label("Welcome Back"),
                email,
                password,
                login,
                register,
                message
        );

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));

        stage.setScene(new Scene(root, 1000, 650));
    }
}
