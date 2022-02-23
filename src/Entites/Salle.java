/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author mariem
 */
public class Salle {
    private int idSalle;
    private String nom;
    private float prix;
    private int idAdmin;

    public Salle() {
    }

    public Salle(String nom, float prix, int idAdmin) {
        this.nom = nom;
        this.prix = prix;
        this.idAdmin = idAdmin;
    }
    
    
    public Salle(int idSalle, String nom, float prix, int idAdmin) {
        this.idSalle = idSalle;
        this.nom = nom;
        this.prix = prix;
        this.idAdmin = idAdmin;
    }

    public int getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    @Override
    public String toString() {
        return "Salle{" + "idSalle=" + idSalle + ", nom=" + nom + ", prix=" + prix + ", idAdmin=" + idAdmin + '}' + "\n";
    }
    
}
