import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MySevletDB")
public class MyServletDB extends HttpServlet {
   private static final long serialVersionUID = 1L;
   static String url = "jdbc:mysql://ec2-54-180-113-223.ap-northeast-2.compute.amazonaws.com:3306/DBwebproject";
   static String user = "dnguyen_remote";
   static String password = "henry1007";
   static Connection connection = null;


   public MyServletDB() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      response.getWriter().println("-------- MySQL JDBC Connection Testing ------------<br>");
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");//("com.mysql.jdbc.Driver");
      } catch (ClassNotFoundException e) {
         System.out.println("Where is your MySQL JDBC Driver?");
         e.printStackTrace();
         return;
      }
      response.getWriter().println("MySQL JDBC Driver Registered!<br>");
      connection = null;
      try {
         connection = DriverManager.getConnection(url, user, password);
      } catch (SQLException e) {
         System.out.println("Connection Failed! Check output console");
         e.printStackTrace();
         return;
      }
      if (connection != null) {
         response.getWriter().println("You made it, take control your database now!<br>");
      } else {
         System.out.println("Failed to make connection!");
      }
      try {
         String selectSQL = "SELECT * FROM myTable";// WHERE MYUSER LIKE ?";
//         String theUserName = "user%";
         response.getWriter().println(selectSQL + "<br>");
         response.getWriter().println("------------------------------------------<br>");
         PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
//         preparedStatement.setString(1, theUserName);
         ResultSet rs = preparedStatement.executeQuery();
         while (rs.next()) {
            String id = rs.getString("ID");
            String firstname = rs.getString("FIRSTNAME");
            String lastname = rs.getString("LASTNAME");
            String email = rs.getString("EMAIL");
            String date = rs.getString("DATE");
            String goals = rs.getString("GOALS");
            String height = rs.getString("HEIGHT");
            String weight = rs.getString("WEIGHT");
            response.getWriter().append("USER ID: " + id + ", ");
            response.getWriter().append("USER FRISTNAME: " + firstname + ", ");
            response.getWriter().append("USER LASTNAME: " + lastname + ", ");
            response.getWriter().append("USER EMAIL: " + email + ", ");
            response.getWriter().append("USER DATE: " + date + ", ");
            response.getWriter().append("USER HEIGHT: " + height + ", ");
            response.getWriter().append("USER WEIGHT: " + weight + ", ");
            response.getWriter().append("USER GOALS: " + goals + "<br>");
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}