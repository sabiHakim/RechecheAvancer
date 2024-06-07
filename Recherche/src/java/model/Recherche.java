/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static Mano.Recherche.getGategorieByMotCle;
import java.sql.Connection;
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Vector;

/**
 *
 * @author MOREL BEN Taboaly
 */
public class Recherche {
    String recherche;
    Mot_cle mot_cle;
    Categorie categorie;
    String nom="";
    
    public String getRecherche() {
        return recherche;
    }

    public void setRecherche(String recherche) {
        this.recherche = recherche;
    }

    public Mot_cle getMot_cle() {
        return mot_cle;
    }

    public void setMot_cle(Mot_cle mot_cle) {
        this.mot_cle = mot_cle;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    
    public Recherche(){
    this.categorie=new Categorie();
    this.mot_cle = new Mot_cle();
    }
    
    public static Vector<String> diviser(String recherche){
        int length = 0;
        String [] recherches =  recherche.split(" ");
        Vector<String> res = new Vector<String>();
        for(int i = 0 ;i<recherches.length;i++){
            res.add(recherches[i]);
        }
        return res;
    }
    
    
    public Vector<String>  findCategorieInRecherche(Connection c,Vector<String> recherche) throws SQLException{
        StringBuilder categorie = new StringBuilder();
        for(int i = 0 ; i<recherche.size();i++){
            
           if(Categorie.isMotInGategorie(c, recherche.get(i))){
               categorie.append(recherche.get(i)).append(" ");
//               System.err.println(categorie.toString());
               recherche.remove(recherche.get(i));
           } 
        }
        if(categorie.length()>0){
            categorie.deleteCharAt(categorie.length()-1);
        }
       
        this.categorie = Categorie.getGategorieByMotCle(c, categorie.toString());
        return recherche;
    } 
    
    
    public static String transformVectorToString(Vector<String> mots){
 
        StringBuilder valiny  = new StringBuilder();
           for(String mot : mots){
               valiny.append(mot).append(" ");
           }
        if(valiny.length()>0){
            valiny.deleteCharAt(valiny.length()-1);
        }
        return valiny.toString();
    }
   
    
    public static double pourcentageComparaison(String chaine1,String chaine2){
        String [] mots1 = chaine1.split(" ");
        String [] mots2 = chaine2.split(" ");
        Arrays.sort(mots1);
        Arrays.sort(mots2);
        
        if(Arrays.equals(mots1, mots2)){
           return 100;
        }else {
            int longueurMax = Math.max(mots1.length, mots2.length);
            int correspondance = 0;
            
            for(int i = 0 ; i<longueurMax;i++){
                if(i<mots1.length && i <mots2.length && mots1[i].equals(mots2[i])){
                    correspondance++;
                }
            }
            double pourcentage  = (double) correspondance / longueurMax *100;
            return  pourcentage;
        }
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
    public Vector<Produit> recherche(Connection c,Vector<String> mots,Vector<String> mot) throws SQLException{
         int top = this.findTop(mots);
     
         for(String m: mots){
             System.err.println("eee "+m);
         }
        Vector<Categorie> lc = getAllCategorieIn(c,mots);
 
        String sql = "select * from "+this.getMot_cle().getView();
        System.err.println(sql);
        if (lc.size()>0) {
            String cond = " where cat_nom like'%"+lc.get(0).getNom()+"%'";
            for (int i = 1; i < lc.size(); i++) {
                Categorie elementAt = lc.elementAt(i);
                cond = cond +" or cat_nom like'%"+elementAt.getNom()+"%'";
            }
            sql = sql + cond;
        }
        if(top!=0){
            sql=sql+" limit "+top;
        }

        System.out.println(sql);
        Vector<Produit> resultat = new Vector<Produit>();
        
        Statement stat = c.createStatement();
        ResultSet res = stat.executeQuery(sql);
        while(res.next()){
           Produit prod = new Produit();
           prod.setProduit_id(res.getInt("produit_id"));
           prod.setNom(res.getString("nom"));
           Categorie cat = new Categorie(res.getInt("categorie"),res.getString("cat_nom"));
           prod.setCategorie(cat);
           prod.setQualite(res.getInt("qualite"));
           prod.setPrix(res.getDouble("prix"));
           prod.setRapport(res.getDouble("rapport"));
           resultat.add(prod);
        }
        stat.close();
        res.close();
        return resultat;
    }
    
    public int findTop(Vector<String> mot){
        for(int i=0;i<mot.size();i++){
            if(mot.get(i).equals("top")){
                return Integer.parseInt(mot.get(i+1));
            }
        
        }
        return 0;
    }
     public static Vector<String> newRecherche(Vector<String> mot){
         Vector<String> e = new Vector<>();
        for(int i=0;i<mot.size();i++){
            if(mot.get(i).equals("top")){
                mot.remove(i+1);
                mot.remove(i);
            }
        
        }
        e= mot;
        return e;
    }
    
    
    
    
}
