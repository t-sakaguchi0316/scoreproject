package scoremanager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassNum;
import bean.School;
import dao.ClassNumDao;
import tool.Action;

public class StudentCreateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 1) 年度リストを作成（今年を基準に前後10年）
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        List<Integer> yearList = new ArrayList<>();
        for (int y = currentYear - 10; y <= currentYear + 10; y++) {
            yearList.add(y);
        }
        req.setAttribute("yearList", yearList);

        // 2) セッションからログインユーザの学校コードを取得
        School loginSchool = (School) req.getSession().getAttribute("loginSchool");

        // 3) クラス番号リストを取得
        List<String> classList = new ArrayList<>();
        if (loginSchool != null) {
            ClassNumDao classNumDao = new ClassNumDao();
            // DAO に findBySchool(String) メソッドを実装しておくこと
            List<ClassNum> cnBeans = classNumDao.findBySchool(loginSchool.getCd());
            for (ClassNum cn : cnBeans) {
                classList.add(cn.getClass_num());
            }
        }
        req.setAttribute("classList", classList);

        // 4) JSP へフォワード
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/main/student_create.jsp");
        rd.forward(req, res);
    }
}
