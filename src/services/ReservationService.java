/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author mariem
 */

import Entites.Reservation;
import Entites.Salle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DBConnection;
import java.sql.PreparedStatement;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservationService implements I_chariot<Reservation>{
       Connection con = DBConnection.getInstance().getCon();
 
    @Override
    public void ajouteer(Reservation C) {
       String query = "INSERT INTO `Reservation`( `date`, `id_client`,`id_salle` , `nbrP`) VALUES ('"+ C.getDate()+"','"+1+"','"+1+"','"+C.getNbrP()+"')";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("INFO: New Reservation added.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }}
    @Override
    public void modifer(Reservation C) {
         String query = "UPDATE Reservation set  `date`='"+C.getDate()+"' ,`id_salle`='"+1+"', `id_client`='"+1+"',`nbrP`='"+C.getNbrP()+"' where idReservation ='"+C.getIdReservation()+"'";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("INFO: Reservation Updated.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
    }

  /*  @Override
    public  List<Reservation> afficher() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT salle.nom, salle.prixSalle,salle.capacite,salle.image,reservation.date,reservation.nbrP FROM Reservation inner join Salle on Reservation.id_salle=Salle.idSalle where reservation.id_client="+1;

        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next())
            {
                reservations.add(new Reservation(
               
                       result.getString("salle.nom"),
                        result.getFloat("salle.prixSalle"),
                        result.getInt("salle.capacite"),
                        result.getString("salle.image"),
                        result.getDate("reservation.date"),                     
                        result.getInt("reservation.nbrP")
                                
                ));       
            }       
            
            //System.out.println("test");
           System.out.println(reservations);
           // Debugger.log("INFO: Successfully fetched all users.");
               return reservations;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null; }

    @Override
    public void modifer(Reservation C) {
         String query = "UPDATE Reservation set  `date`='"+C.getDate()+"' ,`id_salle`='"+C.getId_salle()+"', `id_client`='"+C.getId_client()+"',`nbrP`='"+C.getNbrP()+"' where idReservation ='"+C.getIdReservation()+"'";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("INFO: Reservation Updated.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } }

   
    
    public List<Reservation> rechercher(String datee) {
       List<Reservation> Reservationss = afficher();
        Reservationss.stream().filter(x -> x.getDate().contains(datee)).forEach(System.out::println);
         
        return Reservationss;
         }
    
    public List<Reservation> triId() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM Reservation";

        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()) {
                reservations.add(new Reservation(
                        result.getInt("idReservation"),
                        result.getDate("date"),
                        result.getInt("id_client"),
                        result.getInt("id_salle"),
                        result.getInt("nbrP")
                        
                ));
            }
            //System.out.println("afficher successfully");
            //System.out.println(salles);
           // Debugger.log("INFO: Successfully fetched all users.");
            reservations.stream().sorted(Comparator.comparingInt(Reservation::getIdReservation).reversed()).forEach(System.out::println);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null; }
     */
    

   

     public void supprimerId(int id) {
        try {
            String req = "DELETE from Reservation where idReservation =?";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   public  List<Reservation> afficherres() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT idReservation,date,nbrP FROM Reservation where id_client="+1;

        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next())
            {
                reservations.add(new Reservation(
               
                        result.getInt("idReservation"),
                        result.getDate("date"),                     
                        result.getInt("nbrP")
                                
                ));       
            }       
            
            //System.out.println("test");
           System.out.println(reservations);
           // Debugger.log("INFO: Successfully fetched all users.");
               return reservations;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null; }

    @Override
    public List<Reservation> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Reservation C) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
   
}  



