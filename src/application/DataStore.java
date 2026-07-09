package application;

import java.util.ArrayList;

public class DataStore {
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Equipment> equipmentList = new ArrayList<>();
    public static ArrayList<Rental> rentals = new ArrayList<>();
    public static ArrayList<Equipment> myListings = new ArrayList<>();
    public static ArrayList<String> reports = new ArrayList<>();

    public static String currentUser = "Current User";
    private static boolean loaded = false;

    public static void loadSampleData() {
        if (loaded) {
            return;
        }

        loaded = true;

        users.add(new User("Admin", "admin@asu.edu", "Admin"));
        users.add(new User("John Smith", "john@asu.edu", "Renter"));
        users.add(new User("Samuel", "samuel@asu.edu", "Owner"));
        users.add(new User("Mark G.", "mark@asu.edu", "Renter"));

        equipmentList.add(new Equipment("Canon Camera", "Camera & Photo", "Like New", "Samuel", 700));
        equipmentList.add(new Equipment("MacBook Pro", "Computer & Laptop", "Good", "Samuel", 1200));
        equipmentList.add(new Equipment("LG 24 Monitor", "Electronics Devices", "Used", "Samuel", 300));
        equipmentList.add(new Equipment("Gaming Controller", "Gaming Console", "Good", "Samuel", 80));
        equipmentList.add(new Equipment("Samsung Phone", "Electronics Devices", "Used", "Samuel", 500));

        reports.add("User: Mark G. | Listing: Camera | Issue: Broken | Status: Pending");
    }

    public static double totalRevenue() {
        double total = 0;

        for (Rental rental : rentals) {
            total += rental.getTotal();
        }

        return total;
    }
}
