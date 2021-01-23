package com.projet.dao;
import static com.projet.dao.DAOUtilitaire2.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.projet.dao.Utilisateurdao;
import com.projet.model.Utilisateur;

/*En pr�cisant directement dans l'import le mot-cl� static, vous pourrez appeler vos m�thodes comme si elles faisaient
directement partie de votre classe courante.*/

public class UtilisateurDaoImpl implements Utilisateurdao 
{
	private static final String SQL_INSERT = "INSERT INTO utilisateur (cin,id,nom,prenom,sexe,login,mdp,telephone,email) VALUES (?, ?,?, ?,?,?,?,?,?)";
	private static final String sql1="SELECT * FROM utilisateur WHERE email = ? && mdp =?";
	
	private DAOFactory daoFactory;
	
	/*r�cup�rer une connexion, le DAO doit donc avoir acc�s � une instance de
	la DAOFactory.*/
	
	UtilisateurDaoImpl( DAOFactory daoFactory ) 
	{
	this.daoFactory = daoFactory;
	}
	/* Impl�mentation de la m�thode d�finie dans l'interface
	ClientDao */
	
	public void ajouter( Utilisateur utilisateur ) throws DAOException 
	{
	Connection connexion = null;
	PreparedStatement preparedStatement = null;
	try 
	{
		/* R�cup�ration d'une connexion depuis la Factory */
		connexion = daoFactory.getConnection();
		preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT,utilisateur.getCin(),utilisateur.getId(),utilisateur.getNom(),utilisateur.getPrenom(),utilisateur.getSexe(),utilisateur.getLogin(),utilisateur.getMdp(),utilisateur.getTelephone(),utilisateur.getEmail());
		int statut = preparedStatement.executeUpdate();
		if ( statut == 0 ) 
		{
			throw new DAOException( "�chec de la cr�ation du client, aucune ligne ajout�e dans la table." );
		}
	}
	catch ( SQLException e ) 
	{
		throw new DAOException( e );
	}
	finally {
		fermeturesSilencieuses(preparedStatement, connexion );
		}
}
	
	
	public Utilisateur trouver( String mdp , String email ) throws DAOException
	{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Utilisateur utilisateur = null;
		
		try {
			/* R�cup�ration d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			* Pr�paration de la requ�te avec les objets pass�s en arguments
			* (ici, uniquement un id) et ex�cution.
			*/
			preparedStatement = initialisationRequetePreparee(connexion, sql1,utilisateur.getEmail(),utilisateur.getMdp());
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de donn�es retourn�e dans le
			ResultSet */
			if ( resultSet.next() ) 
			{
			utilisateur = map( resultSet );
			}
			} 
		  catch ( SQLException e )
		   {
			throw new DAOException( e );
			} 
		finally 
		{
			fermeturesSilencieuses( resultSet, preparedStatement,connexion );
	   }
		return utilisateur;	
	}
	
	public void supprimer( Utilisateur utilisateur ) throws DAOException {
	}
	
	public void modifier( Utilisateur utilisateur ) throws DAOException
	{
	}
	
	private static Utilisateur map( ResultSet resultSet ) throws
	SQLException {
	Utilisateur utilisateur = new Utilisateur();
	utilisateur.setNom( resultSet.getString( "nom" ) );
	System.out.print(utilisateur.getNom());
	return utilisateur;
	}
	
	
}

	
	
	
	