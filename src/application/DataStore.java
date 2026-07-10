package application;

import java.util.ArrayList;

public class DataStore {
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Equipment> equipmentList = new ArrayList<>();
    public static ArrayList<Rental> rentals = new ArrayList<>();
    public static ArrayList<Equipment> myListings = new ArrayList<>();
    public static ArrayList<String> reports = new ArrayList<>();

    public static String currentUser = "";
    public static String currentUserEmail = "";

    public static boolean profileCompleted = false;
    public static String profileName = "";
    public static String profileAddress = "";
    public static String profileCity = "";
    public static String profileEmail = "";
    public static String profilePhone = "";

    private static boolean loaded = false;

    public static void loadSampleData() {
        if (loaded) {
            return;
        }

        loaded = true;

        // Only sample equipment is loaded.
        // Users must register before logging in.
        equipmentList.add(new Equipment("Canon Camera", "Camera & Photo", "Like New", "Samuel", 700));
        equipmentList.add(new Equipment("MacBook Pro", "Computer & Laptop", "Good", "Samuel", 1200));
        equipmentList.add(new Equipment("LG 24 Monitor", "Electronics Devices", "Used", "Samuel", 300));
        equipmentList.add(new Equipment("Gaming Controller", "Gaming Console", "Good", "Samuel", 80));
        equipmentList.add(new Equipment("Samsung Phone", "Electronics Devices", "Used", "Samuel", 500));
    }

    public static User findUser(String email, String password) {
        for (User user : users) {
            if (user.checkLogin(email, password)) {
                return user;
            }
        }

        return null;
    }

    public static boolean emailExists(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }

        return false;
    }

    public static double totalRevenue() {
        double total = 0;

        for (Rental rental : rentals) {
            total += rental.getTotal();
        }

        return total;
    }
}
