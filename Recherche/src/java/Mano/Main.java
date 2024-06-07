/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mano;

import java.sql.Connection;
import java.util.Vector;

/**
 *
 * @author kodar
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Recherche recherche = new Recherche();
        Vector<String> res = Recherche.diviser("prix pire ");
        Connection c  = connexion.Connexion.getConnection();

        
        recherche.recherche(c, res);
    }
}
