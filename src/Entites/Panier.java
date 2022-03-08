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
public class Panier {
    private int idPanier;
    private Produit produit;

    public Panier() {
    }
    public Panier(Produit produit) {
        this.produit = produit;
    }

    public Panier(int idPanier, Produit produit) {
        this.idPanier = idPanier;
        this.produit = produit;
    }

    public int getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(int idPanier) {
        this.idPanier = idPanier;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public String toString() {
        return "Panier{" + "idPanier=" + idPanier + ", produit=" + produit + '}';
    }
    
    
}
