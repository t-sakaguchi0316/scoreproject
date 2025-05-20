package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends DAO {

    // 既存の get メソッド。Bean のままなら setClass_num を呼び出し
    public ClassNum get(String class_num, School school) throws Exception {
        String sql =
            "SELECT CLASS_NUM, SCHOOL_CD " +
            "FROM CLASS_NUM " +
            "WHERE CLASS_NUM = ? AND SCHOOL_CD = ?";

        try (
            Connection con = getConnection();
            PreparedStatement st = con.prepareStatement(sql)
        ) {
            st.setString(1, class_num);
            st.setString(2, school.getCd());
            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next()) return null;
                ClassNum cn = new ClassNum();
                cn.setClass_num(rs.getString("CLASS_NUM"));
                cn.setSchool(school);
                return cn;
            }
        }
    }

    // 新規追加：指定校のクラス一覧を取得
    public List<ClassNum> findBySchool(String schoolCd) throws Exception {
        List<ClassNum> list = new ArrayList<>();
        String sql =
            "SELECT CLASS_NUM, SCHOOL_CD " +
            "FROM CLASS_NUM " +
            "WHERE SCHOOL_CD = ? " +
            "ORDER BY CLASS_NUM";

        try (
            Connection con = getConnection();
            PreparedStatement st = con.prepareStatement(sql)
        ) {
            st.setString(1, schoolCd);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ClassNum cn = new ClassNum();
                    cn.setClass_num(rs.getString("CLASS_NUM"));
                    // School Bean もセットする場合
                    School sc = new School();
                    sc.setCd(schoolCd);
                    cn.setSchool(sc);
                    list.add(cn);
                }
            }
        }
        return list;
    }
}
