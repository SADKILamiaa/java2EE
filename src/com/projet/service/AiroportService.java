package com.projet.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.projet.dao.AeropDAO;
import com.projet.model.Airport;

public class AiroportService {
	private AeropDAO airopordao;
	
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	public String getResultat() {
		return resultat;
	}
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	public AiroportService( AeropDAO  airopordao) 
	{
		this.airopordao = airopordao;
	}
	public Airport creerairoport(HttpServletRequest request)
	{
		Airport airoport =new Airport();
		String nom =VerificationDonn�e.getValeurChamp(request, "nom");
		
		try 
		{
			VerificationDonn�e.validationNom(nom);
		} 
		catch (Exception e) 
		{
			setErreur("nom", e.getMessage());
		}
		airoport.setNom(nom);
		
		if (erreurs.isEmpty())
		{
			airopordao.ajouter(airoport);
			resultat = "Succ�s de la cr�ation du  A�roport.";
		} 
		else 
		{
			resultat = "�chec de la cr�ation du  A�roport.";
		}
		return airoport;
		
	}


private void setErreur(String champ, String message) 
{
	erreurs.put(champ, message);
}
}