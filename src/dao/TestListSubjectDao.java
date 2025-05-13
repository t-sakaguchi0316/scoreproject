package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

/**
 * 学生ごとのテストNo別得点を取得するDAO
 */
public class TestListSubjectDao extends DAO {
    /**
     * フィルタ条件（校舎・入学年・クラス番号・科目）で絞り込むSQL
     */
    private static final String baseSql =
        "SELECT st.ent_year    AS entYear, " +
        "       t.student_no   AS studentNo, " +
        "       st.name        AS studentName, " +
        "       st.class_num   AS classNum, " +
        "       t.no           AS testNo, " +
        "       t.point        AS point " +
        "  FROM TEST t " +
        "  JOIN STUDENT st ON t.student_no = st.no " +
        " WHERE t.school_cd   = ? " +
        "   AND st.ent_year   = ? " +
        "   AND st.class_num  = ? " +
        "   AND t.subject_cd  = ?";

    /**
     * ResultSetからTestListSubjectを組み立てる。
     * 同一studentNoの行はマージし、pointsマップにテストNo→得点を登録
     */
    private List<TestListSubject> postFilter(ResultSet rs) throws Exception {
        Map<String, TestListSubject> map = new LinkedHashMap<>();
        while (rs.next()) {
            String studentNo = rs.getString("studentNo");
            TestListSubject bean = map.get(studentNo);
            if (bean == null) {
                bean = new TestListSubject();
                bean.setEntYear(rs.getInt("entYear"));
                bean.setStudentNo(studentNo);
                bean.setStudentName(rs.getString("studentName"));
                bean.setClassNum(rs.getString("classNum"));
                map.put(studentNo, bean);
            }
            int testNo = rs.getInt("testNo");
            int pt     = rs.getInt("point");
            bean.putPoint(testNo, pt);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 条件を指定してDBから該当データを取得し、postFilter経由で返却
     *
     * @param entYear   入学年
     * @param classNum  クラス番号
     * @param subject   科目bean（getCd()で科目コードを取得）
     * @param school    校舎bean（getCd()で校舎コードを取得）
     * @return TestListSubjectのリスト
     * @throws Exception SQL実行時の例外
     */
    public List<TestListSubject> filter(int entYear,
                                       String classNum,
                                       Subject subject,
                                       School school) throws Exception {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(baseSql)) {

            ps.setString(1, school.getCd());
            ps.setInt(2, entYear);
            ps.setString(3, classNum);
            ps.setString(4, subject.getCd());

            try (ResultSet rs = ps.executeQuery()) {
                return postFilter(rs);
            }
        }
    }
}