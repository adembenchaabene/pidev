/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author chaab
 */
public interface I_chariot <T>{
    
    public void ajouteer (T C);
    public List <T> afficher();
    public void modifer(T C);
    public void supprimer (T C);

}
