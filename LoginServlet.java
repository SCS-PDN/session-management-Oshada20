package lk.pdn.scs;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
public class LoginServerlet extends HttpServerlet {
	 protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
	        String username = req.getParameter("username");
	        String password = req.getParameter("password");

	        if ((username.equals("student1") && password.equals("pass1")) ||
	            (username.equals("student2") && password.equals("pass2"))) {

	            HttpSession session = req.getSession();
	            session.setAttribute("username", username);

	            Cookie cookie = new Cookie("username", username);
	            cookie.setMaxAge(3600);
	            res.addCookie(cookie);

	            res.sendRedirect("dashboard");
	        } else {
	            res.getWriter().println("Invalid credentials. <a href='login.html'>Try again</a>");
	        }
	    }
}
