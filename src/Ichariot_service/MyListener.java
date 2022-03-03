/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ichariot_service;

import Ichariot_model.Article;
import Ichariot_model.Commentaire;

/**
 *
 * @author chaab
 */
public interface MyListener {
    public void onClickListener(Article article);
    public void onClickListener2(Commentaire commentaire);
    
}
