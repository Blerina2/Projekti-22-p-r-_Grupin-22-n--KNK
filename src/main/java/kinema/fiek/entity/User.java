package kinema.fiek.entity;

import java.util.List;

public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private kinema.fiek.entity.UserRole userRole;

    private List<kinema.fiek.entity.Rezervim> rezervim;

    public List<kinema.fiek.entity.Rezervim> getRezervim() {
        return rezervim;
    }

    public void setRezervim(List<kinema.fiek.entity.Rezervim> rezervim) {
        this.rezervim = rezervim;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public kinema.fiek.entity.UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(kinema.fiek.entity.UserRole userRole) {
        this.userRole = userRole;
    }
}