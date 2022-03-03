package Modules;

import Utils.Enums.Roles;

public class User {

    private int idUser;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Roles role;

    public User() {
    }

    public User(int idUser, String nom, String prenom, String email, String password) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }

    public User(String nom, String prenom, String email, String password, Roles role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Roles getRole() {
        return role;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    protected User(int idUser, String nom, String prenom, String email, String password, Roles role) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(int idUser, String nom, String prenom, String email) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public User(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "idUser=" + idUser
                + ", nom='" + nom + '\''
                + ", prenom='" + prenom + '\''
                + ", email='" + email + '\''
                + ", password='" + password + '\''
                + ", role=" + role
                + '\n';
    }
}
