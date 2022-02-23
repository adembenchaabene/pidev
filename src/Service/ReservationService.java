/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

/**
 *
 * @author mariem
 */

import Entites.Reservation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Utils.DBConnection;
import java.util.Comparator;

public class ReservationService implements I_chariot<Reservation>{
       Connection con = DBConnection.getInstance().getCon();
 
    @Override
    public void ajouteer(Reservation C) {
       String query = "INSERT INTO `Reservation`( `date`, `id_client`,`id_salle`) VALUES ('"+C.getDate()+"','"+C.getId_client()+"','"+C.getId_salle()+"')";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("INFO: New Reservation added.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }}

    @Override
    public List<Reservation> afficher() {
        List<Reservation> Reservations = new ArrayList<>();
        String query = "SELECT * FROM Reservation";

        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()) {
                Reservations.add(new Reservation(
                        result.getInt("idReservation"),
                        result.getString("date"),
                        result.getInt("id_client"),
                         result.getInt("id_salle")
                ));
            }
            //System.out.println("test");
            //System.out.println(Reservations);
           // Debugger.log("INFO: Successfully fetched all users.");
return(Reservations);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null; }

    @Override
    public void modifer(Reservation C) {
         String query = "UPDATE Reservation set  `date`='"+C.getDate()+"' ,`id_salle`='"+C.getId_salle()+"', `id_client`='"+C.getId_client()+"' where idReservation ='"+C.getIdReservation()+"'";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("INFO: Reservation Updated.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } }

    @Override
    public void supprimer(Reservation C) {
       String query = "DELETE from Reservation where idReservation ='"+C.getIdReservation()+"'";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("INFO: Reservation Deleted.");
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
                        result.getString("date"),
                        result.getInt("id_client"),
                         result.getInt("id_salle")
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
}  



