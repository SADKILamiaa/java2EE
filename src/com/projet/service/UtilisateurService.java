package com.projet.service;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.projet.model.Utilisateur;
import com.projet.dao.Utilisateurdao;


public class UtilisateurService {
	
	
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	public Map<String, String> getErreurs() 
	{
		return erreurs;
	}
	
	public String getResultat() 
	{
		return resultat;
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
	Utilisateur u = new Utilisateur();
	try 
	{
		VerificationDonn�e.validationNom(nom);
	} 
	catch (Exception e) 
	{
		setErreurs("nom", e.getMessage());
	}
	utilisateur.setNom(nom);
	try 
	{
		VerificationDonn�e.validationPrenom(prenom);
	} 
	catch (Exception e) 
	{
		setErreurs("prenom", e.getMessage());
	}
	utilisateur.setPrenom(prenom);
	utilisateur.setCin(cin);
	try 
	{
		VerificationDonn�e.validationEmail(email);
	} 
	catch (Exception e) 
	{
		setErreurs("email", e.getMessage());
	}
	utilisateur.setEmail(email);
	utilisateur.setMdp(mdp);
	try 
	{
		VerificationDonn�e.validationTelephone(telephone);
	} 
	catch (Exception e) 
	{
		setErreurs("telephone", e.getMessage());
	}
	utilisateur.setTelephone(telephone);
	
	if (erreurs.isEmpty())
	{ 
	   u= utilisateurdao.Chercher(email);
	   if(u==null)
	   {
		   utilisateurdao.ajouter(utilisateur);
	   }
	   else
	   {
			resultat = "Adresse email d�ja utilis�"; 
	   }
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
	Utilisateur utilisateur=utilisateurdao.trouver(email,mdp);
	if(utilisateur==null)
	   {
		resultat = "Adresse email ou mots passe incorrecte"; 
	   }
	return utilisateur;
    }
	
	
	public Utilisateur ModifierUtilisateur(HttpServletRequest request, Utilisateur utilisateur)
	{
	String nom =VerificationDonn�e.getValeurChamp(request, "nom");
	String prenom= VerificationDonn�e.getValeurChamp(request, "prenom");
	String cin =VerificationDonn�e.getValeurChamp(request, "cin");
	String telephone =VerificationDonn�e.getValeurChamp(request, "telephone");
	String email =VerificationDonn�e.getValeurChamp(request, "email");
	String mdp =VerificationDonn�e.getValeurChamp(request, "mdp");
	String valeur =VerificationDonn�e.getValeurChamp(request, "id");
	utilisateur.setId(Integer.parseInt(valeur));
	try 
	{
		VerificationDonn�e.validationNom(nom);
	} 
	catch (Exception e) 
	{
		setErreurs("nom", e.getMessage());
	}
	utilisateur.setNom(nom);
	try 
	{
		VerificationDonn�e.validationPrenom(prenom);
	} 
	catch (Exception e) 
	{
		setErreurs("prenom", e.getMessage());
	}
	utilisateur.setPrenom(prenom);
	utilisateur.setCin(cin);
	try 
	{
		VerificationDonn�e.validationEmail(email);
	} 
	catch (Exception e) 
	{
		setErreurs("email", e.getMessage());
	}
	utilisateur.setEmail(email);
	utilisateur.setMdp(mdp);
	try 
	{
		VerificationDonn�e.validationTelephone(telephone);
	} 
	catch (Exception e) 
	{
		setErreurs("telephone", e.getMessage());
	}
	utilisateur.setTelephone(telephone);
	
	if (erreurs.isEmpty())
	{
		utilisateurdao.modifier( utilisateur);
	
		resultat = "Reussite de la Modification du Information Personnelles";
	
	} 
	else 
	{
		resultat = "�chec de la Modification du Information Personnelles";	
	}
	return utilisateur;
}
	
private void setErreurs(String champ, String message) 
{
	erreurs.put(champ, message);
}

}