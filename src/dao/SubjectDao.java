package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Subject;

public class SubjectDao {

	public Subject get(String cd, School school) throws Exception {
		Subject subject = null;

	    Connection con = getConnection(); // 実装は別途必要
	    PreparedStatement st = con.prepareStatement(
	        "SELECT cd, name FROM subject WHERE cd = ? AND school = ?"
	    );
	    st.setString(1, cd);
	    st.setString(2, school.getCd());
	    ResultSet rs = st.executeQuery();

	    if (rs.next()) {
	        subject = new Subject();
	        subject.setCd(rs.getString("cd"));
	        subject.setName(rs.getString("name")); // name を取得
	        subject.setSchool(school); // String ではなく School オブジェクトをセット
	    }

	    rs.close();
	    st.close();
	    con.close();

	    return subject;
	}

	private Connection getConnection() {
		// 実際には JDBC の接続処理を実装する必要があります
		return null;
	}
}
