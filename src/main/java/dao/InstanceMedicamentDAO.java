package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.InstanceMedicament;

public class InstanceMedicamentDAO {


	 PreparedStatement St=null;
	 ResultSet rs = null;
	 private static final String SELECT_ALL_Inst= "select * from instances_med ";
	 private static final String Insert_Inst= "INSERT INTO instances_med(date_exp_med,date_prod_med,qte_stock_med,id_med) VALUES(?,?,?,?);  ";
	 private static final String SELECT_INSTANCE_BY_ID = "select * from instance_med where id_instance_med = ?";
	 
	 public void insert(InstanceMedicament I) {
		 try { Connection connection = SingletonConnection.getConnection();
			St=connection.prepareStatement(Insert_Inst);
			St.setDate(1, I.getDate_exp_med());
			St.setDate(2, I.getDate_prod_med());
			St.setInt(3, I.getQte_stock_med());
			St.setInt(4, I.getId_med());
			St.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	 public static Date ConverteDate(String d) {
		 Date sqlDate=null;
		  DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // Format de la chaîne de caractères
	      try {
	         java.util.Date date = df.parse(d); // Conversion de la chaîne en Date
	         long time = date.getTime(); // Nombre de millisecondes écoulées depuis le 1er janvier 1970
	       sqlDate = new Date(time);
	       
	      } catch (ParseException e) {
	         e.printStackTrace();
	      }
	       
		 return sqlDate;
	 }
	 public List<InstanceMedicament> List_Inst(){
		 List<InstanceMedicament> Inst=new ArrayList<InstanceMedicament>();
		
		 try { Connection connection = SingletonConnection.getConnection();
			St=connection.prepareStatement(SELECT_ALL_Inst);
			rs= St.executeQuery();
			while(rs.next()) {
				InstanceMedicament Ins=new InstanceMedicament();
				Ins.setId_instance_med(rs.getInt("id_instance_med"));
				Ins.setId_med(rs.getInt("id_med"));
				Ins.setQte_stock_med(rs.getInt("qte_stock_med"));
				Ins.setDate_exp_med(rs.getDate("date_exp_med"));
				Ins.setDate_prod_med(rs.getDate("date_prod_med"));
				Inst.add(Ins);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return Inst;
		 
	 }
	 public InstanceMedicament selecterInstanceMedicament(int id_instance_med){
		
InstanceMedicament Ins = null;
		 try { Connection connection = SingletonConnection.getConnection();
			St=connection.prepareStatement(SELECT_INSTANCE_BY_ID);
			St.setInt(1, id_instance_med);
			rs= St.executeQuery();
			while(rs.next()) {
				Ins=new InstanceMedicament();
				Ins.setId_instance_med(rs.getInt("id_instance_med"));
				Ins.setId_med(rs.getInt("id_med"));
				Ins.setQte_stock_med(rs.getInt("qte_stock_med"));
				Ins.setDate_exp_med(rs.getDate("date_exp_med"));
				Ins.setDate_prod_med(rs.getDate("date_prod_med"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return  Ins;
		 
	 }
	
}