package com.projet.service;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import com.projet.model.Utilisateur;
import com.projet.dao.DAOException;
import com.projet.dao.Utilisateurdao;
import com.projet.service.*;
public class UtilisateurService {
	
	
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	public Map<String, String> getErreurs() 
	{
		return erreurs;
	}
	private Utilisateurdao utilisateurdao;
	
	public UtilisateurService( Utilisateurdao  utilisateurdao) 
	{
		this.utilisateurdao = utilisateurdao;
	}
	public Utilisateur creerUtilisateur(HttpServletRequest request)
	{
	String nom =VerificationDonn�e.getValeurChamp(request, "nom");
	String prenom= VerificationDonn�e.getValeurChamp(request, "prenom");
	String cin =VerificationDonn�e.getValeurChamp(request, "cin");
	String telephone =VerificationDonn�e.getValeurChamp(request, "telephone");
	String email =VerificationDonn�e.getValeurChamp(request, "email");
	String mdp =VerificationDonn�e.getValeurChamp(request, "mdp");

	Utilisateur utilisateur = new Utilisateur();
	try 
	{
		VerificationDonn�e.validationNom(nom);
	} 
	catch (Exception e) 
	{
		setErreur("nom", e.getMessage());
	}
	utilisateur.setNom(nom);
	try 
	{
		VerificationDonn�e.validationPrenom(prenom);
	} 
	catch (Exception e) 
	{
		setErreur("prenom", e.getMessage());
	}
	utilisateur.setPrenom(prenom);
	utilisateur.setCin(cin);
	try 
	{
		VerificationDonn�e.validationEmail(email);
	} 
	catch (Exception e) 
	{
		setErreur("email", e.getMessage());
	}
	utilisateur.setEmail(email);
	utilisateur.setMdp(mdp);
	try 
	{
		VerificationDonn�e.validationTelephone(telephone);
	} 
	catch (Exception e) 
	{
		setErreur("telephone", e.getMessage());
	}
	utilisateur.setTelephone(telephone);
	
	if (erreurs.isEmpty())
	{
		utilisateurdao.ajouter( utilisateur);
		resultat = "Succ�s de la cr�ation du client.";
	} 
	else 
	{
		resultat = "�chec de la cr�ation du client.";
	}
	return utilisateur;
}
	
	
	
	public Utilisateur connecterUtilisateur(HttpServletRequest request)
	{
	String email =VerificationDonn�e.getValeurChamp(request, "email");
	String mdp =VerificationDonn�e.getValeurChamp(request, "mdp");
	Utilisateur utilisateur = new Utilisateur();
	utilisateur.setEmail(email);
	utilisateur.setMdp(mdp);

	return utilisateur;
}
	
private void setErreur(String champ, String message) 
{
	erreurs.put(champ, message);
}
}