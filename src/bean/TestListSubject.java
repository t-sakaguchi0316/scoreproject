package bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 学生ごとの科目別得点を保持するBean
 */
public class TestListSubject implements Serializable {
    private int entYear;
    private String studentNo;
    private String studentName;
    private String classNum;
    private Map<Integer, Integer> points;

    public TestListSubject() {
        // points マップを初期化
        this.points = new HashMap<>();
    }

    public int getEntYear() {
        return entYear;
    }

    public void setEntYear(int entYear) {
        this.entYear = entYear;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public Map<Integer, Integer> getPoints() {
        return points;
    }

    public void setPoints(Map<Integer, Integer> points) {
        this.points = points;
    }

    /**
     * 指定科目コードの点数を文字列として取得する。
     * 未登録の場合は null を返す。
     *
     * @param key 科目コード
     * @return 点数（String）
     */
    public String getPoint(int key) {
        Integer value = this.points.get(key);
        return (value != null) ? value.toString() : null;
    }

    /**
     * 指定科目コードに対して点数を設定（追加／更新）する。
     *
     * @param key   科目コード
     * @param value 点数
     */
    public void putPoint(int key, int value) {
        this.points.put(key, value);
    }
}
