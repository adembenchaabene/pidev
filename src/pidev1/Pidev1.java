/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev1;

import Service.ReservationService;
import Service.SalleService;
import Entites.Reservation;
import Entites.Salle;

/**
 *
 * @author mariem
 */
public class Pidev1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Salle a = new Salle("cineeeee",1.2f,3);
        Salle b = new Salle(26,"salle4",1.2f,3);
        SalleService ss = new SalleService();
        
       //ss.ajouteer(a);
        //ss.supprimer(b);
        //ss.getSalles();
        //a.setNom("mariem");
        //ss.updateSalle(a);
        //ss.getSalles();
        ss.rechercher("salle3");
        //ss.triId();
        
        
        Reservation r = new Reservation("2022-04-01",1,2);
        ReservationService rr= new ReservationService();
        
        
        //rr.triId();
        //rr.rechercher("03");
        //rr.addReservation(r);
         //rr.deleteReservation(r);
        //r.setDate("2022-20-12");
        //rr.updateReservation(r);
        //rr.getReservations();
        //System.out.println("");
    }
    
}
