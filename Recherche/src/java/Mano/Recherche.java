/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mano;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import model.Categorie;

/**
 *
 * @author kodar
 */
public class Recherche {
    
    public static Vector<String> diviser(String recherche){
        int length = 0;
        String [] recherches =  recherche.split(" ");
        Vector<String> res = new Vector<String>();
        for(int i = 0 ;i<recherches.length;i++){
            res.add(recherches[i]);
        }
        return res;
    }
    
    public Vector<Categorie> getAllCategorieIn(Connection c,Vector<String> mots) throws SQLException{
        Vector<Categorie> lc = new Vector<Categorie>();
        for (int i = 0; i < mots.size(); i++) {
            if(getGategorieByMotCle(c, mots.get(i)).getNom()!=""){
                lc.add(getGategorieByMotCle(c, mots.get(i)));         
            }
        }
        return lc;
    }
    
    
        
    public void recherche(Connection c,Vector<String> mots) throws SQLException{
        Vector<Categorie> lc = getAllCategorieIn(c, mots);
        String sql = "select * from vproduit ";
        if (lc.size()>0) {
            String cond = "where cat_nom like'%"+lc.get(0).getNom()+"%'";
            for (int i = 1; i < lc.size(); i++) {
                Categorie elementAt = lc.elementAt(i);
                cond = cond +" or cat_nom like'%"+elementAt.getNom()+"%'";
            }
            
            sql = sql + cond;
        }
        System.out.println(sql);
        
//        Vector<model.Produit> resultat = new Vector<model.Produit>();
//        
//        Statement stat = c.createStatement();
//        ResultSet res = stat.executeQuery(sql);
//        while(res.next()){
//           model.Produit prod = new model.Produit();
//           prod.setProduit_id(res.getInt("produit_id"));
//           prod.setNom(res.getString("nom"));
//           Categorie cat = new Categorie(res.getInt("categorie"),res.getString("cat_nom"));
//           prod.setCategorie(cat);
//           prod.setQualite(res.getInt("qualite"));
//           prod.setPrix(res.getDouble("prix"));
//           resultat.add(prod);
//        }
//        stat.close();
//        res.close();
//        return resultat;
    }
    
    
    public static Categorie  getGategorieByMotCle(Connection c,String mot_cle) throws SQLException{
        Categorie resultat = new Categorie();
        resultat.setNom("");
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
}
