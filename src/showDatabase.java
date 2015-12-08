import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by Owner on 08/12/2015.
 */

public class showDatabase extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        try{
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            //loading drivers for mysql


            //creating connection with the database
            Connection con= DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/names", "root", "stevo123");

             PreparedStatement ps=con.prepareStatement
               ("insert into Student values(?,?,?)");
            ps.setString(1, name);
             ps.setString(2, email);
             ps.setString(3, pass);


             int i=ps.executeUpdate();
            ResultSet res = ps.executeQuery("SELECT * FROM student");
            //("SELECT * FROM data LEFT JOIN subjects ON data.ID = subjects.ID");

            String str = GetString(res);


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

                    "<h1 align=\"center\">" + title + "</h1>\n" +"<p>"+str+"</p>"+
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
    String GetString(ResultSet resultSet) throws SQLException {
        StringBuffer results = new StringBuffer();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int numberOfColumns = metaData.getColumnCount();


        for ( int i = 1; i <= numberOfColumns; i++ ) {
            results.append( metaData.getColumnName( i )
                    + "\t" );
        }

        results.append( "<br>" );
        while ( resultSet.next() ) {

            for ( int i = 1; i <= numberOfColumns; i++ ) {
                results.append( resultSet.getObject( i )
                        + "\t\t" );
            }
            results.append( "<br>" );
            results.append( "\n" );
        }
        return results.toString();
    }
}