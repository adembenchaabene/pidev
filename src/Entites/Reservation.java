/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.sql.Date;

/**
 *
 * @author mariem
 */
public class Reservation {
    private int idReservation;
    private Date date;
    private int id_client;
    private int id_salle;
    private int nbrP;

    public Reservation(Date date, int id_client, int id_salle,int nbrP) {
        this.date = date;
        this.id_client = id_client;
        this.id_salle = id_salle;
        this.nbrP = nbrP;
    }

    public Reservation() {
    }

    public Reservation(int idReservation, Date date, int id_client, int id_salle,int nbrP) {
        this.idReservation = idReservation;
        this.date = date;
        this.id_client = id_client;
        this.id_salle = id_salle;
        this.nbrP = nbrP;
    }

    public Reservation(Date date, int nbrP) {
        this.date = date;
        this.nbrP = nbrP;
    }

    public Reservation(int idReservation, Date date, int nbrP) {
        this.idReservation = idReservation;
        this.date = date;
        this.nbrP = nbrP;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }


    public int getNbrP() {
        return nbrP;
    }

    public void setNbrP(int nbrP) {
        this.nbrP = nbrP;
    }

   

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
        return "Reservation  date=" + date + ", id_client=" + id_client + ", id_salle=" + id_salle + ", nbrP=" + nbrP + '}';
    }

   
    
    
}
