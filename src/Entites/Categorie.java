/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author compu serv
 */
public class Categorie {

    private int id_categorie;
    private String nomCateg;
    private String description;

    public Categorie() {
    }

    public Categorie(int id_categorie, String nomCateg, String description) {
        this.id_categorie = id_categorie;
        this.nomCateg = nomCateg;
        this.description = description;
    }

    public Categorie(String nomCateg, String description) {
        this.nomCateg = nomCateg;
        this.description = description;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getNomCateg() {
        return nomCateg;
    }

    public void setNomCateg(String nomCateg) {
        this.nomCateg = nomCateg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id_categorie=" + id_categorie + ", nomCateg=" + nomCateg + ", description=" + description +'}'+"\n";
    }
    

}
