package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends DAO{
	public ClassNum get(String class_num,School school)throws Exception{
		Connection con = getConnection();
		ClassNum classNumobj=null;
		PreparedStatement st = con.prepareStatement(
	            "SELECT class_num,school FROM student WHERE class_num=? AND school=?");

		st.setString(1, class_num);
		st.setString(2, school.getCd());
		ResultSet rs = st.executeQuery();


		if (rs.next()) {
            classNumobj = new ClassNum();
            classNumobj.setClassNum(rs.getString("class_num"));
            classNumobj.setSchool(rs.getString("school"));

        }
		rs.close();
        st.close();
        con.close();

        return classNumobj;
	}



}
