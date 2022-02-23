/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author mariem
 */
public class Reservation {
    private int idReservation;
    private String date;
    private int id_client;
    private int id_salle;

    public Reservation(String date, int id_client, int id_salle) {
        this.date = date;
        this.id_client = id_client;
        this.id_salle = id_salle;
    }

    public Reservation() {
    }

    public Reservation(int idReservation, String date, int id_client, int id_salle) {
        this.idReservation = idReservation;
        this.date = date;
        this.id_client = id_client;
        this.id_salle = id_salle;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_salle() {
        return id_salle;
    }

    public void setId_salle(int id_salle) {
        this.id_salle = id_salle;
    }

    @Override
    public String toString() {
        return "Reservation{" + "idReservation=" + idReservation + ", date=" + date + ", id_client=" + id_client + ", id_salle=" + id_salle + '}' +"\n";
    }
    
    
}
