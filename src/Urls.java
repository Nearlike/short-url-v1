
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shorturl.beans.Url;

/**
 * Servlet implementation class Urls
 */
@WebServlet("/Urls")
public class Urls extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Urls() {
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
		int idUser = Integer.parseInt((String) session.getAttribute("id"));
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println(">> Driver connected");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shorturl", "root", "");
			System.out.println(">> Connected");

			String anSQLquery = "SELECT * FROM urls WHERE id_user = ?";
			PreparedStatement state = conn.prepareStatement(anSQLquery);
			state.setInt(1, idUser);
			ResultSet urls = state.executeQuery();
			
			List<Url> listUrl = new ArrayList();
			
			while(urls.next()){
				Url url = new Url();
				url.setUrlLong(urls.getString("url_long"));
				url.setUrlShort(urls.getString("url_short"));
				url.setDate(urls.getString("date_create"));
				listUrl.add(url);
			}
			
			request.setAttribute("listUrl", listUrl);
			getServletContext().getRequestDispatcher("/WEB-INF/moncompte.jsp").forward(request, response);
			
			conn.close();

		} catch (ClassNotFoundException e) {
			System.err.println(">> Driver not connected");
		} catch (SQLException e) {
			System.err.println("Erreur SQL : " + e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
