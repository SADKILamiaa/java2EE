package com.projet.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/Deconnexions")
public class Deconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String URL_REDIRECTION ="Acceuil";
public void doGet( HttpServletRequest request,HttpServletResponse response ) throws ServletException, IOException
{
/* R�cup�ration et destruction de la session en cours */
HttpSession session = request.getSession();
session.removeAttribute("sessionUtilisateur");

/* Redirection vers le Site du Z�ro ! */
response.sendRedirect( URL_REDIRECTION );

}
}