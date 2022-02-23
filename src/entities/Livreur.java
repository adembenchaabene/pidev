/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author DELL
 */
public class Livreur {
    private int id;
    private String nom;
    private int numtel;

    public Livreur() {
    }

    public Livreur(int id, String nom, int numtel) {
        this.id = id;
        this.nom = nom;
        this.numtel = numtel;
    }

     public Livreur( String nom, int numtel) {
        
        this.nom = nom;
        this.numtel = numtel;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    @Override
    public String toString() {
        return "Livreur{" + "id=" + id + ", nom=" + nom + ", numtel=" + numtel + '}';
    }

    
    
}
