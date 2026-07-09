package application;

public class User {
    private String name;
    private String email;
    private String role;
    private String phone;
    private String address;

    public User(String name, String email, String role) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.phone = "";
        this.address = "";
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return name + " | " + email + " | " + role;
    }
}
