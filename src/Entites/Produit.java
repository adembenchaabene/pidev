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
public class Produit {

    private int idProduit;
    private String nomProduit;
    private int quantite;
    private float prix;
    private int id_categorie;
    private String description;
    private String image;

    public Produit() {
    }

    public Produit(int idProduit, String nomProduit, int quantite, float prix, int id_categorie, String description, String image) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.quantite = quantite;
        this.prix = prix;
        this.id_categorie = id_categorie;
        this.description = description;
        this.image = image;
    }

    

    public Produit(String nomProduit, int quantite, float prix, int id_categorie, String description, String image) {
        this.nomProduit = nomProduit;
        this.quantite = quantite;
        this.prix = prix;
        this.id_categorie = id_categorie;
        this.description = description;
        this.image = image;
    }

    

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Produit{" + "idProduit=" + idProduit + ", nomProduit=" + nomProduit + ", quantite=" + quantite + ", prix=" + prix + ", id_categorie=" + id_categorie + ", description=" + description + ", image=" + image + '}'+"\n";
    }

    
    

}
