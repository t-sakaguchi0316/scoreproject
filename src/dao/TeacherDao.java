package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Teacher;

public class TeacherDao extends DAO{

	public Teacher get(String id) throws Exception{
		Teacher teacher=null;
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement(
	            "SELECT id FROM teacher WHERE id = ?");

		 st.setString(1, id);

		 ResultSet rs = st.executeQuery();

		if (rs.next()) {
            teacher = new Teacher();
            teacher.setId(rs.getString("id"));

        }
		rs.close();
        st.close();
        con.close();

        return teacher;


	}

	public Teacher login(String id,String password)throws Exception{
		Connection con =getConnection();
		PreparedStatement st = con.prepareStatement(
	            "SELECT id,password FROM teacher WHERE id = ? AND password=?");
		Teacher teacher = null;
		st.setString(1,id);
		st.setString(2, password);

		ResultSet rs = st.executeQuery();

		if(rs.next()){
			teacher=new Teacher();
			teacher.setId(rs.getString("id"));
			teacher.setPassword(rs.getString("password"));

		}
		rs.close();
        st.close();
        con.close();

        return teacher;
	}
}