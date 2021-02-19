package com.projet.dao;
import static com.projet.dao.DAOUtilitaire2.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.projet.dao.Utilisateurdao;
import com.projet.model.Reservation;
import com.projet.model.Utilisateur;
;

/*En pr�cisant directement dans l'import le mot-cl� static, vous pourrez appeler vos m�thodes comme si elles faisaient
directement partie de votre classe courante.*/

public class UtilisateurDaoImpl implements Utilisateurdao 
{
	private static final String SQL_INSERT = "INSERT INTO utilisateur (cin,id,nom,prenom,sexe,mdp,telephone,email) VALUES (?, ?,?, ?,?,?,?,?)";
	private static final String sql1="SELECT * FROM utilisateur WHERE email = ? && mdp =?";
	private static final String sql2="SELECT * FROM utilisateur WHERE email = ?";
	private static final String SQL_UPDATE = "UPDATE utilisateur SET cin=?, nom=?,prenom=?,mdp=?,telephone=?,email=? WHERE  id= ?";
	private static  DAOFactory daoFactory;
	private static final String sql55="SELECT * FROM utilisateur WHERE email = ?";

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
		preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT,utilisateur.getCin(),utilisateur.getId(),utilisateur.getNom(),utilisateur.getPrenom(),utilisateur.getSexe(),utilisateur.getMdp(),utilisateur.getTelephone(),utilisateur.getEmail());
		int statut = preparedStatement.executeUpdate();
		if (statut == 0 ) 
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
	
	public Utilisateur trouver(String email, String mdp) throws DAOException {
		return trouver( sql1, email,mdp);
		}
	

	public Utilisateur Chercher(String emaile) throws DAOException {
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
		preparedStatement = initialisationRequetePreparee(connexion, sql2,emaile );
		resultSet = preparedStatement.executeQuery();
		/* Parcours de la ligne de donn�es retourn�e dans le
		ResultSet */
		if ( resultSet.next() ) {	
		 utilisateur= map( resultSet );
		}
		}
		catch ( SQLException e ) {
		throw new DAOException( e );
		} 
		finally {
		fermeturesSilencieuses( resultSet, preparedStatement,connexion );
		}
		return utilisateur;
		}
	
	private Utilisateur trouver( String sql, Object... objets ) throws DAOException {
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
		preparedStatement = initialisationRequetePreparee(connexion, sql,objets );
		resultSet = preparedStatement.executeQuery();
		/* Parcours de la ligne de donn�es retourn�e dans le
		ResultSet */
		if ( resultSet.next() ) {	
		 utilisateur= map( resultSet );
		}
		}
		catch ( SQLException e ) {
		throw new DAOException( e );
		} 
		finally {
		fermeturesSilencieuses( resultSet, preparedStatement,connexion );
		}
		return utilisateur;
		}

	
	public void modifier(Utilisateur utilisateur) throws DAOException {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try 
		{
			
			/* R�cup�ration d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_UPDATE,utilisateur.getCin(),utilisateur.getNom(),utilisateur.getPrenom(),utilisateur.getMdp(),utilisateur.getTelephone(),utilisateur.getEmail(),utilisateur.getId());
			int statut = preparedStatement.executeUpdate();
			if (statut == 0 ) 
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
	
	
	private static Utilisateur map( ResultSet resultSet ) throws SQLException 
	{
	Utilisateur utilisateur = new Utilisateur();
	
	utilisateur.setId(resultSet.getInt("id"));
	utilisateur.setProfil(resultSet.getString("profil"));
	utilisateur.setNom(resultSet.getString("nom"));
	utilisateur.setCin(resultSet.getString("cin"));
	utilisateur.setPrenom(resultSet.getString("prenom") );
	utilisateur.setEmail(resultSet.getString("email"));
	utilisateur.setSexe(resultSet.getString("sexe"));
	utilisateur.setMdp(resultSet.getString("mdp"));
	utilisateur.setTelephone(resultSet.getString("telephone"));
	Reservationdao reservationdao = daoFactory.getReservationDao();
	utilisateur.setReservations( reservationdao.trouver( resultSet.getInt("id"),"valider"));
	utilisateur.setReservations2( reservationdao.trouver( resultSet.getInt("id"),"annuler"));
	utilisateur.setReservations3( reservationdao.trouver( resultSet.getInt("id"),"encore"));
	return utilisateur;
	}

	@Override
	public Utilisateur chercher(String email) throws DAOException {
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
		preparedStatement = initialisationRequetePreparee(connexion, sql55,email );
		resultSet = preparedStatement.executeQuery();
		/* Parcours de la ligne de donn�es retourn�e dans le
		ResultSet */
		if ( resultSet.next() ) {	
		 utilisateur= map( resultSet );
		}
		}
		catch ( SQLException e ) {
		throw new DAOException( e );
		} 
		finally {
		fermeturesSilencieuses( resultSet, preparedStatement,connexion );
		}
		return utilisateur;
		}
	
	}


	
	
	
	