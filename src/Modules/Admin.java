package Modules;

import Utils.Enums.Roles;

public class Admin extends User {

    public Admin( String nom, String prenom, String email, String password, Roles role) {

        super( nom, prenom, email, password, role);
    }
public Admin(int idUser, String nom, String prenom, String email, String password, Roles role) {

        super(idUser, nom, prenom, email, password, role);
    }
    @Override
    public String toString() {
        return super.toString();

    }
}
