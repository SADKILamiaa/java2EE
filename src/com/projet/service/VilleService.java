package com.projet.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.projet.dao.Utilisateurdao;
import com.projet.dao.VilleDao;
import com.projet.model.Ville;

public class VilleService {
	private VilleDao villedao;
	
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	public String getResultat() {
		return resultat;
	}
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	public VilleService( VilleDao  villedao) 
	{
		this.villedao = villedao;
	}
	public Ville creerVille(HttpServletRequest request)
	{
		Ville ville =new Ville();
		String nom =VerificationDonn�e.getValeurChamp(request, "nom");
		
		try 
		{
			VerificationDonn�e.validationNom(nom);
		} 
		catch (Exception e) 
		{
			setErreur("nom", e.getMessage());
		}
		ville.setNom(nom);
		
		if (erreurs.isEmpty())
		{
			villedao.ajouter(ville);
			resultat = "Succ�s de la cr�ation du client.";
		} 
		else 
		{
			resultat = "�chec de la cr�ation du client.";
		}
		return ville;
		
	}


private void setErreur(String champ, String message) 
{
	erreurs.put(champ, message);
}
}