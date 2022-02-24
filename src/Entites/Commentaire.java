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
public class Commentaire {
    private int idCommentaire;
    private int auteur_c;
    private int id_article;
    private String contenu_c;

    public Commentaire() {
    }

    public Commentaire(int idCommentaire, int auteur_c, int id_article, String contenu_c) {
        this.idCommentaire = idCommentaire;
        this.auteur_c = auteur_c;
        this.id_article = id_article;
        this.contenu_c = contenu_c;
    }

    public Commentaire(int auteur_c, int id_article, String contenu_c) {
        this.auteur_c = auteur_c;
        this.id_article = id_article;
        this.contenu_c = contenu_c;
    }

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public int getAuteur_c() {
        return auteur_c;
    }

    public void setAuteur_c(int auteur_c) {
        this.auteur_c = auteur_c;
    }

    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public String getContenu_c() {
        return contenu_c;
    }

    public void setContenu_c(String contenu_c) {
        this.contenu_c = contenu_c;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "idCommentaire=" + idCommentaire + ", auteur_c=" + auteur_c + ", id_article=" + id_article + ", contenu_c=" + contenu_c + "}\n" ;
    }
    
    
    
}
