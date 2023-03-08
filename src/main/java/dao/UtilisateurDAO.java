package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Utilisateur;

public class UtilisateurDAO {

	private static final String INSERT_USERS = "INSERT INTO utilisateurs (" + "nom_util ," + "prenom_util ,"
			+ "email_util ," + "pwd_util ," + "date_naiss_util ," + "adresse_util," + "tele_util "
			+ ") VALUES  (?, ?, ?, ?, ?, ?, ?);";

	private static final String SELECT_USER_BY_ID = "select * from utilisateurs where id_util =?";
	private static final String SELECT_USER_BY_EMAIL = "select * from utilisateurs where email_util =?";
	static Connection con = SingletonConnection.getConnection();

	public UtilisateurDAO() {
	}

	public void insererUtilisateur(Utilisateur util) {
		try (PreparedStatement preparedStatement = con.prepareStatement(INSERT_USERS)) {

			preparedStatement.setString(1, util.getNom_util());
			preparedStatement.setString(2, util.getPrenom_util());
			preparedStatement.setString(3, util.getEmail_util());
			preparedStatement.setString(4, util.getPwd_util());
			preparedStatement.setDate(5, util.getDate_naiss_util());
			preparedStatement.setString(6, util.getAdresse_util());
			preparedStatement.setString(7, util.getTele_util());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Utilisateur selectUser(int id_util) {
		Utilisateur util = null;

		try (PreparedStatement preparedStatement = con.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id_util);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String nom_util = rs.getString("nom_util");
				String prenom_util = rs.getString("prenom_util");
				Date date_naiss_util = rs.getDate("date_naiss_util");
				String adresse_util = rs.getString("adresse_util");
				String tele_util = rs.getString("tele_util");
				String email_util = rs.getString("email_util");
				String pwd_util = rs.getString("pwd_util");

				util = new Utilisateur(id_util, nom_util, prenom_util, date_naiss_util, adresse_util, tele_util,
						email_util, pwd_util);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return util;
	}

	public Utilisateur obtenirUtilParEmail(String email_util) {

		Utilisateur util = null;
		try (PreparedStatement preparedStatement = con.prepareStatement(SELECT_USER_BY_EMAIL);) {
			preparedStatement.setString(1, email_util);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				util = new Utilisateur();
				util.setEmail_util(rs.getString("email_util"));
				util.setPwd_util(rs.getString("pwd_util"));
				return util;
			} else {
				return null;
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return util;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
