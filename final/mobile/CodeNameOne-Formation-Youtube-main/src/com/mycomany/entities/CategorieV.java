/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author Mynet
 */
public class CategorieV {
    private int id;
    private String type;
  
 public CategorieV(){
     
 }
 public CategorieV(int id,String type){
     this.id=id;
     this.type=type;
 }
 public CategorieV(String type){
     this.type=type;
 }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }
}
