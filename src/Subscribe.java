
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Subscribe
 */
@WebServlet("/Subscribe")
public class Subscribe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Subscribe() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Boolean isLogged = (Boolean) session.getAttribute("isLogged");
		getServletContext().getRequestDispatcher("/WEB-INF/subscribe.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Boolean isLogged = (Boolean) session.getAttribute("isLogged");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String confirmation = request.getParameter("confirmation");

		if(!password.equals(confirmation) || login.equals("") || password.equals("")){
			doGet(request, response);
		}
 
		
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println(">> Driver connected");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shorturl", "root", "");
			System.out.println(">> Connected");

			Statement createState = conn.createStatement();
			String query = "INSERT INTO users (login, password) VALUES ('" + login + "', '" + password + "')";
			createState.executeUpdate(query);
			
			conn.close();

		} catch (ClassNotFoundException e) {
			System.err.println(">> Driver not connected");
		} catch (SQLException e) {
			System.err.println("Erreur SQL : " + e.getMessage());
		}
	}
}
