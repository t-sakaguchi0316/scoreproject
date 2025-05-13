package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

/**
 * 学生ごとの科目別得点を取得するDAO
 */
public class TestListSubjectDao {
    /**
     * フィルタ条件（校舎・入学年・クラス番号・科目）で絞り込むSQL
     */
    private static final String baseSql =
        "SELECT st.ent_year, st.no AS studentNo, " +
        "       st.name AS studentName, " +
        "       st.class_num, " +
        "       t.subject_cd AS subjectCd, " +
        "       t.point AS point " +
        "  FROM TEST t " +
        "  JOIN STUDENT st ON t.no = st.no " +
        " WHERE st.school_cd = ? " +
        "   AND st.ent_year  = ? " +
        "   AND st.class_num = ? " +
        "   AND t.subject_cd = ?";

    /**
     * ResultSetからTestListSubjectを組み立てる。<br>
     * 同一studentNoの行はマージし、pointsマップに科目コード→得点を登録
     */
    private List<TestListSubject> postFilter(ResultSet rs) throws Exception {
        Map<String, TestListSubject> map = new LinkedHashMap<>();
        while (rs.next()) {
            String no = rs.getString("studentNo");
            TestListSubject bean = map.get(no);
            if (bean == null) {
                bean = new TestListSubject();
                bean.setEntYear(rs.getInt("ent_year"));
                bean.setStudentNo(no);
                bean.setStudentName(rs.getString("studentName"));
                bean.setClassNum(rs.getString("class_num"));
                bean.setPoints(new HashMap<>());
                map.put(no, bean);
            }
            // 科目コードと得点をマップに追加
            int subjCd = rs.getInt("subjectCd");
            int pt     = rs.getInt("point");
            bean.putPoint(subjCd, pt);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 条件を指定してDBから該当データを取得し、postFilter経由で返却
     *
     * @param entYear  入学年
     * @param classNum クラス番号
     * @param subject  科目bean
     * @param school   校舎bean
     * @return TestListSubjectのリスト
     */
    public List<TestListSubject> filter(int entYear,
                                        String classNum,
                                        Subject subject,
                                        School school) {
        List<TestListSubject> result = Collections.emptyList();
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
