package lk.pdn.scs;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.courses;

public class EnrollServerlet extends HttpServlet {
	private List<courses> getAllCourses() {
        List<courses> courses = new ArrayList<>();
        courses.add(new courses("CSE101", "Intro to CS", "Dr. Smith"));
        courses.add(new courses("MAT202", "Calculus II", "Prof. Johnson"));
        courses.add(new courses("PHY303", "Physics III", "Dr. Brown"));
        return courses;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String courseId = request.getParameter("courseId");
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }

        // Get or create enrolled courses list in session
        List<courses> enrolledCourses = (List<courses>) session.getAttribute("enrolledCourses");
        if (enrolledCourses == null) {
            enrolledCourses = new ArrayList<>();
        }

        // Find course by ID
        for (courses c : getAllCourses()) {
            if (c.getCourseId().equals(courseId)) {
                // Avoid duplicates
                if (!enrolledCourses.contains(c)) {
                    enrolledCourses.add(c);
                }
                break;
            }
        }

        session.setAttribute("enrolledCourses", enrolledCourses);
        response.sendRedirect("dashboard?enrolled=true");
    }
}
