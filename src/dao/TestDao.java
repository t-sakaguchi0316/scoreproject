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

/**
 * TESTテーブルのレコード取得、検索、保存（登録/更新）を行うDAO
 */
public class TestDao extends DAO {
    /** 基本SQL：成績検索 */
    private static final String baseSql =
        "SELECT t.student_no, t.subject_cd, t.school_cd, t.class_num, t.no, t.point " +
        "FROM TEST t " +
        "JOIN STUDENT s ON t.student_no = s.no " +
        "WHERE s.ent_year = ? AND s.class_num = ? " +
        "AND t.subject_cd = ? AND t.no = ? AND t.school_cd = ?";

    /**
     * 1件取得
     */
    public Test get(Student student, Subject subject, School school, int no) throws Exception {
        String sql =
            "SELECT * FROM TEST " +
            "WHERE student_no = ? AND subject_cd = ? AND school_cd = ? AND no = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getNo());
            ps.setString(2, subject.getCd());
            ps.setString(3, school.getCd());
            ps.setInt(4, no);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Test t = new Test();
                    t.setStudent(student);
                    t.setSubject(subject);
                    t.setSchool(school);
                    t.setClassNum(rs.getString("class_num"));
                    t.setNo(rs.getInt("no"));
                    t.setPoint(rs.getInt("point"));
                    return t;
                }
            }
        }
        return null;
    }

    /**
     * 成績の検索
     */
    public List<Test> filter(int entYear,
                             String classNum,
                             Subject subject,
                             int no,
                             School school) throws Exception {
        List<Test> list = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(baseSql)) {

            ps.setInt(1, entYear);
            ps.setString(2, classNum);
            ps.setString(3, subject.getCd());
            ps.setInt(4, no);
            ps.setString(5, school.getCd());

            try (ResultSet rs = ps.executeQuery()) {
                list = postFilter(rs, school);
            }
        }
        return list;
    }

    /**
     * ResultSet → List<Test> の変換
     * 同一student_noごとに1レコードずつBeanにマッピング
     */
    private List<Test> postFilter(ResultSet rs, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        StudentDao studentDao = new StudentDao();
        SubjectDao subjectDao = new SubjectDao();

        while (rs.next()) {
            String studentNo = rs.getString("student_no");
            // StudentDao#get は String 1 引数を受け取る
            Student student = studentDao.get(studentNo);
            String subjectCd = rs.getString("subject_cd");
            Subject subject = subjectDao.get(subjectCd, school);

            Test t = new Test();
            t.setStudent(student);
            t.setSubject(subject);
            t.setSchool(school);
            t.setClassNum(rs.getString("class_num"));
            t.setNo(rs.getInt("no"));
            t.setPoint(rs.getInt("point"));
            list.add(t);
        }
        return list;
    }

    /**
     * 成績の保存（複数件）
     */
    public boolean save(List<Test> tests) throws Exception {
        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);
            for (Test test : tests) {
                if (!save(test, conn)) {
                    conn.rollback();
                    return false;
                }
            }
            conn.commit();
            return true;
        }
    }

    /**
     * 成績の保存（1件）。登録または更新
     */
    private boolean save(Test test, Connection conn) throws Exception {
        String mergeSql =
            "MERGE INTO TEST (student_no, subject_cd, school_cd, class_num, no, point) " +
            "KEY(student_no, subject_cd, school_cd, no) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(mergeSql)) {
            ps.setString(1, test.getStudent().getNo());
            ps.setString(2, test.getSubject().getCd());
            ps.setString(3, test.getSchool().getCd());
            ps.setString(4, test.getClassNum());
            ps.setInt(5, test.getNo());
            ps.setInt(6, test.getPoint());
            return ps.executeUpdate() > 0;
        }
    }
}