/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.entities;

/**
 *
 * @author pc
 */
public class user {
    private Integer id;
    private String email;
    private String userName;
    private String first_name;
    private String last_name;
    private String roles;
    private String password;
    private Boolean Blocked;
    
    public user() {
    }

    public user(Integer id, String email, String userName, String first_name, String last_name, String roles, String password , Boolean Blocked) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.first_name = first_name;
        this.last_name = last_name;
        this.roles = roles;
        this.password = password;
        this.Blocked= Blocked;
    }

    public user(int aInt, String string, String string0, String string1, boolean aBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setBlocked(Boolean Blocked) {
        this.Blocked = Blocked;
    }

    public user(Integer id,String email, String userName, String first_name, String last_name, String roles, String password) {
         this.id = id;
        this.email = email;
        this.userName = userName;
        this.first_name = first_name;
        this.last_name = last_name;
        this.roles = roles;
        this.password = password;
    }

    public Boolean getBlocked() {
        return Blocked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "user{" + "id=" + id + ", email=" + email + ", userName=" + userName + ", first_name=" + first_name + ", last_name=" + last_name + ", roles=" + roles + ", password=" + password + '}';
    }

 
}
