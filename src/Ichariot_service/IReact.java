/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ichariot_service;

/**
 *
 * @author chaab
 */
public interface IReact<T> {
    
  public boolean putLikeToPost( int idusers ,int idarticles);
  public boolean putUnLikeToPost(int idusers ,int idarticles);
  public long numberOfLikesByPost(int id); 
}
