/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author fex
 */
public class Connexion {
    public  static Connection getConnection()throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/recherche2";
        String utilisateur = "postgres";
        String motDePasse = "3040132";
        Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
        return connexion;
    }
}


