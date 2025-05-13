package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends DAO {

    // 1件取得
    public Test get(Student student, Subject subject, School school, int no) throws Exception {
        Test test = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                "SELECT * FROM TEST WHERE student_no = ? AND subject_cd = ? AND school_cd = ? AND num = ?"
            );
            statement.setString(1, student.getNo());
            statement.setString(2, subject.getCd());
            statement.setString(3, school.getCd());
            statement.setInt(4, no);

            ResultSet rSet = statement.executeQuery();

            if (rSet.next()) {
                test = new Test();
                test.setStudent(student);
                test.setSubject(subject);
                test.setSchool(school);
                test.setClassNum(rSet.getString("class_num"));
                test.setNo(rSet.getInt("num"));
                test.setPoint(rSet.getInt("point"));
            }
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return test;
    }

    // 成績の検索
    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            String sql = "SELECT * FROM TEST t JOIN STUDENT s ON t.student_no = s.no " +
                         "WHERE s.ent_year = ? AND s.class_num = ? AND t.subject_cd = ? AND t.num = ? AND t.school_cd = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, entYear);
            statement.setString(2, classNum);
            statement.setString(3, subject.getCd());
            statement.setInt(4, num);
            statement.setString(5, school.getCd());

            ResultSet rSet = statement.executeQuery();
            list = postFilter(rSet, school, subject);
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return list;
    }

    // 成績の保存（複数）
    public boolean save(List<Test> list) throws Exception {
        Connection connection = getConnection();
        try {
            for (Test test : list) {
                if (!save(test, connection)) {
                    connection.rollback();
                    return false;
                }
            }
            connection.commit();
            return true;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            if (connection != null) connection.close();
        }
    }

    // 成績の保存（1件）
    private boolean save(Test test, Connection connection) throws Exception {
        PreparedStatement statement = null;

        try {
            String sql = "MERGE INTO TEST USING (SELECT 1 FROM DUAL) ON " +
                         "(student_no = ? AND subject_cd = ? AND school_cd = ? AND num = ?) " +
                         "WHEN MATCHED THEN UPDATE SET point = ? " +
                         "WHEN NOT MATCHED THEN INSERT (student_no, subject_cd, school_cd, class_num, num, point) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, test.getStudent().getNo());
            statement.setString(2, test.getSubject().getCd());
            statement.setString(3, test.getSchool().getCd());
            statement.setInt(4, test.getNo());
            statement.setInt(5, test.getPoint());
            statement.setString(6, test.getStudent().getNo());
            statement.setString(7, test.getSubject().getCd());
            statement.setString(8, test.getSchool().getCd());
            statement.setString(9, test.getClassNum());
            statement.setInt(10, test.getNo());
            statement.setInt(11, test.getPoint());

            int result = statement.executeUpdate();
            return result > 0;
        } finally {
            if (statement != null) statement.close();
        }
    }

    // ResultSet → List<Test> の変換
    private List<Test> postFilter(ResultSet rSet, School school, Subject subject) throws Exception {
        List<Test> list = new ArrayList<>();
        StudentDao studentDao = new StudentDao();

        while (rSet.next()) {
            Test test = new Test();
            test.setStudent(studentDao.get(rSet.getString("student_no")));
            test.setSubject(subject);
            test.setSchool(school);
            test.setClassNum(rSet.getString("class_num"));
            test.setNo(rSet.getInt("num"));
            test.setPoint(rSet.getInt("point"));
            list.add(test);
        }

        return list;
    }
}
