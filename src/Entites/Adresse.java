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
public class Adresse {
    private int id_adresse;
    private String ville;
    private String rue;
    private int numMaison;
    private int iduser;
    public Adresse() {
    }

     public Adresse( String ville, String rue, int numMaison, int iduser) {
        this.ville = ville;
        this.rue = rue;
        this.numMaison = numMaison;
        this.iduser=iduser;
    }
     
    public Adresse(int id_adresse, String ville, String rue, int numMaison,int iduser) {
        this.id_adresse = id_adresse;
        this.ville = ville;
        this.rue = rue;
        this.numMaison = numMaison;
        this.iduser=iduser;
    }

    public Adresse(int id_adresse, String ville, String rue, int numMaison) {
       this.id_adresse = id_adresse;
        this.ville = ville;
        this.rue = rue;
        this.numMaison = numMaison;
    }

    public int getId_adresse() {
        return id_adresse;
    }

    public String getVille() {
        return ville;
    }

    public String getRue() {
        return rue;
    }

    public int getNumMaison() {
        return numMaison;
    }

    public int getIduser() {
        return iduser;
    }
    
    

    public void setId_adresse(int id_adresse) {
        this.id_adresse = id_adresse;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }
    
    public void setNumMaison(int numMaison) {
        this.numMaison = numMaison;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    @Override
    public String toString() {
        return "Adresse{" + "id_adresse=" + id_adresse + ", ville=" + ville + ", rue=" + rue + ", numMaison=" + numMaison + ", iduser=" + iduser + '}' + "\n";
    }

   
    
    
}
