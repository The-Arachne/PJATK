package zad1;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.derby.client.am.DateTime;

@WebServlet("/HelloThere")
public class HelloThere extends HttpServlet {
	String url="jdbc:derby://localhost:1527/BAZA";
	private static final long serialVersionUID = 1L;
       
    public HelloThere() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			Connection conn = DriverManager.getConnection(url,"user","1");
			Statement stmt = conn.createStatement();
			
			String query="set SCHEMA APP";
			stmt.execute(query);
			
			query="select p.isbn,a.name as AUTOR,p.tytul,p.rok,w.name as WYDAWCA,p.cena from POZYCJE p inner join autor a on p.autid=a.autid inner join wydawca w on p.wydid=w.wydid";
			ResultSet rs=stmt.executeQuery(query);
			ArrayList<String>list=new ArrayList<>();
			
			list.add("<table id=\"myTable\">");
			list.add("<tr>  <th>ISBN</th><th>AUTOR</th><th>TYTUL</th><th>ROK</th><th>WYDAWCA</th><th>CENA</th>  </tr>");
			
			while(rs.next()) {
				list.add("<tr>  <td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td>  </tr>");
			}
			list.add("</table>");
			System.out.println(list);
			request.setAttribute("list",list.toArray(new String[list.size()]));
		} catch (SQLException e1) {e1.printStackTrace();}
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
}
