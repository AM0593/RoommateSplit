
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Register extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        try {

            //loading driver 
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RM", "root", "diktyawww14");

            PreparedStatement ps = con.prepareStatement("insert into user_table values(?,?)");

            ps.setString(1, name);
            ps.setString(2, pass);
            int i = ps.executeUpdate();

            if (i > 0) {

                response.sendRedirect("index.jsp");
                         //   request.getRequestDispatcher("/index.jsp").forward(request, response);

            }

        } catch (Exception se) {
            se.printStackTrace();
        }

    }
}
