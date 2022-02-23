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

import Entites.Salle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Utils.DBConnection;
import java.util.Comparator;

public class SalleService implements I_chariot<Salle>{
       Connection con = DBConnection.getInstance().getCon();
 
    @Override
    public void ajouteer(Salle C) {
        String query = "INSERT INTO `salle`( `nom`, `prixSalle`,`id_admin`) VALUES ('"+C.getNom()+"','"+C.getPrix()+"','"+C.getIdAdmin()+"')";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("INFO: New Salle added.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } }

    @Override
    public List<Salle> afficher() {
        List<Salle> salles = new ArrayList<>();
        String query = "SELECT * FROM Salle";

        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()) {
                salles.add(new Salle(
                        result.getInt("idSalle"),
                        result.getString("nom"),
                        result.getFloat("prixSalle"),
                         result.getInt("id_admin")
                ));
            }
            System.out.println("afficher successfully");
            System.out.println(salles);
           // Debugger.log("INFO: Successfully fetched all users.");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null; }

    @Override
    public void modifer(Salle C) {
        String query = "UPDATE salle set  `prixSalle`='"+C.getPrix()+"', `nom`='"+C.getNom()+"', `id_admin`='"+C.getIdAdmin()+"' where idSalle ='"+C.getIdSalle()+"'";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("INFO: Salle Updated.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }  }

    @Override
    public void supprimer(Salle C) {
        String query = "DELETE from Salle where idSalle ='"+C.getIdSalle()+"'";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("INFO: Salle Deleted.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }}
    
    public List<Salle> rechercher(String name) {
        List<Salle> salles = new ArrayList<>();
        String query = "SELECT * FROM Salle";

        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()) {
                salles.add(new Salle(
                        result.getInt("idSalle"),
                        result.getString("nom"),
                        result.getFloat("prixSalle"),
                         result.getInt("id_admin")
                ));
            }
            //System.out.println("afficher successfully");
            //System.out.println(salles);
           // Debugger.log("INFO: Successfully fetched all users.");
                salles.stream()
                .filter(x-> x.getNom().contains(name))
                .forEach(x->System.out.println(x));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null; }

    
    public List<Salle> triId() {
        List<Salle> salles = new ArrayList<>();
        String query = "SELECT * FROM Salle";

        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()) {
                salles.add(new Salle(
                        result.getInt("idSalle"),
                        result.getString("nom"),
                        result.getFloat("prixSalle"),
                         result.getInt("id_admin")
                ));
            }
            //System.out.println("afficher successfully");
            //System.out.println(salles);
           // Debugger.log("INFO: Successfully fetched all users.");
            salles.stream().sorted(Comparator.comparingInt(Salle::getIdSalle).reversed()).forEach(System.out::println);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null; }
}  



