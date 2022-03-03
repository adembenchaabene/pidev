/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev1;

import Service.ProduitService;
import Service.CategorieService;
import Entites.Produit;
import Entites.Categorie;

/**
 *
 * @author compu serv
 */
public class Pidev1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       // Produit p1=new Produit(1,"nouveau",10,20.2f,5);
       // Produit p2=new Produit(2,"Maryem",10,20.2f,5);
        //Produit p3=new Produit(3,"Safa",10,20.2f,5);
        //Produit p4=new Produit(4,"Adem",10,20.2f,5);
        
        //Produit p2=new Produit(2,"ghofrane",12,20.2f,3);
        ProduitService ps=new ProduitService();
        /*ps.addProduit(p2);
       p1.setNomProduit("Ghofraneso");
       ps.updateProduit(p1);*/
       // ps.getProduits();
        //ps.deleteProduit(p2);
     
      //Categorie c1=new Categorie(2,"pc portables","Nouvelle generation");
       //Categorie c2=new Categorie(7,"pc","descrip");
       // c3=new Categorie(4,"desktop","aucune");
      //Categorie c2=new Categorie(3,"Accessoires","Souris,cables..");
      //Categorie c3=new Categorie(4,"TV","samsung");
      CategorieService cs=new CategorieService();
     // cs.addCategorie(c1);
      
     //c1.setNomCateg("Souris");
     //cs.updateCategorie(c1);
      //cs.getCategories();
     //cs.deleteCategorie(c3);
     //cs.getCategories();
     //ps.TriID();
     //ps.Rechercher("adem");
     //ps.Rechercher("G");
       // System.out.println(cs.afficher());
      //cs.Rechercher("z");
      //cs.TriID();
     // cs.ajouteer(c2);
      //cs.ajouteer(c3);
      //cs.supprimer(c2);
      //cs.TriID();
      //cs.Rechercher("pc");
      
    }
    
}
