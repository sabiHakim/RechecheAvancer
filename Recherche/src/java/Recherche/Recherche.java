/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recherche;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Mot_cle;
import model.Produit;

/**
 *
 * @author MOREL BEN Taboaly
 */
public class Recherche extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Recherche</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Recherche at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // processRequest(request, response);
       String re = request.getParameter("recherche");
        model.Recherche recherche = new model.Recherche();
        Vector<String> res = model.Recherche.diviser(re);
        Connection c;
        try {
            c = connexion.Connexion.getConnection();
            Vector<String> res3 = model.Recherche.newRecherche(res);
               Vector<String> reste = recherche.findCategorieInRecherche(c, res3);
        String mot_reste = model.Recherche.transformVectorToString(reste);
        Mot_cle mot_cle = new Mot_cle();
       
       
        if(reste.size()==0){
             mot_cle = mot_cle.getMotCle(c,"produit");
        }else {
        mot_cle = mot_cle.getMotCle(c, mot_reste);
       }
        recherche.setMot_cle(mot_cle);
      // Vector<String> res2 = model.Recherche.diviser(re);
       // Vector<String> res3 = model.Recherche.newRecherche(res2);
   
        Vector<Produit> prod = recherche.recherche(c,model.Recherche.diviser(re),res3);
        c.close();
        request.setAttribute("prods", prod);
        request.getRequestDispatcher("resultat.jsp").forward(request, response);
        
        } catch (Exception ex) {
           
        }
     
       
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
