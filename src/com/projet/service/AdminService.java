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
import com.projet.dao.AdminDao;
import com.projet.dao.DAOException;
import com.projet.dao.Utilisateurdao;
import com.projet.service.*;


public class AdminService {
	
	
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	public Map<String, String> getErreurs() 
	{
		return erreurs;
	}
	private AdminDao admindao;
	
	public AdminService( AdminDao admindao) 
	{
		this.admindao = admindao;
	}
	public Utilisateur creerAdmin(HttpServletRequest request)
	{
	String nom =VerificationDonnée.getValeurChamp(request, "nom");
	String prenom= VerificationDonnée.getValeurChamp(request, "prenom");
	
	String telephone =VerificationDonnée.getValeurChamp(request, "telephone");
	String email =VerificationDonnée.getValeurChamp(request, "email");
	String mdp =VerificationDonnée.getValeurChamp(request, "mdp");

	Utilisateur admin = new Utilisateur();
	try 
	{
		VerificationDonnée.validationNom(nom);
	} 
	catch (Exception e) 
	{
		setErreur("nom", e.getMessage());
	}
	admin.setNom(nom);
	try 
	{
		VerificationDonnée.validationPrenom(prenom);
	} 
	catch (Exception e) 
	{
		setErreur("prenom", e.getMessage());
	}
	admin.setPrenom(prenom);
	
	try 
	{
		VerificationDonnée.validationEmail(email);
	} 
	catch (Exception e) 
	{
		setErreur("email", e.getMessage());
	}
	admin.setEmail(email);
	admin.setMdp(mdp);
	try 
	{
		VerificationDonnée.validationTelephone(telephone);
	} 
	catch (Exception e) 
	{
		setErreur("telephone", e.getMessage());
	}
	admin.setTelephone(telephone);
	
	if (erreurs.isEmpty())
	{
		admindao.ajouter( admin);
		resultat = "Succès de la création du client.";
	} 
	else 
	{
		resultat = "Échec de la création du client.";
	}
	return admin;
}
	
	
	
	public Utilisateur connecterUtilisateur(HttpServletRequest request)
	{
	String email =VerificationDonnée.getValeurChamp(request, "email");
	String mdp =VerificationDonnée.getValeurChamp(request, "mdp");
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