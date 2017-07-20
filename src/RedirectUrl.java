
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class redirect
 */
@WebServlet("/RedirectUrl")
public class RedirectUrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RedirectUrl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getServletPath();
		request.setAttribute("path", "http://localhost:8080/short-url" + url);
		boolean isValid = true;
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println(">> Driver connected");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shorturl", "root", "");
			System.out.println(">> Connected");

			String anSQLquery = "SELECT * FROM urls WHERE url_short = ?";
			PreparedStatement state = conn.prepareStatement(anSQLquery);
			state.setString(1, "http://localhost:8080/short-url" + url);

			ResultSet urlSearch = state.executeQuery();
			while (urlSearch.next()) {

				if (urlSearch.getString("maxClics") != null) {
					if (Integer.parseInt(urlSearch.getString("maxClics")) <= Integer
							.parseInt(urlSearch.getString("nbClics"))) {
						request.setAttribute("redirectValid", false);
						isValid = false;
					}
				}
				
				if (urlSearch.getString("dateStart") != null) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

					Date date1 = format.parse(urlSearch.getString("dateStart"));
					Date date2 = format.parse(urlSearch.getString("dateEnd"));
					Date today = new Date();
					
					if (today.compareTo(date1) < 0 || date2.compareTo(date1) < 0 || date2.compareTo(today) < 0) {
						request.setAttribute("redirectValid", false);
						isValid = false;
					}
				}

				if (urlSearch.getString("password") != null) {
					request.setAttribute("passwordValid", true);
					request.setAttribute("idPasswordValid", urlSearch.getString("id"));
					isValid = false;
				}
				
				if(!isValid){
					getServletContext().getRequestDispatcher("/WEB-INF/redirect.jsp").forward(request, response);
				}else{
					String anSQLquery2 = "UPDATE urls SET nbClics = nbClics + 1 WHERE id = ?";
					PreparedStatement state2 = conn.prepareStatement(anSQLquery2);
					state2.setString(1, urlSearch.getString("id"));
					state2.executeUpdate();
					response.sendRedirect(urlSearch.getString("url_long"));
				}
			}

			
				

			conn.close();

		} catch (ClassNotFoundException e) {
			System.err.println(">> Driver not connected");
		} catch (SQLException e) {
			System.err.println("Erreur SQL : " + e.getMessage());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String password = request.getParameter("password");
		String urlPassword = null;
		String urlLong = null;
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println(">> Driver connected");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shorturl", "root", "");
			System.out.println(">> Connected");

			String anSQLquery = "SELECT * FROM urls WHERE id = ?";
			PreparedStatement state = conn.prepareStatement(anSQLquery);
			state.setString(1, request.getParameter("id"));
			ResultSet urls = state.executeQuery();
			while (urls.next()) {
				urlPassword = urls.getString("password");
				urlLong = urls.getString("url_long");
			}

			if (urlPassword != null && password.equals(urlPassword)) {
				String anSQLquery2 = "UPDATE urls SET nbClics = nbClics + 1 WHERE id = ?";
				PreparedStatement state2 = conn.prepareStatement(anSQLquery2);
				state2.setString(1, request.getParameter("id"));
				state2.executeUpdate();
				response.sendRedirect(urlLong);
			} else {
				doGet(request, response);
			}
			conn.close();

		} catch (ClassNotFoundException e) {
			System.err.println(">> Driver not connected");
		} catch (SQLException e) {
			System.err.println("Erreur SQL : " + e.getMessage());
		}
	}
}
