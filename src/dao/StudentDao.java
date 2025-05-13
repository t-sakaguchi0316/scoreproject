package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

/**
 * STUDENTテーブルのレコード取得、検索、保存（登録/更新）を行うDAO
 */
public class StudentDao extends DAO {
    /** 1件取得SQL */
    private static final String SELECT_ONE_SQL =
        "SELECT no, name, ent_year, class_num, is_attend, school_cd " +
        "FROM STUDENT WHERE no = ?";

    /** 検索SQL：校舎・入学年・クラス番号・在籍フラグ */
    private static final String SELECT_FILTER_WITH_CLASS =
        "SELECT no, name, ent_year, class_num, is_attend " +
        "FROM STUDENT " +
        "WHERE school_cd = ? AND ent_year = ? AND class_num = ? AND is_attend = ? " +
        "ORDER BY no ASC";

    /** 検索SQL：校舎・入学年・在籍フラグ */
    private static final String SELECT_FILTER_WITH_YEAR =
        "SELECT no, name, ent_year, class_num, is_attend " +
        "FROM STUDENT " +
        "WHERE school_cd = ? AND ent_year = ? AND is_attend = ? " +
        "ORDER BY no ASC";

    /** 検索SQL：校舎・在籍フラグ */
    private static final String SELECT_FILTER_BASIC =
        "SELECT no, name, ent_year, class_num, is_attend " +
        "FROM STUDENT " +
        "WHERE school_cd = ? AND is_attend = ? " +
        "ORDER BY no ASC";

    /**
     * 学生番号で1件取得
     * @param no 学生番号
     * @return Student または null
     */
    public Student get(String no) throws Exception {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ONE_SQL)) {
            ps.setString(1, no);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Student s = new Student();
                    s.setNo(rs.getString("no"));
                    s.setName(rs.getString("name"));
                    s.setEntYear(rs.getInt("ent_year"));
                    s.setClassNum(rs.getString("class_num"));
                    s.setIsAttend(rs.getBoolean("is_attend"));
                    School sc = new School();
                    sc.setCd(rs.getString("school_cd"));
                    s.setSchool(sc);
                    return s;
                }
            }
        }
        return null;
    }

    /**
     * 校舎・入学年・クラス番号・在籍フラグで学生リスト取得
     */
    public List<Student> filter(School school,
                                int entYear,
                                String classNum,
                                boolean isAttend) throws Exception {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_FILTER_WITH_CLASS)) {
            ps.setString(1, school.getCd());
            ps.setInt(2, entYear);
            ps.setString(3, classNum);
            ps.setBoolean(4, isAttend);
            try (ResultSet rs = ps.executeQuery()) {
                return postFilter(rs, school);
            }
        }
    }

    /**
     * 校舎・入学年・在籍フラグで学生リスト取得
     */
    public List<Student> filter(School school,
                                int entYear,
                                boolean isAttend) throws Exception {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_FILTER_WITH_YEAR)) {
            ps.setString(1, school.getCd());
            ps.setInt(2, entYear);
            ps.setBoolean(3, isAttend);
            try (ResultSet rs = ps.executeQuery()) {
                return postFilter(rs, school);
            }
        }
    }

    /**
     * 校舎・在籍フラグで学生リスト取得
     */
    public List<Student> filter(School school,
                                boolean isAttend) throws Exception {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_FILTER_BASIC)) {
            ps.setString(1, school.getCd());
            ps.setBoolean(2, isAttend);
            try (ResultSet rs = ps.executeQuery()) {
                return postFilter(rs, school);
            }
        }
    }

    /**
     * ResultSetからStudentリストを組み立てる
     */
    private List<Student> postFilter(ResultSet rs, School school) throws Exception {
        List<Student> list = new ArrayList<>();
        while (rs.next()) {
            Student s = new Student();
            s.setNo(rs.getString("no"));
            s.setName(rs.getString("name"));
            s.setEntYear(rs.getInt("ent_year"));
            s.setClassNum(rs.getString("class_num"));
            s.setIsAttend(rs.getBoolean("is_attend"));
            s.setSchool(school);
            list.add(s);
        }
        return list;
    }

    /**
     * 学生データを登録または更新
     */
    public boolean save(Student student) throws Exception {
        String mergeSql =
            "MERGE INTO STUDENT (no, name, ent_year, class_num, is_attend, school_cd) " +
            "KEY(no) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(mergeSql)) {
            ps.setString(1, student.getNo());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getEntYear());
            ps.setString(4, student.getClassNum());
            ps.setBoolean(5, student.getIsAttend());
            ps.setString(6, student.getSchool().getCd());
            return ps.executeUpdate() > 0;
        }
    }
}
