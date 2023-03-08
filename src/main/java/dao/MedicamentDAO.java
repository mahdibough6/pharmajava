package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Medicament;

public class MedicamentDAO {

	    private static final String SELECT_MED_BY_ID = "select * from utilisateurs where id_med =?";
	    private static final String SELECT_ALL_MEDS= "select * from medicaments ";

	    public MedicamentDAO() {}


	    

	    public Medicament selecterMedicament(int id_med) {
	        Medicament med = null;

	        try (Connection connection = SingletonConnection.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MED_BY_ID);) {
	            preparedStatement.setInt(1, id_med);

	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	                String image_med = rs.getString("image_med");
	                String titre_med = rs.getString("titre_med");
	                String desc_med = rs.getString("desc_med");
	                Double prix_med = rs.getDouble("prix_med");
	                boolean status_ord_med = rs.getBoolean("status_ord_med");
	                boolean status_med = rs.getBoolean("status_med");

	                med = new  Medicament( id_med,  image_med,  titre_med,  desc_med, prix_med, status_ord_med, status_med);
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return med;
	    }

	    public List < Medicament > selecterTousMedicament() {

	        List < Medicament > meds = new ArrayList < > ();
	        try (Connection connection = SingletonConnection.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MEDS);) {

	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	                int id_med = rs.getInt("id_med");
	                String image_med = rs.getString("image_med");
	                String titre_med = rs.getString("titre_med");
	                String desc_med = rs.getString("desc_med");
	                Double prix_med = rs.getDouble("prix_med");
	                boolean status_ord_med = rs.getBoolean("status_ord_med");
	                boolean status_med = rs.getBoolean("status_med");
	                meds.add(new Medicament( id_med,  image_med,  titre_med,  desc_med, prix_med, status_ord_med, status_med));
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return meds;
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
