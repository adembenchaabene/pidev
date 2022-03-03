/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ichariot_model;

import java.util.Date;

/**
 *
 * @author chaab
 */
public class ReactArticle {
    private int idlike;
    private int idarticles;
    private int idusers;
    private Date datecrea;

    public int getIdlike() {
        return idlike;
    }

    public void setIdlike(int idlike) {
        this.idlike = idlike;
    }

    public int getIdarticles() {
        return idarticles;
    }

    public void setIdarticles(int idarticles) {
        this.idarticles = idarticles;
    }

    public int getIdusers() {
        return idusers;
    }

    public void setIdusers(int idusers) {
        this.idusers = idusers;
    }

    public Date getDatecrea() {
        return datecrea;
    }

    public void setDatecrea(Date datecrea) {
        this.datecrea = datecrea;
    }

    public ReactArticle(int idlike, int idarticles, int idusers, Date datecrea) {
        this.idlike = idlike;
        this.idarticles = idarticles;
        this.idusers = idusers;
        this.datecrea = datecrea;
    }

    public ReactArticle() {
    }

    public ReactArticle(int idarticles, int idusers, Date datecrea) {
        this.idarticles = idarticles;
        this.idusers = idusers;
        this.datecrea = datecrea;
    }

    @Override
    public String toString() {
        return "ReactArticle{" + "idlike=" + idlike + ", idarticles=" + idarticles + ", idusers=" + idusers + ", datecrea=" + datecrea + '}'+"\n";
    }
    
    
    
}
