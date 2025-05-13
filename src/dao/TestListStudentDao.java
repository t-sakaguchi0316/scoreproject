package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListStudent;

/**
 * 科目ごとの学生別得点を取得するDAO
 */
public class TestListStudentDao {
    /**
     * フィルタ条件（校舎・入学年・クラス番号・科目）で絞り込むSQL
     */
    private static final String baseSql =
        "SELECT t.subject_cd   AS subjectCd, " +
        "       sub.name        AS subjectName, " +
        "       t.no            AS num, " +
        "       t.point         AS point " +
        "  FROM TEST t " +
        "  JOIN STUDENT st ON t.no            = st.no " +
        "  JOIN SUBJECT sub ON t.subject_cd   = sub.cd " +
        " WHERE st.school_cd   = ? " +
        "   AND st.ent_year    = ? " +
        "   AND st.class_num   = ? " +
        "   AND t.subject_cd   = ?";

    /**
     * ResultSetからTestListStudentを組み立てる。<br>
     * 各行をTestListStudent Beanにマッピングしてリストに追加
     */
    private List<TestListStudent> postFilter(ResultSet rs) throws Exception {
        List<TestListStudent> list = new ArrayList<>();
        while (rs.next()) {
            TestListStudent bean = new TestListStudent();
            bean.setSubjectCd(  rs.getString("subjectCd"));
            bean.setSubjectName(rs.getString("subjectName"));
            bean.setNum(        rs.getInt("num"));
            bean.setPoint(      rs.getInt("point"));
            list.add(bean);
        }
        return list;
    }

    /**
     * 条件を指定してDBから該当データを取得し、postFilter経由で返却
     *
     * @param entYear   入学年
     * @param classNum  クラス番号
     * @param subject   科目bean（getCd()で科目コードを取得）
     * @param school    校舎bean（getCd()で校舎コードを取得）
     * @return TestListStudentのリスト
     */
    public List<TestListStudent> filter(int entYear,
                                       String classNum,
                                       Subject subject,
                                       School school) {
        List<TestListStudent> result = new ArrayList<>();
        try (Connection conn = new DAO().getConnection();
             PreparedStatement ps = conn.prepareStatement(baseSql)) {

            ps.setString(1, school.getCd());
            ps.setInt(2, entYear);
            ps.setString(3, classNum);
            ps.setString(4, subject.getCd());

            try (ResultSet rs = ps.executeQuery()) {
                result = postFilter(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
