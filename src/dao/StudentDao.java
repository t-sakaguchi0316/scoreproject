package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends DAO{


	public Student get(String no) throws Exception {
	    Connection con = getConnection();
	    PreparedStatement st = con.prepareStatement(
	        "SELECT no, name, entYear, classNum, isAttend, school_no FROM student WHERE no = ?"
	    );
	    st.setString(1, no);
	    ResultSet rs = st.executeQuery();

	    Student student = null;
	    if (rs.next()) {
	        student = new Student();
	        student.setNo(rs.getString("no"));
	        student.setName(rs.getString("name"));
	        student.setEntYear(rs.getInt("entYear"));
	        student.setClassNum(rs.getString("classNum"));
	        student.setIsAttend(rs.getBoolean("isAttend"));

	        // 学校情報の取得が必要ならここでSchoolDaoなどで取得
	        School school = new School();
	        school.setCd(rs.getString("school_no"));
	        student.setSchool(school);
	    }

	    rs.close();
	    st.close();
	    con.close();

	    return student;
	}


	private List<Student>postFilter(School school)throws Exception{
		Connection con = getConnection();
	    PreparedStatement st = con.prepareStatement(
	        "SELECT no, name, entYear, classNum, isAttend FROM student WHERE school_no = ? AND isAttend = true"
	    );
	    st.setString(1, school.getCd());  // SchoolクラスにgetNo()があると仮定

	    ResultSet rs = st.executeQuery();

	    List<Student> list = new ArrayList<>();
	    while (rs.next()) {
	        Student student = new Student();
	        student.setNo(rs.getString("no"));
	        student.setName(rs.getString("name"));
	        student.setEntYear(rs.getInt("entYear"));
	        student.setClassNum(rs.getString("classNum"));
	        student.setIsAttend(rs.getBoolean("isAttend"));
	        student.setSchool(school);  // 引数のSchoolをそのままセット

	        list.add(student);
	    }

	    rs.close();
	    st.close();
	    con.close();

	    return list;
	}
	public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
	    Connection con = getConnection();
	    PreparedStatement st = con.prepareStatement(
	        "SELECT no, name, entYear, classNum, isAttend "
	        + "FROM student WHERE school_no = ? AND entYear = ? AND classNum = ? AND isAttend = ?"
	    );
	    st.setString(1, school.getCd());
	    st.setInt(2, entYear);
	    st.setString(3, classNum);
	    st.setBoolean(4, isAttend);

	    ResultSet rs = st.executeQuery();
	    List<Student> list = new ArrayList<>();

	    while (rs.next()) {
	        Student student = new Student();
	        student.setNo(rs.getString("no"));
	        student.setName(rs.getString("name"));
	        student.setEntYear(rs.getInt("entYear"));
	        student.setClassNum(rs.getString("classNum"));
	        student.setIsAttend(rs.getBoolean("isAttend"));
	        student.setSchool(school);
	        list.add(student);
	    }

	    rs.close();
	    st.close();
	    con.close();

	    return list;
	}

	public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
	    Connection con = getConnection();
	    PreparedStatement st = con.prepareStatement(
	        "SELECT no, name, entYear, classNum, isAttend FROM student WHERE school_no = ? AND entYear = ? AND isAttend = ?"
	    );
	    st.setString(1, school.getCd());
	    st.setInt(2, entYear);
	    st.setBoolean(3, isAttend);

	    ResultSet rs = st.executeQuery();
	    List<Student> list = new ArrayList<>();

	    while (rs.next()) {
	        Student student = new Student();
	        student.setNo(rs.getString("no"));
	        student.setName(rs.getString("name"));
	        student.setEntYear(rs.getInt("entYear"));
	        student.setClassNum(rs.getString("classNum"));
	        student.setIsAttend(rs.getBoolean("isAttend"));
	        student.setSchool(school);
	        list.add(student);
	    }

	    rs.close();
	    st.close();
	    con.close();

	    return list;
	}

	public List<Student> filter(School school, boolean isAttend) throws Exception {
	    Connection con = getConnection();
	    PreparedStatement st = con.prepareStatement(
	        "SELECT no, name, entYear, classNum, isAttend FROM student WHERE school_no = ? AND isAttend = ?"
	    );
	    st.setString(1, school.getCd());
	    st.setBoolean(2, isAttend);

	    ResultSet rs = st.executeQuery();
	    List<Student> list = new ArrayList<>();

	    while (rs.next()) {
	        Student student = new Student();
	        student.setNo(rs.getString("no"));
	        student.setName(rs.getString("name"));
	        student.setEntYear(rs.getInt("entYear"));
	        student.setClassNum(rs.getString("classNum"));
	        student.setIsAttend(rs.getBoolean("isAttend"));
	        student.setSchool(school);
	        list.add(student);
	    }

	    rs.close();
	    st.close();
	    con.close();

	    return list;
	}

	public boolean save(Student student) throws Exception {
	    Connection con = getConnection();
	    PreparedStatement st = con.prepareStatement(
	        "INSERT INTO student (no, name, entYear, classNum, isAttend, school_no) VALUES (?, ?, ?, ?, ?, ?)"
	    );
	    st.setString(1, student.getNo());
	    st.setString(2, student.getName());
	    st.setInt(3, student.getEntYear());
	    st.setString(4, student.getClassNum());
	    st.setBoolean(5, student.getIsAttend());
	    st.setString(6, student.getSchool().getCd());

	    int rows = st.executeUpdate(); // 1行追加されれば1

	    st.close();
	    con.close();

	    return rows > 0;
	}

}