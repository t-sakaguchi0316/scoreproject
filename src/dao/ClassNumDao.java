package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends DAO {

    /**
     * クラス番号テーブルから 1 レコードを取得する
     *
     * @param class_num 検索するクラス番号
     * @param school    検索する学校オブジェクト
     * @return 該当レコードがあれば ClassNum、なければ null
     * @throws Exception
     */
    public ClassNum get(String class_num, School school) throws Exception {
        Connection con = getConnection();
        ClassNum classNumObj = null;

        // ■ テーブル名とカラム名を適切に修正
        String sql = ""
            + "SELECT CLASS_NUM, SCHOOL_CD "
            + "FROM CLASS_NUM "
            + "WHERE CLASS_NUM = ? AND SCHOOL_CD = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, class_num);
        st.setString(2, school.getCd());

        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            classNumObj = new ClassNum();
            // ■ bean.ClassNum#setClassNum は文字列を受け取る
            classNumObj.setClass_num(rs.getString("CLASS_NUM"));
            // ■ bean.ClassNum#setSchool は School 型を受け取る想定
            classNumObj.setSchool(school);
        }

        rs.close();
        st.close();
        con.close();

        return classNumObj;
    }
}
