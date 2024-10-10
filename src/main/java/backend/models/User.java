package backend.models;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private double gewicht;
    private int age;
    private double groesse;
    private String geschlecht;
    private String role;

    public User(int id, String username, String email, String password, double gewicht, int age, double groesse, String geschlecht, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.gewicht = gewicht;
        this.age = age;
        this.groesse = groesse;
        this.geschlecht = geschlecht;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getGewicht() {
        return gewicht;
    }

    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGroesse() {
        return groesse;
    }

    public void setGroesse(double groesse) {
        this.groesse = groesse;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gewicht=" + gewicht +
                ", age=" + age +
                ", groesse=" + groesse +
                ", geschlecht='" + geschlecht + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
