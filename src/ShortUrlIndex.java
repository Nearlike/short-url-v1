
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;

import shorturl.beans.Url;

/**
 * Servlet implementation class ShortUrlAction
 */
@WebServlet("/ShortUrlIndex")
public class ShortUrlIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShortUrlIndex() {
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
		getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Boolean isLogged = (Boolean) session.getAttribute("isLogged");
		Url url = new Url();
		
		if(!request.getParameter("url").equals("")){
			url.setUrlLong(request.getParameter("url"));
		}else{
			doGet(request, response);
		}

		String randomString = RandomStringUtils.randomAlphabetic(7);
		url.setUrlShort("http://localhost:8080/short-url/" + randomString);
		if (request.getParameter("activePassword") == null) {
			url.setPassword(null);
		} else {
			url.setPassword(request.getParameter("password"));
		}

		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println(">> Driver connecting");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shorturl", "root", "");
			System.out.println("<< Connected");

			String query = "INSERT INTO urls (url_long, url_short, password, nbClics) VALUES (?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(query);
			
			statement.setObject(1,url.getUrlLong(), Types.VARCHAR); 
			statement.setObject(2,url.getUrlShort(),Types.VARCHAR); 
			if (url.getPassword()!=null) {
				statement.setObject(3,url.getPassword(),Types.VARCHAR);
			} else {
				statement.setObject(3, null,Types.VARCHAR);
			}
			statement.setObject(4, 0, Types.INTEGER);
			int insertDone = statement.executeUpdate();
			
			if(insertDone == 1){
				request.setAttribute("urlShort", url.getUrlShort());
				getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
			}
			
			conn.close();

		} catch (ClassNotFoundException e) {
			System.err.println(">> Driver not connected");
		} catch (SQLException e) {
			System.err.println("Erreur SQL : " + e.getMessage());
		}
	}

}
