package lk.pdn.scs;

import model.courses;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

public class DashboardServlet extends HttpServlet {
    private List<courses> getCourses() {
        return Arrays.asList(
            new courses("CSC101", "Intro to CS", "Dr. Ruwan"),
            new courses("MAT202", "Calculus II", "Dr. NUWAN"),
            new courses("PHY303", "Physics III", "Dr. Jagath")
        );
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            res.sendRedirect("login.html");
            return;
        }

        req.setAttribute("courseList", getCourses());

        List<courses> enrolled = (List<courses>) session.getAttribute("enrolledCourses");
        if (enrolled == null) enrolled = new ArrayList<>();
        req.setAttribute("enrolledCourses", enrolled);

        if ("true".equals(req.getParameter("enrolled"))) {
            req.setAttribute("message", "Enrollment successful!");
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(req, res);
    }
}
