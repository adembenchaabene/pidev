/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author chaab
 */
public class Article {
    private int idArticle ;
    private String contenu;
    private int auteur;
    private String titre;
    private Etat Etat;

    public Article() {
    }

    public Article(int idArticle, String contenu, int auteur, String titre,Etat Etat) {
        this.idArticle = idArticle;
        this.contenu = contenu;
        this.auteur = auteur;
        this.titre = titre;
         this.Etat=Etat;
    }

    public Article(String contenu, int auteur, String titre,Etat Etat) {
        this.idArticle=titre.length()*contenu.length();
        this.contenu = contenu;
        this.auteur = auteur;
        this.titre = titre;
        this.Etat=Etat;
    }

    public Article(String contenu, String titre,Etat Etat) {
        this.idArticle=titre.length()*contenu.length();
        this.contenu = contenu;
        this.titre = titre;
         this.Etat=Etat;
         
    }

    

    public void setEtat(Etat Etat) {
        this.Etat = Etat;
    }

    public Etat getEtat() {
        return Etat;
    }

   

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getAuteur() {
        return auteur;
    }

    public void setAuteur(int auteur) {
        this.auteur = auteur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public String toString() {
        return "Article{" + "idArticle=" + idArticle + ", contenu=" + contenu + ", auteur=" + auteur + ", titre=" + titre +", Etat=" + Etat + '}' + "\n";
    }
    

    
}
