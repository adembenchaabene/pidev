/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ichariot;

import Ichariot_service.ReservationService;
import Ichariot_service.SalleService;
import Ichariot_model.Reservation;
import Ichariot_model.Salle;

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
        
        Salle a = new Salle("salle de confff",1.2f,"img3",30);
        Salle b = new Salle(27,"cine",1.2f,"img2",20);
        SalleService ss = new SalleService();
        
        //ss.ajouteer(a);
        //ss.supprimer(b);
       
        //b.setImage("img2");
        //ss.modifer(b);
        //ss.getSalles();
        //ss.rechercher("salle3");
        //ss.triId();
        
        
        //Reservation r = new Reservation("2022-04-01",1,2,20);
        ReservationService rr= new ReservationService();
        
        
        //rr.triId();
        //rr.rechercher("03");
       // rr.ajouteer(r);
         //rr.deleteReservation(r);
        //r.setDate("2022-20-12");
        //rr.updateReservation(r);
        //rr.getReservations();
        //System.out.println(rr.afficher());
    }
    
}
