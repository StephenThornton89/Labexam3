/**
 * Created by Owner on 08/12/2015.
 */
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
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String name1 = request.getParameter("name1");
        String newname = request.getParameter("new_name");
        try{
            String driver = "com.mysql.jdbc.Driver";
                Class.forName(driver);
            //loading drivers for mysql


            //creating connection with the database
            Connection  con=DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/names","root","stevo123");

           // PreparedStatement ps=con.prepareStatement
                 //   ("insert into Student values(?,?,?)");
            PreparedStatement ps2=con.prepareStatement ("Update student SET email=? WHERE email=?;");
          //  ps.setString(1, name);
           // ps.setString(2, email);
           // ps.setString(3, pass);

            ps2.setString(1, request.getParameter("new_name"));
            ps2.setString(2, request.getParameter("name2"));
           // int i=ps.executeUpdate();
            int i=ps2.executeUpdate();


                System.out.println("Sent to database");
                //Sends back gotten informations
                response.setContentType("text/html");
                String title ="Using GET Method to Read Form Data";
                String docType =
                        "<!doctype html public \"-//w3c//dtd html 4.0 "+
                                "transitional//en\">\n";
                out.println(docType +
                        "<html>\n" +
                        "<head><title>" + title + "</title></head>\n" +

                        "<h1 align=\"center\">" + title + "</h1>\n" +
                        "<ul>\n" +"\n" +
                        "</ul>\n" +
                        "</body></html>")
                ;

            /*if(ii>0)
            {
                response.setContentType("text/html");
                String title ="Using GET Method to Read Form Data";
                String docType =
                        "<!doctype html public \"-//w3c//dtd html 4.0 "+
                                "transitional//en\">\n";
                out.println(docType +
                        "<html>\n" +
                        "<head><title>" + title + "</title></head>\n" +

                        "<h1 align=\"center\">" + title + "</h1>\n" +
                        "<ul>\n" +"\n" +
                        "</ul>\n" +
                        "</body></html>")
                ;
            }*/

        }
        catch(Exception se)
        {
            se.printStackTrace();
        }

    }
}