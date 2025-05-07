package chapter14;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


public class All extends HttpServlet {
	public void doGet (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		// UTF-8 を設定
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

		PrintWriter out=response.getWriter();

		try {
			InitialContext ic=new InitialContext();
			DataSource ds=(DataSource)ic.lookup(
				"java:/comp/env/jdbc/score");
			Connection con=ds.getConnection();

			PreparedStatement st=con.prepareStatement(
			"select * from student");
			ResultSet rs=st.executeQuery();

			while (rs.next()) {
				while (rs.next()) {
				    out.println(rs.getInt("no"));
				    out.println("：");
				    out.println(rs.getString("name"));
				    out.println("：");

				}

			}

			st.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace(out);
		}

	}
}