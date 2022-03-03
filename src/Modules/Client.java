package Modules;

import Utils.Enums.Roles;

public class Client extends User {

    public Client() {
    }
    

    public Client(int idUser, String nom, String prenom, String email, String password, Roles role) {

        super(idUser, nom, prenom, email, password, role);
    }

    @Override
    public String toString() {
        return super.toString();

    }
}
