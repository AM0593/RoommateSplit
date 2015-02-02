
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Split extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String cost = request.getParameter("cost");
        String mates = request.getParameter("mates");

        float perperson = Float.parseFloat(cost) / Float.parseFloat(mates);

        try {

            //loading driver 
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RM", "root", "diktyawww14");

            PreparedStatement ps = con.prepareStatement("insert into expenses_table values(?,?,?,?,?)");
            ps.setString(1, "0");
            ps.setString(2, name);
            ps.setString(3, cost);
            ps.setString(4, mates);
            ps.setString(5, String.valueOf(perperson));

            int i = ps.executeUpdate();

            if (i > 0) {

                response.sendRedirect("inside.jsp");
                

            }

        } catch (Exception se) {
            se.printStackTrace();
        }

    }
}
