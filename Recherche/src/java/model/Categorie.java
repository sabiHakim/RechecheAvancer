/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author MOREL BEN Taboaly
 */
public class Categorie {
    int id ; 
    String nom;

    public Categorie(){}
    public Categorie(int id, String nom) {
        this.id=id;
        this.nom = nom;
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
    
    public Vector<Categorie>  getAllGategorieByMotCle(Connection c,String mot_cle) throws SQLException{
        Vector<Categorie> resultat = new Vector<Categorie>();
        String sql = "select * from categorie where nom like '%"+mot_cle+"%'";
        Statement stat = c.createStatement();
        ResultSet res = stat.executeQuery(sql);
        while(res.next()){
            resultat.add(new Categorie(res.getInt("categorie_id"),res.getString("nom")));
        }
        stat.close();
        res.close();
        return resultat;
    }
    
    public static Categorie  getGategorieByMotCle(Connection c,String mot_cle) throws SQLException{
        Categorie resultat = new Categorie();
        String sql = "select * from categorie where nom like '%"+mot_cle+"%'";
//        System.out.println(sql);
        Statement stat = c.createStatement();
        ResultSet res = stat.executeQuery(sql);
        while(res.next()){
            return new Categorie(res.getInt("categorie_id"),res.getString("nom"));
        }
        stat.close();
        res.close();
        return resultat;
    }
    
    public static boolean isMotInGategorie(Connection c,String mot_cle) throws SQLException{
        String sql = "select * from categorie where nom like '%"+mot_cle+"%'";
        Statement stat = c.createStatement();
        ResultSet res = stat.executeQuery(sql);
        while(res.next()){
           return true;
        }
        stat.close();
        res.close();
        return false;
    }
    
}
