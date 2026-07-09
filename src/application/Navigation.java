package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Navigation {
    public static HBox create(Stage stage) {
        Label logo = new Label("SUNDEVIL Gear Share");
        logo.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");

        Button browse = button("Browse");
        Button rentals = button("My Rentals");
        Button listings = button("My Listings");
        Button add = button("Add Listing");
        Button profile = button("Profile");
        Button admin = button("Admin");
        Button logout = button("Logout");

        browse.setOnAction(e -> BrowseView.show(stage));
        rentals.setOnAction(e -> MyRentalsView.show(stage));
        listings.setOnAction(e -> MyListingsView.show(stage));
        add.setOnAction(e -> EquipmentListingView.show(stage));
        profile.setOnAction(e -> ProfileView.show(stage));
        admin.setOnAction(e -> AdminView.show(stage));
        logout.setOnAction(e -> LoginView.show(stage));

        HBox nav = new HBox(10, logo, browse, rentals, listings, add, profile, admin, logout);
        nav.setPadding(new Insets(15));
        nav.setAlignment(Pos.CENTER_LEFT);
        nav.setStyle("-fx-background-color: #8C1D40;");

        return nav;
    }

    private static Button button(String text) {
        Button button = new Button(text);
        button.setPrefWidth(115);
        return button;
    }
}
