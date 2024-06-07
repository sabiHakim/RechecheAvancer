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
public class Mot_cle {
    String nom;
    String view ;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
    
    public Mot_cle getMotCle(Connection c,String mot_cle) throws SQLException{
        Vector<Mot_cle> resu = getAllMotCle(c);
        double [] pourc = new double[resu.size()];
//        String [] mot_cles = (String[]) resu.toArray();
        for(int i= 0 ;i<resu.size();i++){
            pourc[i] =Recherche.pourcentageComparaison(mot_cle,resu.get(i).getNom());
        }
        double max =pourc[0];
        int idmax  = 0;
        for(int i= 0 ; i<pourc.length;i++){
            if(max<pourc[i]){
                max= pourc[i];
                idmax = i;
            }
        }
        
        return resu.get(idmax);
    
    }
    
    public  Vector<Mot_cle> getAllMotCle(Connection c) throws SQLException{
        Vector<Mot_cle> resu = new Vector<>();
        Statement stat = c.createStatement();
        ResultSet res = stat.executeQuery("select * from mot_cle");
        while(res.next()){
            Mot_cle mot = new Mot_cle();
            mot.setNom(res.getString("nom"));
            mot.setView(res.getString("view"));
            resu.add(mot);
        }
       
        return resu;
    
    }
    
    
}
