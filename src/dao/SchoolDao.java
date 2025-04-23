package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
//cdt部分の確認
public class SchoolDao extends DAO{
	public School get(String cd) throws Exception{
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement(
	            "SELECT cd FROM student WHERE cd = ?");
		ResultSet rs = st.executeQuery();

		rs.close();
	    st.close();
	    con.close();
	    School cdt = null;
	    return cdt;
	}
}