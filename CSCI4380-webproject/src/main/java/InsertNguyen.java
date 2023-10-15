
/**
 * @file InsertNguyen.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SimpleFormInsert")
public class InsertNguyen extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public InsertNguyen() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String firstName = request.getParameter("firstName");
      String lastName = request.getParameter("lastName");
      String email = request.getParameter("email");
      String date = request.getParameter("date");
      String goals = request.getParameter("goals");
      Connection connection = null;
      String insertSql = " INSERT INTO myTable (id, FIRSTNAME, LASTNAME, EMAIL, DATE, GOALS) values (default, ?, ?, ?, ?, ?)";

      try {
         DBConnection.getDBConnection();
         connection = DBConnection.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, firstName);
         preparedStmt.setString(1, lastName);
         preparedStmt.setString(2, email);
         preparedStmt.setString(3, date);
         preparedStmt.setString(4, goals);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Insert Data to DB table";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>First Name</b>: " + firstName + "\n" + //
            "  <li><b>Last Name</b>: " + lastName + "\n" + //    
            "  <li><b>Email</b>: " + email + "\n" + //
            "  <li><b>Date</b>: " + date + "\n" + //
            "  <li><b>Goals</b>: " + goals + "\n" + //
            "</ul>\n");

      out.println("<a href=/CSCI4380-webproject/search_nguyen.html>Search Data</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}

