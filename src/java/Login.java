
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        if (Validate.checkUser(name, pass)) {

            //dimiourgia neou session gia ton sigkekrimeno xristi
            HttpSession session = request.getSession();
            session.setAttribute(name, pass);
            session.setAttribute("user", name);
            session.setMaxInactiveInterval(30 * 60);  //kleisimo tou session meta apo 30min

            //neo cookie
            Cookie userName = new Cookie("name", name);
            userName.setMaxAge(30 * 60);
            response.addCookie(userName);
            response.sendRedirect("inside.jsp");

        } else {

            out.println("Username or Password incorrect");
            RequestDispatcher rs = request.getRequestDispatcher("/index.jsp");
            rs.include(request, response);
        }

    }
}
