package model;

import java.sql.Date;

public class Commande {
    private int id_comm;
    private int status_comm ;
    private int id_util ;
    private int id_ord ;
    private Date date_comm ;
    
    public Commande(){
    	
    }

	

	public Commande(int status_comm, int id_util, int id_ord, Date date_comm) {
		super();
		this.status_comm = status_comm;
		this.id_util = id_util;
		this.id_ord = id_ord;
		this.date_comm = date_comm;
	}



	public Commande(int id_comm, int status_comm, int id_util, int id_ord, Date date_comm) {
		super();
		this.id_comm = id_comm;
		this.status_comm = status_comm;
		this.id_util = id_util;
		this.id_ord = id_ord;
		this.date_comm = date_comm;
	}



	public int getId_comm() {
		return id_comm;
	}

	public void setId_comm(int id_comm) {
		this.id_comm = id_comm;
	}

	public int getStatus_comm() {
		return status_comm;
	}

	public void setStatus_comm(int status_comm) {
		this.status_comm = status_comm;
	}

	public int getId_ord() {
		return id_ord;
	}

	public void setId_ord(int id_ord) {
		this.id_ord = id_ord;
	}

	public Date getDate_comm() {
		return date_comm;
	}

	public void setDate_comm(Date date_comm) {
		this.date_comm = date_comm;
	}


	/**
	 * @return the id_util
	 */
	public int getId_util() {
		return id_util;
	}

	/**
	 * @param id_util the id_util to set
	 */
	public void setId_util(int id_util) {
		this.id_util = id_util;
	}



	@Override
	public String toString() {
		return "Commande [id_comm=" + id_comm + ", status_comm=" + status_comm + ", id_util=" + id_util + ", id_ord="
				+ id_ord + ", date_comm=" + date_comm + "]";
	}
    
	
    
}
