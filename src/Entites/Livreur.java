/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author DELL
 */
public class Livreur {
    private int id;
    private String nom;
    private String prenom;
    private int numtel;
    private String email;
    
    public Livreur() {
    }

    public Livreur(int id, String nom, String prenom, int numtel, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.email = email;
    }

    public Livreur(String nom, String prenom, int numtel, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.email = email;
    }

   

  
     
    public int getId() {
        return id;
    }

  
    public String getNom() {
        return nom;
    }

    public int getNumtel() {
        return numtel;
    }

    public String getPrenom() {
        return prenom;
    }

   

    public String getEmail() {
        return email;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

  

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Livreur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", numtel=" + numtel + ", email=" + email + '}' + "\n";
    }

  
}
