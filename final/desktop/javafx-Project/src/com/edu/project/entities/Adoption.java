/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.entities;

/**
 *
 * @author Ennou
 */
public class Adoption {
    int id;
    String nom,proprietaire,age,description,sexe,race;
    int c_id;

    public Adoption(int id, String nom, String proprietaire, String age, String description, String sexe, String race) {
        this.id = id;
        this.nom = nom;
        this.proprietaire = proprietaire;
        this.age = age;
        this.description = description;
        this.sexe = sexe;
        this.race = race;
    }

    public Adoption() {
    }

    public Adoption(String nom, String proprietaire, String age, String description, String sexe, String race, int c_id) {
        this.nom = nom;
        this.proprietaire = proprietaire;
        this.age = age;
        this.description = description;
        this.sexe = sexe;
        this.race = race;
        this.c_id = c_id;
    }

    public Adoption(int id, String nom, String proprietaire, String age, String description, String sexe, String race, int c_id) {
        this.id = id;
        this.c_id = c_id;
        this.nom = nom;
        this.proprietaire = proprietaire;
        this.age = age;
        this.description = description;
        this.sexe = sexe;
        this.race = race;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public Adoption(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Adoption{" + "id=" + id + ", nom=" + nom + ", proprietaire=" + proprietaire + ", age=" + age + ", description=" + description + ", sexe=" + sexe + ", race=" + race + ", c_id=" + c_id + '}';
    }

    public Adoption(String nom, String proprietaire, String age, String description, String sexe, String race) {
        this.nom = nom;
        this.proprietaire = proprietaire;
        this.age = age;
        this.description = description;
        this.sexe = sexe;
        this.race = race;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }
    
    
}