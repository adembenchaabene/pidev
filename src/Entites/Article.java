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
    private String image;
    private int nbrreact;

    public Article(String contenu, int auteur, String titre, Etat Etat, String image, int nbrreact) {
        this.contenu = contenu;
        this.auteur = auteur;
        this.titre = titre;
        this.Etat = Etat;
        this.image = image;
        this.nbrreact = nbrreact;
    }

    public Article(int idArticle, String contenu, int auteur, String titre, Etat Etat, String image, int nbrreact) {
        this.idArticle = idArticle;
        this.contenu = contenu;
        this.auteur = auteur;
        this.titre = titre;
        this.Etat = Etat;
        this.image = image;
        this.nbrreact = nbrreact;
    }

    public int getNbrreact() {
        return nbrreact;
    }

    public void setNbrreact(int nbrreact) {
        this.nbrreact = nbrreact;
    }
    

    public Article() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Article(int idArticle, String contenu, int auteur, String titre,Etat Etat,String image) {
        this.idArticle = idArticle;
        this.contenu = contenu;
        this.auteur = auteur;
        this.titre = titre;
        this.Etat=Etat;
        this.image=image; 
         
    }

    public Article(String contenu, int auteur, String titre,Etat Etat,String image ) {
        this.idArticle=titre.length()*contenu.length();
        this.contenu = contenu;
        this.auteur = auteur;
        this.titre = titre;
        this.Etat=Etat;
        this.image=image;
    }

    public Article(String contenu, String titre,Etat Etat,String image) {
        this.idArticle=titre.length()*contenu.length();
        this.contenu = contenu;
        this.titre = titre;
        this.Etat=Etat;
        this.image=image; 
         
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
        return "Article{" + "idArticle=" + idArticle + ", contenu=" + contenu + ", auteur=" + auteur + ", titre=" + titre + ", Etat=" + Etat + ", image=" + image + '}'+"\n";
    }
   

    
}
