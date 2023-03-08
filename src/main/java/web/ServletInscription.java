package web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Utilisateur;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import dao.UtilisateurDAO;



@WebServlet("/inscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		RequestDispatcher rd ;
		//if the user is already logged in
		if(session.getAttribute("user") != null) {
         rd = request.getRequestDispatcher("/accueil");
         rd.forward(request, response);
		}
	// if not 	
		else {
         rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
         rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nom_util = request.getParameter("nomUtil");
		String prenom_util = request.getParameter("prenomUtil");
		String email_util= request.getParameter("emailUtil");
		String pwd_util = request.getParameter("pwdUtil");
		String date_naiss_util_text  = request.getParameter("dateNaissUtil");
		String adresse_util = request.getParameter("adresseUtil");
		String tele_util = request.getParameter("teleUtil");
		
		
		Date date_naiss_util = Date.valueOf(date_naiss_util_text);
		
		
		Utilisateur util = new Utilisateur(nom_util, prenom_util,date_naiss_util,adresse_util,  tele_util,  email_util, pwd_util);
		UtilisateurDAO utilDAO = new UtilisateurDAO();
		utilDAO.insererUtilisateur(util);
		HttpSession session=request.getSession();
        session.setAttribute("user", util);	
        
        RequestDispatcher rd = request.getRequestDispatcher("panier.js");
        rd.forward(request, response);
	
        
        

	}

}
