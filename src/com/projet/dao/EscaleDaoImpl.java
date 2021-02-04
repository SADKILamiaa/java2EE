package com.projet.dao;

import static com.projet.dao.DAOUtilitaire2.fermeturesSilencieuses;
import static com.projet.dao.DAOUtilitaire2.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EscaleDaoImpl implements EscaleDao {
	private static final String SQL_SELECT_PAR_ID="SELECT COUNT(*) FROM escale WHERE Vol_id=?";

	private static DAOFactory daoFactory;

	EscaleDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

	public int compter( int id ) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int a = 0;
		try {
			/* R�cup�ration d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			* Pr�paration de la requ�te avec les objets pass�s en arguments
			* (ici, uniquement un id) et ex�cution.
			*/
			preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_PAR_ID, id);
			resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() )
			{
				a=resultSet.getInt(1);
			}
		}
			catch ( SQLException e ) {
				throw new DAOException( e );
				} 
				finally {
				fermeturesSilencieuses( resultSet, preparedStatement,
				connexion );
				}
				return a;
				}
	}

