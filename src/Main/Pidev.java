/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Entites.Adresse;
import Entites.Livraison;
import Entites.Livreur;
import services.ServiceAdresse;
import services.ServiceLivraison;
import services.ServiceLivreur;

/**
 *
 * @author DELL
 */
public class Pidev {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       ServiceLivreur sp = new ServiceLivreur();
       /* Livreur p = new Livreur("safachaari", 92623308);
        Livreur p1 = new Livreur(3,"safachaari2", 11234);
        Livreur p2 = new Livreur("oumayma", 555555);
        Livreur p3 = new Livreur(5,"oumaymatest", 555555);
        //sp.ajout(p2);
        //sp.modifier(p3);
        //sp.supprimer(5);
        System.out.println(sp.afficher());*/
       //sp.recherche("safa");
       // sp.tri();
        
      // ServiceLivraison sp = new ServiceLivraison();
       /* Livraison p = new Livraison("rapideee",2,1,3,1);
        Livraison p1 = new Livraison(1,"normal",1,1,4,1);
       Livraison p2 = new Livraison("rapideeetest",3,1,3,1);
        sp.ajout(p);
        sp.modifier(p1);
        sp.supprimer(4);
        System.out.println(sp.afficher());*/
       // sp.recherche("l");
      //  sp.tri();*/
        
       /*ServiceAdresse sp = new ServiceAdresse();
         Adresse p = new Adresse("sfax","rue5",5);
        Adresse p1 = new Adresse("nebel","ruehbibbourguiba",7);
        Adresse p2 = new Adresse(3,"sfax","rueeee",8);
        Adresse p2 = new Adresse("tunis","ruehbibthameur",8);
        sp.modifier(p2);
        sp.ajout(p1);
        sp.supprimer(2);
        System.out.println(sp.afficher());
        sp.recherche("sf");
        sp.tri();*/
        
    }
    
}
