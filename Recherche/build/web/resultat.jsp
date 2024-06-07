<%-- 
    Document   : resultat
    Created on : 31 janv. 2024, 00:26:01
    Author     : MOREL BEN Taboaly
--%>
<%@page import="model.Produit"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.Vector"%>
<%
    Vector<Produit> prods = (Vector<Produit>) request.getAttribute("prods");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Resultat du recherche</h1>
        <table border="1" width="700">
            <thead>
                <tr>
                    <td>Nom</td>
                    <td>Categorie</td>
                    <td>Qualite</td>
                    <td>Prix</td>
                    <td>Rapport</td>
                </tr>
                   
            </thead>
            <tbody>
                <% for(Produit prod  : prods) {%>
                <tr>
                    <td><%=prod.getNom() %></td>
                    <td><%=prod.getCategorie().getNom() %></td>
                    <td><%=prod.getQualite() %></td>
                    <td><%=prod.getPrix() %></td>
                    <td><%=prod.getRapport() %></td>
                </tr>
                <% } %>
            </tbody>
            
        </table>
    </body>
</html>
