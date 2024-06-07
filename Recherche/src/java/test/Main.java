/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Connection;
import java.util.Vector;
import model.Mot_cle;
import model.Produit;
import model.Recherche;

/**
 *
 * @author MOREL BEN Taboaly
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here7
        Recherche recherche = new Recherche();
        Vector<String> res = Recherche.diviser("prix pire");
        Connection c  = connexion.Connexion.getConnection();
        Vector<String> reste = recherche.findCategorieInRecherche(c, res);

        String mot_reste = Recherche.transformVectorToString(reste);
//        System.out.println(mot_reste);
        Mot_cle mot_cle = new Mot_cle();
        mot_cle = mot_cle.getMotCle(c, mot_reste);
        recherche.setMot_cle(mot_cle);
//        Vector<Produit> vp = recherche.recherche(c,Recherche.diviser("pire prix"));
////        System.out.println("Nb :"+vp.size());
//        
////        System.out.println(recherche.getCategorie().getNom());
//        for (int i = 0; i < vp.size(); i++) {
//            Produit elementAt = vp.elementAt(i);
//            System.out.println(elementAt.getNom());
//        }
//        
        
        
//        for(int i=0;i<res.size();i++){         
//            System.err.println(res.get(i));
// 
//        }
       // System.err.println(c);
//        for(int i=0;i<reste.size();i++){         
//            System.err.println(reste.get(i));
// 
//        }
//        
//      String s1 = "meilleur qualite prix";
//        String s2 = "prix m eilleur qualite";
//    System.out.println(recherche.getMot_cle().getNom());
        
    }
      
    
}
