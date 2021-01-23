package com.projet.dao;

import java.sql.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class DAOUtilitaire2
{
	/* initialiser une requ�te pr�par�e avec des param�tres */
	/* r�cup�re une liste de param�tres et les ajoute � une requ�te pr�par�e donn�e */
	/* initialiser une requ�te pr�par�e avec des param�tres */
	public static PreparedStatement initialisationRequetePreparee( Connection connexion, String sql,Object... objets ) throws SQLException 
	{
		/*la connexion, dont nous avons besoin pour appeler la m�thode connexion.prepareStatement() ;
		la requ�te SQL, que nous passons en argument lors de l'appel pour construire l'objet PreparedStatement ;
		un bool�en, indiquant s'il faut ou non retourner d'�ventuelles valeurs auto-g�n�r�es. Nous l'utilisons alors directement au
		sein de l'appel � connexion.prepareStatement() gr�ce � une simple expression ternaire ;
		une succession d'objets... de tailles variables ! Eh oui, l� encore nous n'avons aucun moyen d'anticiper et de savoir �
		l'avance combien de param�tres attend notre requ�te pr�par�e.*/
		
			PreparedStatement preparedStatement =connexion.prepareStatement( sql);
			for ( int i = 0; i < objets.length; i++ ) {
				/*n'aurons ici aucun moyen simple de savoir de quel type est chaque objet. Heureusement, il existe une m�thode
				preparedStatement.setObject() qui prend en argument un objet de type Object, et qui s'occupe ensuite derri�re
				les rideaux d'effectuer la conversion vers le type SQL du param�tre attendu avant d'envoyer la requ�te � la base de donn�es.*/
				
			preparedStatement.setObject( i + 1, objets[i] );
			}
			return preparedStatement;
			}
	
	
	
	public static void fermetureSilencieuse( ResultSet resultSet )
	{
		if ( resultSet != null ) {
		try {
		resultSet.close();
		} catch ( SQLException e ) {
		System.out.println( "�chec de la fermeture du ResultSet : " + e.getMessage() );
		}
		}
		}
		/* Fermeture silencieuse du statement */
		public static void fermetureSilencieuse( Statement statement ) {
		if ( statement != null ) {
		try {
		statement.close();
		} 
		catch ( SQLException e ) 
		{
		System.out.println( "�chec de la fermeture du Statement : " + e.getMessage() );
		}
		}
		}
		/* Fermeture silencieuse de la connexion */
		public static void fermetureSilencieuse( Connection connexion )
		{
		if ( connexion != null ) {
		try {
		connexion.close();
		} 
		catch ( SQLException e ) 
		{
		System.out.println( "�chec de la fermeture de la connexion : " + e.getMessage() );
		}
		}
		}
		/* Fermetures silencieuses du statement et de la connexion */
		public static void fermeturesSilencieuses( Statement statement,Connection connexion ) {
		fermetureSilencieuse( statement );
		fermetureSilencieuse( connexion );
		}
		/* Fermetures silencieuses du resultset, du statement et de la
		connexion */
		public static void fermeturesSilencieuses( ResultSet resultSet,Statement statement, Connection connexion ) 
		{
		fermetureSilencieuse( resultSet );
		fermetureSilencieuse( statement );
		fermetureSilencieuse( connexion );
		}
}
