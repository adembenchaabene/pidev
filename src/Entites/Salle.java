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
    private String image;
    private int capacite;
    
 

    public Salle() {
    }

    public Salle(String nom, float prix, String image,int capacite) {
        this.nom = nom;
        this.prix = prix;
        this.image = image;
        this.capacite=capacite;
    }
    
    
    public Salle(int idSalle, String nom, float prix, String image,int capacite) {
        this.idSalle = idSalle;
        this.nom = nom;
        this.prix = prix;
        this.image = image;
        this.capacite=capacite;
    }

   
   

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return   "Salle{" + "idSalle=" + idSalle + ", nom=" + nom + ", prix=" + prix + ", image=" + image + " capacite=" + capacite + "}  '\n'  "; 
    }

    
}
