/**
 * Created by Stephen on 08/12/2015.
 */
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class exam extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("first_name");
        String last = request.getParameter("last_name");
        String ID = request.getParameter("IDNum");
        String email = request.getParameter("email");
        String phone = request.getParameter("telephone");
        String com = request.getParameter("comments");
        try{
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            //loading drivers for mysql


            //creating connection with the database
            Connection con= DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/names", "root", "stevo123");

            PreparedStatement ps=con.prepareStatement
                    ("insert into person values(?,?,?,?,?,?)");

            if(name.length()>0)
            ps.setString(1, name);
            else
                ps.setString(1, "Void");

            if(last.length()>0)
                ps.setString(2, last);
            else
                ps.setString(2, "Void");
            if(ID.length()>0)
                ps.setString(3, ID);
            else
                ps.setString(3, "Void");
            if(email.length()>0)
            ps.setString(4, email);
            else
                ps.setString(4, "Void");
            if(phone.length()>0)
            ps.setString(5, phone);
            else
                ps.setString(5, "Void");
            if(phone.length()>0)
            ps.setString(6, com);
            else
                ps.setString(6, "Void");


            int i=ps.executeUpdate();
            ResultSet res = ps.executeQuery("SELECT * FROM person");
            //("SELECT * FROM data LEFT JOIN subjects ON data.ID = subjects.ID");

            String str = GetString(res);


            System.out.println("Sent to database");
            //Sends back gotten informations
            response.setContentType("text/html");
            String title ="Data that is stored in the database";
            String docType =
                    "<!doctype html public \"-//w3c//dtd html 4.0 "+
                            "transitional//en\">\n";
            out.println(docType +
                    "<html>\n" +
                    "<head><title>" + title + "</title></head>\n" +

                    "<h1 align=\"center\">" + title + "</h1>\n" +"<p>"+str+"</p>"+
                    "<ul>\n" +"\n" +
                    "</ul>\n" +
                    "</body></html>")
            ;


        }
        catch(Exception se)
        {
            se.printStackTrace();
        }

    }
    String GetString(ResultSet resultSet) throws SQLException {

        StringBuffer results = new StringBuffer();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int numberOfColumns = metaData.getColumnCount();

        results.append("<table>");
        results.append("<tr>");
        for ( int i = 1; i <= numberOfColumns; i++ ) {
            results.append("<td>"+ metaData.getColumnName( i )
                    + "</td>" );
        }

        results.append( "</tr>" );

        while ( resultSet.next() ) {
            results.append( "<tr>" );
            for ( int i = 1; i <= numberOfColumns; i++ ) {
                results.append("<td>"+ resultSet.getObject( i )
                        + "</td>" );
            }
            results.append( "</tr>" );
            results.append( "\n" );
        }
        results.append("</table>");

        return results.toString();
    }
}