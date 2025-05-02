package lk.pdn.scs;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

public class LogoutServerlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession(false);
        if (session != null) session.invalidate();

        Cookie cookie = new Cookie("username", "");
        cookie.setMaxAge(0);
        res.addCookie(cookie);

        res.sendRedirect("login.html");
    }

}
