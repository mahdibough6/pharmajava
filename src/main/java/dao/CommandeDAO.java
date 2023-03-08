package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Commande;

public class CommandeDAO {

	    private static final String INSERT_USERS = "INSERT INTO commandes ("
	    	    +"status_comm,"
	    	    +"id_ord INT,"
	    	    +"id_util INT NOT NULL, "
	    	    +"date_comm DATE"
	    		+ ") VALUES  (?, ?, ?, ?, ?);";

	    private static final String SELECT_ORDER_BY_ID = "select * from commandes where id_comm =?";
	    private static final String SELECT_ALL_ORDERS = "select * from commandes";
	    private static final String UPDATE_ORDER_STATUS = "update commandes SET status_comm = ? where id_comm = ?";

	    public CommandeDAO() {}


	    public void insererCommande(Commande comm) {
	        try (Connection connection = SingletonConnection.getConnection() ; 
	        	 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS)) {

	            preparedStatement.setInt(1, comm.getStatus_comm());
	            preparedStatement.setInt(2, comm.getId_ord());
	            preparedStatement.setInt(3, comm.getId_util());
	            preparedStatement.setDate(4, comm.getDate_comm());

	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	    }

	    // ici le status comm peut etre soit 1 , 2 , 3 ou 4 
	    // 1 => PANIER
	    // 2 => ENCOURS 
	    // 3 => VALIDER 
	    // 4 => REFUSER 

	    public void changerStatusCommande(int id_comm, int status_comm) {
	    	 try (Connection connection = SingletonConnection.getConnection() ; 
	        	 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATUS)) {
	            preparedStatement.setInt(2, id_comm);
	            preparedStatement.setInt(1, status_comm);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	    }
	    

	    public Commande selecterCommande(int id_comm) {
	        Commande comm = null;

	        try (Connection connection = SingletonConnection.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_ID);) {
	            preparedStatement.setInt(1, id_comm);

	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	                int status_comm = rs.getInt("status_comm");
	                int id_util = rs.getInt("id_util");
	                int id_ord = rs.getInt("id_ord");
	                Date date_comm = rs.getDate("date_comm");

	                comm = new Commande(id_comm, status_comm, id_util, id_ord, date_comm);
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return comm;
	    }
	    
public List < Commande > selecterToutesCommandes() {

	        List < Commande >comms = new ArrayList < > ();
	        try (Connection connection = SingletonConnection.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS);) {

	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	                int id_comm = rs.getInt("id_comm");
	                int status_comm = rs.getInt("status_comm");
	                int id_util = rs.getInt("id_util");
	                int id_ord = rs.getInt("id_ord");
	                Date date_comm = rs.getDate("date_comm");
	                comms.add(new Commande(id_comm, status_comm, id_util, id_ord, date_comm));
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return comms;
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
