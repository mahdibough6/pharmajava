package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Ordonnance;

public class OrdonnanceDAO {

	    private static final String INSERT_ORDS = "INSERT INTO ordonnances ("
	    	    +"fichier_ord,"
	    	    +"id_comm "
	    		+ ") VALUES  (?, ?);";

	    private static final String SELECT_ORD_BY_ID = "select * from ordonance where id_ord =?";

	    public OrdonnanceDAO() {}


	    public void insererOrdonnance(Ordonnance ord) {
	        try (Connection connection = SingletonConnection.getConnection() ; 
	        	 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDS)) {

	            preparedStatement.setString(1, ord.getFichier_ord());
	            preparedStatement.setInt(2, ord.getId_comm());
	            
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	    }

	    public Ordonnance selecterOrdonnance(int id_ord) {
	        Ordonnance ord = null;

	        try (Connection connection = SingletonConnection.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORD_BY_ID);) {
	            preparedStatement.setInt(1, id_ord);

	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	                String fichier_ord = rs.getString("fichier_ord");
	                int id_comm = rs.getInt("id_comm");

	                ord = new Ordonnance(id_ord, fichier_ord, id_comm);
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return ord;
	    }

	    
	    private void printSQLException(SQLException ex) {
	        for (Throwable e: ex) {
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
