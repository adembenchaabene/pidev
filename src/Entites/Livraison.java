/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class Livraison {
    private int idLivraison;
    private String type;
    private int idAdresse;
    private int idproduit;
    private int idlivreur;
    private int etat;
    private int iduser;

    public Livraison(String type, int idAdresse, int idproduit, int idlivreur, int etat, int iduser) {
        this.type = type;
        this.idAdresse = idAdresse;
        this.idproduit = idproduit;
        this.idlivreur = idlivreur;
        this.etat = etat;
        this.iduser = iduser;
    }

    public Livraison() {
    }

    public Livraison(int idLivraison, String type, int idAdresse, int idproduit, int idlivreur, int etat) {
        this.idLivraison = idLivraison;
        this.type = type;
        this.idAdresse = idAdresse;
        this.idproduit = idproduit;
        this.idlivreur = idlivreur;
        this.etat= etat;
    }

    public Livraison(String type, int idAdresse, int idproduit, int etat) {
        this.type = type;
        this.idAdresse = idAdresse;
        this.idproduit = idproduit;
        this.etat = etat;
    }

   
  public Livraison( String type, int idAdresse, int idproduit, int idlivreur,int etat) {
        this.type = type;
        this.idAdresse = idAdresse;
        this.idproduit = idproduit;
        this.idlivreur = idlivreur;
        this.etat= etat;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
  

  
    public int getIdLivraison() {
        return idLivraison;
    }

    public String getType() {
        return type;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public int getIdlivreur() {
        return idlivreur;
    }

    public int getIdAdresse() {
        return idAdresse;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

   
    
    

    public void setIdLivraison(int idLivraison) {
        this.idLivraison = idLivraison;
    }

    public void setType(String type) {
        this.type = type;
    }


    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public void setIdlivreur(int idlivreur) {
        this.idlivreur = idlivreur;
    }

    public void setIdAdresse(int idAdresse) {
        this.idAdresse = idAdresse;
    }

    @Override
    public String toString() {
        if (etat == 0)
        {
        return "idLivraison=" + idLivraison + ", type=" + type + ", idAdresse=" + idAdresse + ", idproduit=" + idproduit + ", idlivreur=" + idlivreur + ", etat= valide"+ "\n";
        }
        else
        {
            return "idLivraison=" + idLivraison + ", type=" + type + ", idAdresse=" + idAdresse + ", idproduit=" + idproduit + ", idlivreur=" + idlivreur + ", etat= en cours" + "\n";
        }
    }

  

       
    
    
}
